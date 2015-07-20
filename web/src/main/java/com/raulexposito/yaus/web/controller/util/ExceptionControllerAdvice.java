package com.raulexposito.yaus.web.controller.util;

import com.raulexposito.yaus.service.exception.InvalidURLException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(InvalidURLException.class)
    public void invalidURLException(Exception exception, HttpServletResponse response) throws IOException{
        int status = HttpStatus.BAD_REQUEST.value();
        response.setStatus(status);
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
}