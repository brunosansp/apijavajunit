package br.com.brunosansp.apijavajunit.services;

import br.com.brunosansp.apijavajunit.domain.Customer;

public interface ICustomerService {
  
  Customer findById(Integer id);
}
