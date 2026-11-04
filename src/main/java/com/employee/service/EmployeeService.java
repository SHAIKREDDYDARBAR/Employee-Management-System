package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }

    public Employee add(Employee employee) { employee.setId(null); return repository.save(employee); }
    public List<Employee> all() { return repository.findAll(); }
    public Employee one(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found: " + id));
    }
    public Employee update(Long id, Employee input) {
        Employee old = one(id);
        old.setName(input.getName()); old.setEmail(input.getEmail());
        old.setDepartment(input.getDepartment()); old.setSalary(input.getSalary());
        return repository.save(old);
    }
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new NoSuchElementException("Employee not found: " + id);
        repository.deleteById(id);
    }
    public List<Employee> byDepartment(String department) {
        return repository.findByDepartmentIgnoreCase(department);
    }
}
