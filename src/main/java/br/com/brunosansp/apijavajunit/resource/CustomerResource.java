package br.com.brunosansp.apijavajunit.resource;

import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.services.impl.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {
  
  private final ModelMapper mapper;
  
  private final CustomerServiceImpl customerService;
  
  public CustomerResource(ModelMapper mapper, CustomerServiceImpl customerService) {
    this.mapper = mapper;
    this.customerService = customerService;
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(customerService.findById(id), CustomerDTO.class));
  }
}
