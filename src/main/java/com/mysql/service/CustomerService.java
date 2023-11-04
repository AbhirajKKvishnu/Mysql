package com.mysql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.dto.CustomerDTO;
import com.mysql.entity.Customer;
import com.mysql.repository.CustomerRepository;

@Component
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDTO getCustomer(Integer customerId) throws Exception {
		Optional<Customer> optional = customerRepository.findById(customerId);

		Customer customer = optional.orElseThrow(() -> new Exception("DB Exception"));

		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(customer.getCustomerId());
		dto.setName(customer.getName());
		dto.setDateOfBirth(customer.getDateOfBirth());
		dto.setEmail(customer.getEmail());
		return dto;
	}

	public List<CustomerDTO> getAllCustomers() throws Exception {
		try {
			Iterable<Customer> customers = customerRepository.findAll();
			List<CustomerDTO> customerDTOList = new ArrayList<>();

			for (Customer customer : customers) {
				CustomerDTO dto = new CustomerDTO();
				dto.setCustomerId(customer.getCustomerId());
				dto.setName(customer.getName());
				dto.setDateOfBirth(customer.getDateOfBirth());
				dto.setEmail(customer.getEmail());
				customerDTOList.add(dto);
			}

			return customerDTOList;
		} catch (Exception e) {
			throw new Exception("An error occurred while retrieving customers: ");
		}
	}

	public Integer addCustomer(CustomerDTO customerDto) throws Exception {
		Optional<Customer> optional = customerRepository.findById(customerDto.getCustomerId());
		System.out.println("aaaaaaaaaaaaaaaaaaa" + optional);

		if (optional.isPresent()) {
			throw new Exception("customerId already present");
		} else {
			Customer customer = new Customer();
			customer.setCustomerId(customerDto.getCustomerId());
			customer.setDateOfBirth(customerDto.getDateOfBirth());
			customer.setEmail(customerDto.getEmail());
			customer.setName(customerDto.getName());
			customerRepository.save(customer);

		}

		return customerDto.getCustomerId();

	}

	public Integer updateCustomer(CustomerDTO customerDto) throws Exception {
		Optional<Customer> optional = customerRepository.findById(customerDto.getCustomerId());
		System.out.println("aaaaaaaaaaaaaaaaaaa" + optional);

		if (optional.isPresent()) {
			Customer customer = optional.get();
			customer.setDateOfBirth(customerDto.getDateOfBirth());
			customer.setEmail(customerDto.getEmail());
			customerRepository.save(customer);
		} else {

			throw new Exception("customerId not present");

		}

		return customerDto.getCustomerId();

	}

	public Integer deleteCustomer(Integer customerId) throws Exception {
		Optional<Customer> optional = customerRepository.findById(customerId);
		System.out.println("aaaaaaaaaaaaaaaaaaa" + optional);

		if (optional.isPresent()) {
			Customer customer = optional.get();
			customerRepository.delete(customer);
		} else {
			throw new Exception("customerId not present");
		}
		return customerId;
	}

}
