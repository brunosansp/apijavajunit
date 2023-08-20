package br.com.brunosansp.apijavajunit.services.impl;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.repositories.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {
  
  public static final Integer ID = 1;
  public static final String NAME = "teste";
  public static final String EMAIL = "teste@mail.com";
  public static final String PASSWORD = "123";
  
  @InjectMocks
  private CustomerServiceImpl service;
  
  @Mock
  private ICustomerRepository repository;
  
  @Mock
  private ModelMapper mapper;
  
  private Customer customer;
  private CustomerDTO customerDTO;
  private Optional<Customer> optionalCustomer;
  
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startCustomer();
  }
  
  @Test
  void whenFindByIdThenReturnAnCustomerInstance() {
    when(repository.findById(anyInt())).thenReturn(optionalCustomer);
    
    Customer response = service.findById(ID);
    
    assertNotNull(response);
    
    assertEquals(Customer.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
  }
  

  @Test
  void findAll() {
  }

  @Test
  void create() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }
  
  private void startCustomer() {
    customer = new Customer(ID, NAME, EMAIL, PASSWORD);
    customerDTO = new CustomerDTO(ID, NAME, EMAIL, PASSWORD);
    optionalCustomer = Optional.of(new Customer(ID, NAME, EMAIL, PASSWORD));
  }
}