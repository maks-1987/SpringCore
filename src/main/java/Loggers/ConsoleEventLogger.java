package Loggers;

import Beans.Event;

public class ConsoleEventLogger extends AbstractLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString()
                        + " я из логИвента");
    }
}
