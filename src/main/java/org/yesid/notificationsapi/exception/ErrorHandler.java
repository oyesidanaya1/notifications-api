package org.yesid.notificationsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CategoryException.class)
  public String handleBadRequest(Exception e) {
    return e.getMessage();
  }
}
