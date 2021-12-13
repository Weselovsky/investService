package org.exampple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PaperPriceChangedExeption extends RuntimeException {
    public PaperPriceChangedExeption() {
    }

    public PaperPriceChangedExeption(String message) {
        super(message);
    }

    public PaperPriceChangedExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public PaperPriceChangedExeption(Throwable cause) {
        super(cause);
    }

    public PaperPriceChangedExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
