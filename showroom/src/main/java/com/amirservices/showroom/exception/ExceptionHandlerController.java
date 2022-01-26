package com.amirservices.showroom.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class ExceptionHandlerController implements ErrorController {

    @RequestMapping(value = "/error")
    public void handleError() throws HttpClientErrorException{
        // Any pre-processing before
        HttpClientErrorException clientErrorException = new HttpClientErrorException(HttpStatus.NOT_FOUND);
        throw clientErrorException;
    }




}
