package br.com.brunosansp.apijavajunit.services.impl;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.repositories.ICustomerRepository;
import br.com.brunosansp.apijavajunit.services.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
  
  private final ICustomerRepository customerRepository;
  
  public CustomerServiceImpl(ICustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  
  @Override
  public Customer findById(Integer id) {
    Optional<Customer> obj = customerRepository.findById(id);
    return obj.orElse(null);
  }
}
