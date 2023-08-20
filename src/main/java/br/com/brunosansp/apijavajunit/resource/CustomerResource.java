package br.com.brunosansp.apijavajunit.resource;

import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.services.ICustomerService;
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
  
  public static final String ID = "/{id}";
  private final ModelMapper mapper;
  
  private final ICustomerService service;
  
  public CustomerResource(ModelMapper mapper, ICustomerService service) {
    this.mapper = mapper;
    this.service = service;
  }
  
  @GetMapping(value = ID)
  public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(service.findById(id), CustomerDTO.class));
  }
  
  @GetMapping
  public ResponseEntity<List<CustomerDTO>> list() {
    return ResponseEntity.ok().body(
      service.findAll().stream().map(x -> mapper.map(x, CustomerDTO.class)).collect(Collectors.toList())
      );
  }
  
  @PostMapping
  public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO request) {
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest().path(ID).buildAndExpand(service.create(request).getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
  
  @PutMapping(value = ID)
  public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
    customerDTO.setId(id);
    return ResponseEntity.ok().body(mapper.map(service.update(customerDTO), CustomerDTO.class));
  }
  
  @DeleteMapping(value = ID)
  public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
