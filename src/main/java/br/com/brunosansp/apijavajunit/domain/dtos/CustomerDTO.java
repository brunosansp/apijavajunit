package br.com.brunosansp.apijavajunit.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
  
  private Integer id;
  private String name;
  private String email;
  
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
}
