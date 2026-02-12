public final class Logger {
    private static Logger instance;
    private static String[] logs;

    private Logger() {
    }

    public synchronized static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        if (logs == null) {
            logs = new String[0];
        }
        String[] newLogs = new String[logs.length + 1];
        System.arraycopy(logs, 0, newLogs, 0, logs.length);
        newLogs[logs.length] = message;
        logs = newLogs;
    }

    public void post() {
        if (logs == null || logs.length == 0) {
            System.out.println("Log is empty.");
            return;
        }
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public void clear() {
        logs = new String[0];
    }

    public void pop() {
        if (logs != null && logs.length > 0) {
            String[] newLogs = new String[logs.length - 1];
            System.arraycopy(logs, 0, newLogs, 0, logs.length - 1);
            logs = newLogs;
        }
    }

    public int getLogCount() {
        return logs == null ? 0 : logs.length;
    }

    // --- TEST SUITE ---
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        int passed = 0;
        int failed = 0;

        System.out.println("Starting Logger Tests...\n");

        // Test 1: Singleton Identity
        Logger anotherLogger = Logger.getInstance();
        if (logger == anotherLogger) {
            System.out.println("[PASS] Singleton identity confirmed.");
            passed++;
        } else {
            System.out.println("[FAIL] Singleton instances are different!");
            failed++;
        }

        // Test 2: Logging and Count
        logger.clear();
        logger.log("First Entry");
        logger.log("Second Entry");
        if (logger.getLogCount() == 2) {
            System.out.println("[PASS] Log count is correct after additions.");
            passed++;
        } else {
            System.out.println("[FAIL] Expected count 2, got " + logger.getLogCount());
            failed++;
        }

        // Test 3: Pop functionality
        logger.pop();
        if (logger.getLogCount() == 1) {
            System.out.println("[PASS] Pop reduced log count correctly.");
            passed++;
        } else {
            System.out.println("[FAIL] Pop failed to reduce count.");
            failed++;
        }

        // Test 4: Clear functionality
        logger.clear();
        if (logger.getLogCount() == 0) {
            System.out.println("[PASS] Clear emptied the logs.");
            passed++;
        } else {
            System.out.println("[FAIL] Clear did not reset logs.");
            failed++;
        }

        // Test 5: Pop on empty log (Edge Case)
        try {
            logger.pop();
            System.out.println("[PASS] Pop on empty log handled safely.");
            passed++;
        } catch (Exception e) {
            System.out.println("[FAIL] Pop on empty log threw exception: " + e.getMessage());
            failed++;
        }

        System.out.println("\n--- Test Results ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}