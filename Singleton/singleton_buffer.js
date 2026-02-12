const { Worker, isMainThread, workerData } = require('worker_threads');

class ScoreSingleton {
    constructor(sharedBuffer) {
        if (ScoreSingleton.instance) {
            return ScoreSingleton.instance;
        }

        // Synchronization logic: use shared memory if provided
        if (sharedBuffer) {
            this._view = new Int32Array(sharedBuffer);
        } else {
            this._score = 0;
        }

        ScoreSingleton.instance = this;
    }

    getScore() {
        return this._view ? Atomics.load(this._view, 0) : this._score;
    }

    addScore(points) {
        if (this._view) {
            Atomics.add(this._view, 0, points);
        } else {
            this._score += points;
        }
    }
    
    scaleScore(factor) {
        // Scaling is trickier with Atomics; usually requires a Compare-and-Swap loop
        if (this._view) {
            let current, next;
            do {
                current = Atomics.load(this._view, 0);
                next = Math.round(current * factor);
            } while (Atomics.compareExchange(this._view, 0, current, next) !== current);
        } else {
            this._score *= factor;
        }
    }

    resetScore(base) {
        if (this._view) {
            Atomics.store(this._view, 0, base);
        } else {
            this._score = base;
        }
    }
}

// ==========================================
// EXECUTION LOGIC
// ==========================================

if (isMainThread) {
    console.log("🚀 Starting ScoreSingleton Multi-Thread Tests...");

    // 1. Setup Shared Memory
    const sharedBuffer = new SharedArrayBuffer(4); // 4 bytes for 1 integer
    const mainInstance = new ScoreSingleton(sharedBuffer);
    mainInstance.resetScore(0);

    // 2. Spawn Workers
    const workerCount = 4;
    let workersFinished = 0;

    for (let i = 0; i < workerCount; i++) {
        const worker = new Worker(__filename, { 
            workerData: { name: `Worker ${i + 1}`, buffer: sharedBuffer } 
        });

        worker.on('exit', () => {
            workersFinished++;
            if (workersFinished === workerCount) {
                console.log(`\n🏁 All workers done. Final Shared Score: ${mainInstance.getScore()}`);
                console.assert(mainInstance.getScore() === workerCount * 100, "❌ FAILED: Synchronization lost updates!");
                console.log("✅ PASSED: Synchronization test successful.");
            }
        });
    }

} else {
    // WORKER THREAD LOGIC
    const { name, buffer } = workerData;
    const score = new ScoreSingleton(buffer);

    // Simulation: Add 1 point, 100 times
    for (let i = 0; i < 100; i++) {
        score.addScore(1);
    }

    console.log(`${name} finished. Local view of score: ${score.getScore()}`);
}