package br.com.brunosansp.apijavajunit.services.impl;

import br.com.brunosansp.apijavajunit.domain.Customer;
import br.com.brunosansp.apijavajunit.domain.dtos.CustomerDTO;
import br.com.brunosansp.apijavajunit.repositories.ICustomerRepository;
import br.com.brunosansp.apijavajunit.services.exceptions.DataIntegratyViolationException;
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
  public static final int INDEX_ZERO = 0;
  public static final String EMAIL_JA_CADASTRADO = "E-mail já cadastrado no sistema";
  public static final String OBJECT_NOT_FOUND = "Objeto não encontrado.";
  
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
    service = new CustomerServiceImpl(repository, mapper);
    startCustomer();
  }
  
  @Test
  void contextLoads() throws Exception {
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
    assertEquals(Customer.class, response.get(INDEX_ZERO).getClass());
    
    assertEquals(ID, response.get(INDEX_ZERO).getId());
    assertEquals(NAME, response.get(INDEX_ZERO).getName());
    assertEquals(EMAIL, response.get(INDEX_ZERO).getEmail());
    assertEquals(PASSWORD, response.get(INDEX_ZERO).getPassword());
  }
  
  @Test
  void whenCreateThenReturnSuccess() {
    when(repository.save(any())).thenReturn(customer);
    
    Customer response = service.create(customerDTO);
    
    assertNotNull(response);
    assertEquals(Customer.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
    assertEquals(PASSWORD, response.getPassword());
  }
  
  @Test
  void whenCreateThenReturnAnDataIntegrityViolationException() {
    when(repository.findByEmail(anyString())).thenReturn(optionalCustomer);
    
    try {
      optionalCustomer.ifPresent(value -> value.setId(9));
      service.create(customerDTO);
    } catch (Exception ex) {
      assertEquals(DataIntegratyViolationException.class, ex.getClass());
      assertEquals(EMAIL_JA_CADASTRADO, ex.getMessage());
    }
  }

  @Test
  void whenUpdateThenReturnSuccess() {
    when(repository.save(any())).thenReturn(customer);
    
    Customer response = service.update(customerDTO);
    
    assertNotNull(response);
    assertEquals(Customer.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
    assertEquals(PASSWORD, response.getPassword());
  }
  
  @Test
  void whenUpdateThenReturnAnDataIntegrityViolationException() {
    when(repository.findByEmail(anyString())).thenReturn(optionalCustomer);
    
    try {
      optionalCustomer.ifPresent(value -> value.setId(2));
      service.create(customerDTO);
    } catch (Exception ex) {
      assertEquals(DataIntegratyViolationException.class, ex.getClass());
      assertEquals(EMAIL_JA_CADASTRADO, ex.getMessage());
    }
  }

  @Test
  void deleteWithSuccess() {
    when(repository.findById(anyInt())).thenReturn(optionalCustomer);
    doNothing().when(repository).deleteById(anyInt());
    service.delete(ID);
    verify(repository, times(1)).deleteById(anyInt());
  }
  
  @Test
  void deleteWithObjectNotFoundException() {
    when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
    
    try {
      service.delete(ID);
    } catch (Exception ex) {
      assertEquals(ObjectNotFoundException.class, ex.getClass());
      assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
    }
  }
  
  private void startCustomer() {
    customer = new Customer(ID, NAME, EMAIL, PASSWORD);
    customerDTO = new CustomerDTO(ID, NAME, EMAIL, PASSWORD);
    optionalCustomer = Optional.of(new Customer(ID, NAME, EMAIL, PASSWORD));
  }
}