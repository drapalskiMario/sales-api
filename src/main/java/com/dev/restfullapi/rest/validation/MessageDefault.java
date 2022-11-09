package com.dev.restfullapi.rest.validation;

public final class MessageDefault {

    private static final String MESSAGE = "field [%s] is required";

    public static final String generate(String field) {
        return String.format(MESSAGE, field);
    }
}
