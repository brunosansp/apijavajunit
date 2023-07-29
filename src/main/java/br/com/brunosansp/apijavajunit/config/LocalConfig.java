package br.com.brunosansp.apijavajunit.config;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.repositories.ICustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
  
  private final ICustomerRepository customerRepository;
  
  public LocalConfig(ICustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  
  @Bean
  public void startDB() {
    Customer customer1 = new Customer(null, "Bruno", "bruno@mail.com", "123");
    Customer customer2 = new Customer(null, "Shogun", "shogun@mail.com", "123");
    
    customerRepository.saveAll(List.of(customer1, customer2));
  }
}
