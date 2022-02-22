package com.kramar.Market.rest.advice;

import com.kramar.Market.exception.CakeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CakeAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CakeNotFoundException.class)
    @ResponseBody
    public String cakeNotFound() {
        return "Осуждаю";
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(org.hibernate.PessimisticLockException.class)
    public String transactionConflict(org.hibernate.PessimisticLockException ex){
        return "Confict error";
    }
}
