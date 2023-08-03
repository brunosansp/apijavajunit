package br.com.brunosansp.apijavajunit.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException {
  
  public DataIntegratyViolationException(String message) {
    super(message);
  }
}
