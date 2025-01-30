package com.turkcell.mini_e_commere_cqrs_hw3.core.exception.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationExceptionResult extends ExceptionResult{
  private List<CustomExceptionResult> errors;

  public ValidationExceptionResult(List<CustomExceptionResult> errors) {
    super("ValidationError");
    this.errors = errors;
  }

  public record CustomExceptionResult(String field, String message) {
  }
}

