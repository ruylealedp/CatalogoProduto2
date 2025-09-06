package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

public class FieldError {
    public String field;
    public String message;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
