package br.com.brunosansp.apijavajunit.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {
  
  private Integer id;
  private String name;
  private String email;
  
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  
}
