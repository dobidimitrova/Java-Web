package bg.softuni.exam.models;

import jakarta.validation.constraints.Size;

public class UserLoginModel {

    @Size(min = 3,max = 20,message = "Wrong username!")
    private String username;

    @Size(min = 3,max = 20,message = "Wrong password!")
    private String password;

    public UserLoginModel() {
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
