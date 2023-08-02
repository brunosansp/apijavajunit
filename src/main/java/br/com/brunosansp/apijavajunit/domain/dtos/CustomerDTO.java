package br.com.brunosansp.apijavajunit.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CustomerDTO {
  
  private Integer id;
  private String name;
  private String email;
  
  @JsonIgnore
  private String password;
  
}
