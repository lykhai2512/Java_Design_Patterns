package Singleton;
public final class Singleton {
    private static Singleton instance;
    private static String[] logs;

    private Singleton() {
    }

    public synchronized static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
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
}