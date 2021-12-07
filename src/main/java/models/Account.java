package models;

import java.util.Date;

public class Account {
    public String firstName;
    public String name;
    public String email;
    public String password;
    public Date birthday;

    public Account(String firstName, String name, String email, String password, Date birthday) {
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
}
