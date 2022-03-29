package Token;

public class LoginToken {

    int id;
    String name;
    int role; // 1 - admin, 2 - customer, 3 - airline

    public LoginToken() {}

    public LoginToken(int id, String name, int role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
