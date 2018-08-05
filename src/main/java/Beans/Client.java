package Beans;

public class Client {

    private String id;
    private String fullName;
    private String greeting;

    public Client(String id, String fullName) {
        //super();
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String gr) {
        this.greeting = gr;
    }

/*    // этот метод заменит toString
    String getFullName() {
        return "Beans.Client{" +
                "id='" + id + '\'' +
                ", name='" + fullName + '\'' +
                '}';
    }*/

}
