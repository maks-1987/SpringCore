/**
 * Приложение будет проверять message и подменять на полноеимя + печатать
 * его на консоль.
 */
public class App {

    Client client;
    ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {

        //app.client = new Client("1", "Jon Smith"); // не будем создавать Клиента
        //app.eventLogger = new ConsoleEventLogger(); // и КонсольЭвентЛоггер
        //app.logEvent("Event for User_1");
    }
    // принимает строку и обращается к ЭвентЛоггеру, чтобы он уже ею
    // занимался. Метод replaceAll из библ Джава заменит ИД на ИМЯ
    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

}
