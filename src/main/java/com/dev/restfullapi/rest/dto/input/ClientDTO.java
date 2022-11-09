package com.dev.restfullapi.rest.dto.input;

import javax.validation.constraints.NotEmpty;

public class ClientDTO {

    @NotEmpty(message = "field [name] is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
