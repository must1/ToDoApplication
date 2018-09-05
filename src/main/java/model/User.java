package model;

public class User {
    private String name;
    private String password;
    private int ID;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
