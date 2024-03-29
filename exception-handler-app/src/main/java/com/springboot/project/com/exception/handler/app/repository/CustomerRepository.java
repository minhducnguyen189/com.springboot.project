package com.springboot.project.com.exception.handler.app.repository;

import com.springboot.project.com.exception.handler.app.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    CustomerEntity findCustomerByEmail(String email);
}
