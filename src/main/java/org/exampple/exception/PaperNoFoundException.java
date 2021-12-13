package org.exampple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaperNoFoundException extends RuntimeException {
    public PaperNoFoundException() {
    }

    public PaperNoFoundException(String message) {
        super(message);
    }

    public PaperNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaperNoFoundException(Throwable cause) {
        super(cause);
    }

    public PaperNoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
