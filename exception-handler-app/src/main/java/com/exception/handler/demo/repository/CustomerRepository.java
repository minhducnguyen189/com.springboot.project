package com.exception.handler.demo.repository;

import com.exception.handler.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    CustomerEntity findCustomerByEmail(String email);
}
