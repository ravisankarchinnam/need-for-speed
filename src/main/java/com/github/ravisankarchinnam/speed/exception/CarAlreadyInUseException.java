package com.github.ravisankarchinnam.speed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception handling Class for Car Already in use scenarios.
 * <p/>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The selected Car is already in use.")
public class CarAlreadyInUseException extends Exception
{

    private static final long serialVersionUID = -1111853904L;


    public CarAlreadyInUseException(String message)
    {
        super(message);
    }

}
