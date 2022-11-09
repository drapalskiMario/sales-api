package com.dev.restfullapi.rest.dto.input;

import javax.validation.constraints.NotEmpty;

public class UpdateStatusOrderDTO {

    @NotEmpty(message = "field [newStatus] is required")
    private String newStatus;

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
