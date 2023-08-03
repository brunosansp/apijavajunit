package br.com.brunosansp.apijavajunit.repositories;

import br.com.brunosansp.apijavajunit.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
  Optional<Customer> findByEmail(String email);
}
