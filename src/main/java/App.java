import Beans.Client;
import Beans.Event;
import Beans.EventType;
import Loggers.EventLogger;

import aspects.StatisticsAspect;
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
    private StatisticsAspect statisticsAspect;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        System.out.println(app.startupMessage);

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "One more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "And one more event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        app.outputLoggingCounter();

        ctx.close();
    }

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

    private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.println("Loggers statistics. Number of calls: ");
            for (Map.Entry<Class<?>, Integer>
                    entry: statisticsAspect.getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        }
    }

    public void setStartupMessage(String startupMessage) {
        this.startupMessage = startupMessage;
    }

    public void setStatisticsAspect(StatisticsAspect statisticsAspect) {
        this.statisticsAspect = statisticsAspect;
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

}/*
org.springframework.beans.factory.BeanCreationException: Error creating
bean with name 'statisticsAspect' defined in class path resource
[aspects.xml]: BeanPostProcessor before instantiation of bean failed;
nested exception is org.springframework.beans.factory.BeanCreationException:
Error creating bean with name 'org.springframework.aop.aspectj.AspectJPointcutAdvisor#0':
Cannot create inner bean '(inner bean)#7dfd3c81' of type
[org.springframework.aop.aspectj.AspectJAroundAdvice] while setting constructor
argument; nested exception is org.springframework.beans.factory.BeanCreationException:
Error creating bean with name '(inner bean)#7dfd3c81': Cannot resolve reference
to bean 'consoleLogging' while setting constructor argument; nested exception
is org.springframework.beans.factory.BeanCreationException: Error creating
bean with name 'consoleLogging': Instantiation of bean failed; nested
exception is java.lang.NoClassDefFoundError:
org/aspectj/weaver/tools/PointcutDesignatorHandler

    Some event for John Smith я из логИвента
    Some event for 2 я из логИвента
*/