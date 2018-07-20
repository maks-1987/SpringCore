import Beans.Client;
import Beans.Event;
import Loggers.ConsoleEventLogger;
import Loggers.EventLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Приложение будет проверять message и подменять на полноеимя + печатать
 * его на консоль.
 */
public class App {

    private Client client;
    private EventLogger eventLogger; // объект интерфейса ЭвентЛоггер

    public static void main(String[] args) {
        // указываем где искать настройки spring
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        // вызвать у бина app метод logEvent
        app.logEvent(event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        //app.client = new Beans.Client("1", "Jon Smith"); // не будем создавать Клиента
        //app.eventLogger = new Loggers.ConsoleEventLogger(); // и КонсольЭвентЛоггер
        //app.logEvent("Beans.Event for User_1");
    }
    public App(Client client, ConsoleEventLogger eventLogger) {
        //super();
        this.client = client;
        this.eventLogger = eventLogger;
    }
    // принимает строку и обращается к ЭвентЛоггеру, чтобы он уже ею
    // занимался. Метод replaceAll из библ Джава заменит ИД на ИМЯ
    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

}/*
    Some event for John Smith я из логИвента
    Some event for 2 я из логИвента
*/