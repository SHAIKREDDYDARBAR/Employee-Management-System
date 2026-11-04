package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @PostMapping public ResponseEntity<Employee> add(@Valid @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(employee));
    }
    @GetMapping public List<Employee> all() { return service.all(); }
    @GetMapping("/{id}") public Employee one(@PathVariable Long id) { return service.one(id); }
    @PutMapping("/{id}") public Employee update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return service.update(id, employee);
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
    @GetMapping("/department/{department}") public List<Employee> department(@PathVariable String department) {
        return service.byDepartment(department);
    }
}
