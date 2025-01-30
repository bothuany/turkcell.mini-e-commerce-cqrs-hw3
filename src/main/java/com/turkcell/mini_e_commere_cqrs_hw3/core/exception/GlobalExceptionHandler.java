package com.turkcell.mini_e_commere_cqrs_hw3.core.exception;

import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.result.BusinessExceptionResult;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.result.ValidationExceptionResult;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.BusinessException;
import com.turkcell.mini_e_commere_cqrs_hw3.core.exception.type.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
  @ExceptionHandler({BusinessException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BusinessExceptionResult handleRuntimeException(BusinessException e) {
    return new BusinessExceptionResult(e.getMessage());
  }

  @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionResult handleMethodArgumentNotValidException(ValidationException e) {
        return new ValidationExceptionResult(
                e.getViolations()
                        .stream()
                        .map(v ->
                                new ValidationExceptionResult.CustomExceptionResult(
                                        v.getPropertyPath().toString(), v.getMessage()
                                )
                        ).toList()
        );
    }
}
