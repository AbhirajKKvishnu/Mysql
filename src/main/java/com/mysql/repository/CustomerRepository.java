package com.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mysql.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
