package com.reactive.example.reactivemongo.repository;

import com.reactive.example.reactivemongo.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}
