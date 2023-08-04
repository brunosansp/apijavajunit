package br.com.brunosansp.apijavajunit.services;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;

import java.util.List;

public interface ICustomerService {
  
  Customer findById(Integer id);
  List<Customer> findAll();
  Customer create(CustomerDTO customerDTO);
  Customer update(CustomerDTO customerDTO);
}
