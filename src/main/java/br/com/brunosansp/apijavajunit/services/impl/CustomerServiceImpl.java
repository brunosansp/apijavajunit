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
  
  private final ICustomerRepository customerRepository;
  
  private final ModelMapper mapper;
  
  public CustomerServiceImpl(ICustomerRepository customerRepository, ModelMapper mapper) {
    this.customerRepository = customerRepository;
    this.mapper = mapper;
  }
  
  @Override
  public Customer findById(Integer id) {
    Optional<Customer> obj = customerRepository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Customer não encontrado."));
  }
  
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }
  
  @Override
  public Customer create(CustomerDTO customerDTO) {
    findByEmail(customerDTO);
    return customerRepository.save(mapper.map(customerDTO, Customer.class));
  }
  
  @Override
  public Customer update(CustomerDTO customerDTO) {
    findByEmail(customerDTO);
    return customerRepository.save(mapper.map(customerDTO, Customer.class));
  }
  
  private void findByEmail(CustomerDTO customerDTO) {
    Optional<Customer> customer = customerRepository.findByEmail(customerDTO.getEmail());
    if(customer.isPresent() && !customer.get().getId().equals(customerDTO.getId()))
      throw new DataIntegratyViolationException("E-mail já cadastrado no sistema.");
  }
}
