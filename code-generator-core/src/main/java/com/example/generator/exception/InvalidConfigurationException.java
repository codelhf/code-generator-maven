package com.example.generator.exception;

import java.util.List;

public class InvalidConfigurationException extends Exception {

    private List<String> errors;

    public InvalidConfigurationException(List<String> errors) {
        super();
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        if (errors != null && errors.size() > 0) {
            return errors.get(0);
        }

        return super.getMessage();
    }
}
