package sample;

/**
 * Created by Victor on 2017-04-12.
 */
public class Employee {
    private final static Employee instance = new Employee ("", "");
    public static Employee getInstance() {
        return instance;
    }
    private String username;
    private String password;

    public Employee(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return username + " | " + password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
