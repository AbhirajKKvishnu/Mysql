package com.mysql.Mysql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mysql.controller.CustomerAPI;
import com.mysql.dto.APIResponseView;
import com.mysql.dto.CustomerDTO;
import com.mysql.entity.Customer;
import com.mysql.repository.CustomerRepository;
import com.mysql.service.CustomerService;

@SpringBootTest
class MysqlApplicationTests {

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerAPI customerAPI;

	@Test
	public void getCustomer() throws Exception {
		
		Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setDateOfBirth(LocalDate.now());
        customer.setEmail("abhi@123");
        customer.setName("name");
		

        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        

	     ResponseEntity<CustomerDTO> response = customerAPI.getCustomer(1);
		 Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getAllCustomer() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setDateOfBirth(LocalDate.now());
		customer.setEmail("abhi@123");
		customer.setName("name");
		List<Customer> custList = new ArrayList<>();
		custList.add(customer);

		Mockito.when(customerRepository.findAll()).thenReturn(custList);

		ResponseEntity<List<CustomerDTO>> response = customerAPI.getallCustomer();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getAllCustomerexception() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setDateOfBirth(LocalDate.now());
		customer.setEmail("abhi@123");
		customer.setName("name");
		List<Customer> custList = new ArrayList<>();
		custList.add(customer);

		Mockito.when(customerRepository.findAll()).thenReturn(null);

		Exception exception = Assertions.assertThrows(Exception.class, () -> {
			customerAPI.getallCustomer();
		});

		Assertions.assertEquals("An error occurred while retrieving customers: ", exception.getMessage());

	}
	@Test
	public void addcustomer() throws Exception {

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(1);
		customer.setDateOfBirth(LocalDate.now());
		customer.setEmail("abhi@123");
		customer.setName("name");

		Mockito.when(customerRepository.findById(null)).thenReturn(null);

		ResponseEntity<APIResponseView> response = customerAPI.addCustomer(customer);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void addcustomerexception() throws Exception {

		CustomerDTO customerdto = new CustomerDTO();
		customerdto.setCustomerId(1);
		customerdto.setDateOfBirth(LocalDate.now());
		customerdto.setEmail("abhi@123");
		customerdto.setName("name");

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setDateOfBirth(LocalDate.now());
		customer.setEmail("abhi@123");
		customer.setName("name");

		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		Exception exception = Assertions.assertThrows(Exception.class, () -> {
			customerAPI.addCustomer(customerdto);
		});
		Assertions.assertEquals("customerId already present", exception.getMessage());

	}
	@Test
	public void update() throws Exception {
		
		Customer customer = new Customer();
      customer.setCustomerId(1);
      customer.setDateOfBirth(LocalDate.now());
      customer.setEmail("abhi@123");
      customer.setName("name");
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setDateOfBirth(LocalDate.now());
		customerDTO.setEmail("hehe@123");

		

        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        

	     ResponseEntity<List<CustomerDTO>> response = customerAPI.updateCustomer(customerDTO);
		 Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void updateexception() throws Exception {
		
		Customer customer = new Customer();
      customer.setCustomerId(1);
      customer.setDateOfBirth(LocalDate.now());
      customer.setEmail("abhi@123");
      customer.setName("name");
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setDateOfBirth(LocalDate.now());
		customerDTO.setEmail("hehe@123");

		

        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        


	     Exception exception = Assertions.assertThrows(Exception.class, () -> {
	         customerAPI.updateCustomer(customerDTO);
	     });

	     Assertions.assertEquals("customerId not present", exception.getMessage());
	}
	@Test
	public void delete() throws Exception {
		
		Customer customer = new Customer();
      customer.setCustomerId(1);
      customer.setDateOfBirth(LocalDate.now());
      customer.setEmail("abhi@123");
      customer.setName("name");
		

        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        

	     ResponseEntity<List<CustomerDTO>> response = customerAPI.deleteCustomer(1);
		 Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void deleteexception() throws Exception {
		
		Customer customer = new Customer();
      customer.setCustomerId(1);
      customer.setDateOfBirth(LocalDate.now());
      customer.setEmail("abhi@123");
      customer.setName("name");
		


	     Exception exception = Assertions.assertThrows(Exception.class, () -> {
        customerAPI.deleteCustomer(1);
    });

    Assertions.assertEquals("customerId not present", exception.getMessage());
}

}
