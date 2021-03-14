package com.orbit.portailAgentStockPR.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String message ;
    //private final Throwable throwable ;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestump ;

    public ApiException(String message/*, Throwable throwable*/, HttpStatus httpStatus, ZonedDateTime timestump) {
        this.message = message;
       // this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestump = timestump;
    }

    public String getMessage() {
        return message;
    }

   /* public Throwable getThrowable() {
        return throwable;
    }*/

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestump() {
        return timestump;
    }
}
