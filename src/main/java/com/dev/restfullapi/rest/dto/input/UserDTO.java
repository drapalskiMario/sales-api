package com.dev.restfullapi.rest.dto.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotEmpty(message = "field [userName] is required")
    private String userName;

    @NotEmpty(message = "field [password] is required")
    private String password;

    @NotNull(message = "field [admin] is required")
    private boolean admin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
