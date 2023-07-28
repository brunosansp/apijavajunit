package br.com.brunosansp.apijavajunit.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  
  @Column(unique = true)
  private String email;
  private String password;
}
