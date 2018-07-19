import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Приложение будет проверять message и подменять на полноеимя + печатать
 * его на консоль.
 */
public class App {

    Client client;
    ConsoleEventLogger eventLogger;

    App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        // указываем где искать настройки spring
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        // вызвать у бина app метод logEvent
        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");

        //app.client = new Client("1", "Jon Smith"); // не будем создавать Клиента
        //app.eventLogger = new ConsoleEventLogger(); // и КонсольЭвентЛоггер
        //app.logEvent("Event for User_1");
    }
    // принимает строку и обращается к ЭвентЛоггеру, чтобы он уже ею
    // занимался. Метод replaceAll из библ Джава заменит ИД на ИМЯ
    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        //String message = client.getFullName();
        eventLogger.logEvent(message);
    }

}/*
    Some event for Client{id='1', name='John Smith'} я из логИвента
    Some event for 2 я из логИвента
*/