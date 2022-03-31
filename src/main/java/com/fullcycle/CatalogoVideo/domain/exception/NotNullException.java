package com.fullcycle.CatalogoVideo.domain.exception;

public class NotNullException extends DomainException {

    public NotNullException() {
        super();
    }

    public NotNullException(String message) {
        super(message);
    }
}
