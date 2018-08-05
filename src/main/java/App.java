import Beans.Client;
import Beans.Event;
import Beans.EventType;
import Loggers.EventLogger;

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

    public static void main(String[] args) {
        // указываем где искать настройки spring
        ConfigurableApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Class says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        // вызвать у бина app метод logEvent
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
        //app.client = new Beans.Client("1", "Jon Smith"); // не будем создавать Клиента
        //app.eventLogger = new Loggers.ConsoleEventLogger(); // и КонсольЭвентЛоггер
        //app.logEvent("Beans.Event for User_1");
    }

    public App(Client client, EventLogger eventLogger, Map<EventType,
                EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;

    }

    // принимает строку и обращается к ЭвентЛоггеру, чтобы он уже ею
    // занимался. Метод replaceAll из библ Джава заменит ИД на ИМЯ
    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

}/*
    Some event for John Smith я из логИвента
    Some event for 2 я из логИвента
*/