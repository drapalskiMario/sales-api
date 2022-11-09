package com.dev.restfullapi.rest.dto.input;

import javax.validation.constraints.NotEmpty;

public class CredentialDTO {

    @NotEmpty(message = "field [username] is required")
    private String userName;

    @NotEmpty(message = "field [password] is required")
    private String password;

    public CredentialDTO() {
    }

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
}
