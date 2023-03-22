package com.springboot.project.json.schema.validator.repository;

import com.springboot.project.json.schema.validator.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

}
