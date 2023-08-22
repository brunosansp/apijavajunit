package br.com.brunosansp.apijavajunit.services.impl;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.repositories.ICustomerRepository;
import br.com.brunosansp.apijavajunit.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Testing the CustomerServiceImpl class")
@SpringBootTest
class CustomerServiceImplTest {
  
  public static final Integer ID = 1;
  public static final String NAME = "teste";
  public static final String EMAIL = "teste@mail.com";
  public static final String PASSWORD = "123";
  public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
  public static final int INDEX = 0;
  
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
    MockitoAnnotations.initMocks(this);
    startCustomer();
  }
  
  @Test
  public void contextLoads() throws Exception {
    assertThat(service).isNotNull();
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
  void whenFindByIdThenReturnAnObjectNotFoundException() {
    when(repository.findById(anyInt()))
      .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
    
    try {
      service.findById(ID);
    } catch (Exception ex) {
      assertEquals(ObjectNotFoundException.class, ex.getClass());
      assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
    }
  }

  @Test
  void whenFindAllThenReturnAnListOfCustomers() {
    when(repository.findAll()).thenReturn(List.of(customer));
    List<Customer> response = service.findAll();
    
    assertNotNull(response);
    assertEquals(1, response.size());
    assertEquals(Customer.class, response.get(INDEX).getClass());
    
    assertEquals(ID, response.get(INDEX).getId());
    assertEquals(NAME, response.get(INDEX).getName());
    assertEquals(EMAIL, response.get(INDEX).getEmail());
    assertEquals(PASSWORD, response.get(INDEX).getPassword());
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