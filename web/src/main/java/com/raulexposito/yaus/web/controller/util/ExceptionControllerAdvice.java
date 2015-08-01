package com.raulexposito.yaus.web.controller.util;

import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
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
    public void invalidURLException(Exception exception, HttpServletResponse response) throws IOException {
        writeMessageToOutputstreamWithStatus(HttpStatus.BAD_REQUEST, exception, response);
    }

    @ExceptionHandler(HashNotFoundException.class)
    public void hashNotFoundException(Exception exception, HttpServletResponse response) throws IOException {
        writeMessageToOutputstreamWithStatus(HttpStatus.NOT_FOUND, exception, response);
    }

    private void writeMessageToOutputstreamWithStatus (HttpStatus status, Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(status.value());
        IOUtils.write(exception.getMessage(), response.getOutputStream());
    }
}