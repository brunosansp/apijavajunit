package br.com.brunosansp.apijavajunit.services;

import br.com.brunosansp.apijavajunit.domain.Customer;

import java.util.List;

public interface ICustomerService {
  
  Customer findById(Integer id);
  
  List<Customer> findAll();
}
