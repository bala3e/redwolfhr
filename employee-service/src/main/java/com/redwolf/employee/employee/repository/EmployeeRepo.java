package com.redwolf.employee.employee.repository;


import com.redwolf.employee.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer > {
    Optional<Employee> findByName(String username);
}
