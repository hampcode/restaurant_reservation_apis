package com.hampcode.restaurant_reservation.exception;

public class UserNotAuthenticatedException extends RuntimeException {

  public UserNotAuthenticatedException() {
    super();
  }

  public UserNotAuthenticatedException(String message) {
    super(message);
  }
}
