package com.core;

import com.core.Beans.Client;
import com.core.Beans.EventType;
import com.core.Beans.Event;
import com.core.Loggers.EventLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Приложение будет проверять message и подменять на полноеимя + печатать
 * его на консоль.
 */
public class App {

    private Client client;
    private EventLogger defaultLogger; // объект интерфейса ЭвентЛоггер
    private Map<EventType, EventLogger> loggers;
    private String startupMessage;

    //private StatisticsAspect statisticsAspect;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml",
                        "loggers.xml", "aspects.xml", "db.xml");
        App app = (App) ctx.getBean("app");

        System.out.println(app.startupMessage);

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

/*        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        app.outputLoggingCounter();*/

        app.logEvents(ctx);
        ctx.close();
    }
    // вынесем часть кода в метод logEvents()
    public void logEvents(ApplicationContext ctx) {
        Event event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        logEvent(null, event, "Some event for 3");
    }

    public App() {}

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    /*private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.println("core.Loggers statistics. Number of calls: ");
            for (Map.Entry<Class<?>, Integer>
                    entry: statisticsAspect.getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        }
    }*/

    public void setStartupMessage(String startupMessage) {
        this.startupMessage = startupMessage;
    }

/*  public void setStatisticsAspect(StatisticsAspect statisticsAspect) {
        this.statisticsAspect = statisticsAspect;
    }*/

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

}/*
Schema already exists
Table already exists
Initialized Event.AUTO_ID to 4
AwareBean > My name is 'awared'
AwareBean > My name is 'class org.springframework.context.support.ClassPathXmlApplicationContext
AwareBean > My eventPublisher is 'class org.springframework.context.support.ClassPathXmlApplicationContext
ContextRefreshedEvent > org.springframework.context.support.ClassPathXmlApplicationContext@17d677df: startup date [Fri Aug 24 11:55:22 EEST 2018]; root of context hierarchy
null

Client says: Hello there!
ConsoleEventLogger max count is not reached. Continue...
BEFORE: ConsoleEventLogger logEvent
Event [id=5, msg=Some event for John Smith;, date=24 авг. 2018 г., 11:55:28] я из логИвента
AFTER_RET: null
ConsoleEventLogger max count is not reached. Continue...
BEFORE: ConsoleEventLogger logEvent
Event [id=6, msg=One more event for John Smith;, date=24 авг. 2018 г., 11:55:28] я из логИвента
AFTER_RET: null
ConsoleEventLogger max count is reached. Logging to File logger
BEFORE: FileEventLogger logEvent
AFTER_RET: null
BEFORE: CombinedEventLogger logEvent
ConsoleEventLogger max count is reached. Logging to File logger
BEFORE: FileEventLogger logEvent
AFTER_RET: null
BEFORE: FileEventLogger logEvent
AFTER_RET: null
BEFORE: DBLogger logEvent

Saved to DB event with id 8
AFTER_RET: null
AFTER_RET: null
BEFORE: CacheFileEventLogger logEvent
AFTER_RET: null
core.Loggers statistics. Number of calls:
    FileEventLogger: 3
    CombinedEventLogger: 1
    ConsoleEventLogger: 2
    DBLogger: 1
    CacheFileEventLogger: 1
ContextClosedEvent > org.springframework.context.support.ClassPathXmlApplicationContext@17d677df:
 startup date [Fri Aug 24 11:55:22 EEST 2018]; root of context hierarchy
Total events in the DB: 2
All DB Event ids: 4, 8
*/