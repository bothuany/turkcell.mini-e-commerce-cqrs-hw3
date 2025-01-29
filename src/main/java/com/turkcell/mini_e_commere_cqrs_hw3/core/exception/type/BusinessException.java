package com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type;

public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
}
