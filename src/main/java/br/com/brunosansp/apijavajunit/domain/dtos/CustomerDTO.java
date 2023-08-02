package br.com.brunosansp.apijavajunit.domain.dtos;

import lombok.Data;

@Data
public class CustomerDTO {
  
  private Integer id;
  private String name;
  private String email;
}
