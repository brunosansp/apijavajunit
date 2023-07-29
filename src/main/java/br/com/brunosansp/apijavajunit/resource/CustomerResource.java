package br.com.brunosansp.apijavajunit.resource;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.services.impl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {
  
  private final CustomerServiceImpl customerService;
  
  public CustomerResource(CustomerServiceImpl customerService) {
    this.customerService = customerService;
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Customer> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(customerService.findById(id));
  }
}
