package com.fullcycle.CatalogoVideo.domain.exception;

public class NotNullException extends DomainException {

    public NotNullException() {
        super();
    }

    public NotNullException(final String property) {
        super(property + " can not be null");
    }
}
