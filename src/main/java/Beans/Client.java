package Beans;

public class Client {

    private String id;
    private String fullName;

    public Client(String id, String name) {
        //super();
        this.id = id;
        this.fullName = name;
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

/*    // этот метод заменит toString
    String getFullName() {
        return "Beans.Client{" +
                "id='" + id + '\'' +
                ", name='" + fullName + '\'' +
                '}';
    }*/

}
