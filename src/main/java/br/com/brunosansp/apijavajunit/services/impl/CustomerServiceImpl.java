package br.com.brunosansp.apijavajunit.services.impl;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.repositories.ICustomerRepository;
import br.com.brunosansp.apijavajunit.services.ICustomerService;
import br.com.brunosansp.apijavajunit.services.exceptions.DataIntegratyViolationException;
import br.com.brunosansp.apijavajunit.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
  
  private final ICustomerRepository repository;
  
  private final ModelMapper mapper;
  
  public CustomerServiceImpl(ICustomerRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public Customer findById(Integer id) {
    Optional<Customer> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }
  
  public List<Customer> findAll() {
    return repository.findAll();
  }
  
  @Override
  public Customer create(CustomerDTO customerDTO) {
    findByEmail(customerDTO);
    return repository.save(mapper.map(customerDTO, Customer.class));
  }
  
  @Override
  public Customer update(CustomerDTO customerDTO) {
    findByEmail(customerDTO);
    return repository.save(mapper.map(customerDTO, Customer.class));
  }
  
  @Override
  public void delete(Integer id) {
    findById(id);
    repository.deleteById(id);
  }
  
  private void findByEmail(CustomerDTO obj) {
    Optional<Customer> user = repository.findByEmail(obj.getEmail());
    if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
      throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
    }
  }
}
