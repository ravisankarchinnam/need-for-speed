package com.github.ravisankarchinnam.speed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class ConstraintsViolationException extends Exception
{

    static final long serialVersionUID = -3387516993224229948L;


    public ConstraintsViolationException(String message)
    {
        super(message);
    }


    public ConstraintsViolationException(Exception exception)
    {
        super(exception);
    }

}
