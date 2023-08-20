package br.com.brunosansp.apijavajunit.resource.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class StandardError {
  
  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private String path;
  
}
