package com.fullcycle.CatalogoVideo.domain.exception;

public class NotBlankException extends DomainException {

    public NotBlankException() {
        super();
    }

    public NotBlankException(final String property) {
        super(property + " can not be blank");
    }
}
