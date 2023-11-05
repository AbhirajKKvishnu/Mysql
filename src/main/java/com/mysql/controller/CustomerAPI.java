package com.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.dto.APIResponseView;
import com.mysql.dto.CustomerDTO;
import com.mysql.service.CustomerService;

@RestController
@RequestMapping(value = "/Check")
public class CustomerAPI {
	
	@Autowired
	CustomerService customerservice;
	
	@GetMapping(value="/customers/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customerId) throws Exception{
		CustomerDTO customer =customerservice.getCustomer(customerId);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	@GetMapping(value="/customers")
	public ResponseEntity<List<CustomerDTO>> getallCustomer() throws Exception{
		List<CustomerDTO> customer =customerservice.getAllCustomers();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	@PostMapping(value="/customers")
	public ResponseEntity<APIResponseView> addCustomer(@RequestBody CustomerDTO customerDto) throws Exception{
		
		
		Integer id=customerservice.addCustomer(customerDto);
		System.out.println(id +"added succesfully");
		APIResponseView responseView =new APIResponseView();
		responseView.setStatusCode("200");
		responseView.setStatusDescription("SUCCESS");
		responseView.setCustomerDTO(customerDto);
		return new ResponseEntity<>(responseView,HttpStatus.OK);
	}
	@PatchMapping(value="/customers")
	public ResponseEntity<List<CustomerDTO>> updateCustomer(@RequestBody CustomerDTO customerDto) throws Exception{
		
		
		Integer id=customerservice.updateCustomer(customerDto);
		System.out.println(id +"updated succesfully");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping(value="/customers")
	public ResponseEntity<List<CustomerDTO>> deleteCustomer(@PathVariable Integer customerId) throws Exception{
		
		
		Integer id=customerservice.deleteCustomer(customerId);
		System.out.println(id +"deleted succesfully");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
