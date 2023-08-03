package br.com.brunosansp.apijavajunit.resource;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.services.impl.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
  
  @GetMapping
  public ResponseEntity<List<CustomerDTO>> list() {
    return ResponseEntity.ok().body(
      customerService.findAll().stream().map(x -> mapper.map(x, CustomerDTO.class)).collect(Collectors.toList())
      );
  }
  
  @PostMapping
  public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO request) {
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest().path("/{id}").buildAndExpand(customerService.create(request).getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
}
