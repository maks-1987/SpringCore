package Loggers;

public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(String msg) {
        System.out.println(msg + " я из логИвента");
    }
}
