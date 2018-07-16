/**
 * Приложение будет проверять message и подменять на полноеимя + печатать
 * его на консоль.
 */
public class App {

    Client client;
    ConsoleEventLogger eventLogger;

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client("1", "Jon Smith");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Event for User_1");
    }
    // принимает строку и обращается к ЭвентЛоггеру, чтобы он уже ею
    // занимался. Метод replaceAll из библ Джава заменит ИД на ИМЯ
    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }

}
