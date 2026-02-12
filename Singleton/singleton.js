class ScoreSingleton {
    constructor() {
        if (ScoreSingleton.instance) {
            return ScoreSingleton.instance;
        }
        this.score = 0;
        ScoreSingleton.instance = this;
    }

    getScore() {
        return this.score;
    }

    addScore(points) {
        this.score += points;
    }
    
    scaleScore(factor) {
        this.score *= factor;
    }

    resetScore(base) {
        this.score = base;
    }
}



// --- Test 1: Singleton Identity ---
const instanceA = new ScoreSingleton();
const instanceB = new ScoreSingleton();

console.assert(instanceA === instanceB, "FAILED: Multiple instances are not the same object.");
console.log("PASSED: Singleton identity confirmed.");

// --- Test 2: Initial State ---
instanceA.resetScore(0); // Ensure clean slate for testing
console.assert(instanceA.getScore() === 0, `FAILED: Expected score 0, got ${instanceA.getScore()}`);
console.log("PASSED: Initial state is 0.");

// --- Test 3: Cross-Instance State Persistence ---
instanceA.addScore(50);
console.assert(instanceB.getScore() === 50, "FAILED: Score added to instanceA did not reflect in instanceB.");
console.log("PASSED: State persists across different variables.");

// --- Test 4: Mathematical Operations ---
instanceB.scaleScore(2); // 50 * 2 = 100
instanceA.addScore(25);  // 100 + 25 = 125
console.assert(instanceA.getScore() === 125, `FAILED: Math operations failed. Expected 125, got ${instanceA.getScore()}`);
console.log("PASSED: Scaling and addition work correctly.");

// --- Test 5: Reset Functionality ---
instanceA.resetScore(500);
console.assert(instanceB.getScore() === 500, "FAILED: Reset failed to update shared state.");
console.log("PASSED: Reset functionality confirmed.");

// --- Test 6: Handling Negative Values & Decimals ---
instanceA.resetScore(10);
instanceA.addScore(-20);   // 10 - 20 = -10
instanceA.scaleScore(0.5); // -10 * 0.5 = -5
console.assert(instanceB.getScore() === -5, `FAILED: Edge case math failed. Expected -5, got ${instanceB.getScore()}`);
console.log("PASSED: Negative and decimal values handled.");

// --- Test 7: The "New" Keyword Immunity ---
const instanceC = new ScoreSingleton();
instanceC.addScore(100); 
console.assert(instanceA.getScore() === instanceC.getScore(), "FAILED: instanceC is not tracking with instanceA.");
console.log("PASSED: Constructor reliably returns existing instance.");

console.log("\nAll tests passed.");
