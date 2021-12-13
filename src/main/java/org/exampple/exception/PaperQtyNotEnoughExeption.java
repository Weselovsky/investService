package org.exampple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PaperQtyNotEnoughExeption extends RuntimeException {
    public PaperQtyNotEnoughExeption() {
    }

    public PaperQtyNotEnoughExeption(String message) {
        super(message);
    }

    public PaperQtyNotEnoughExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public PaperQtyNotEnoughExeption(Throwable cause) {
        super(cause);
    }

    public PaperQtyNotEnoughExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
