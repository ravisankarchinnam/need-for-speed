package com.github.ravisankarchinnam.speed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception handling Class for Driver is Offline.
 * <p/>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The selected driver is offline.")
public class DriverOfflineException extends Exception
{

    private static final long serialVersionUID = 1222701336L;


    public DriverOfflineException(String message)
    {
        super(message);
    }

}
