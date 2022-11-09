package com.dev.restfullapi.exception.handle;

import java.util.Arrays;
import java.util.List;

public class HandleException {

    private List<String> errors;

    public HandleException(String message) {
        this.errors = Arrays.asList(message);
    }

    public HandleException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
