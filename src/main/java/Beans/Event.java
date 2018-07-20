package Beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Это будет Bean-объект, который будет передан Spring
 */
public class Event {
    // AtomicInteger переменная для автогенерации "id" События, начиная с нуля
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    private int id;
    private String msg;
    private Date date;

    private DateFormat dateFormat; // библиотечный класс Джава

    public Event(Date d, DateFormat df) {
        this.id = AUTO_ID.getAndIncrement();
        this.date = d;
        this.dateFormat = df;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Beans.Event {" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
