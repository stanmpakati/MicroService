package com.stancloud.employeeservice.repository;

import com.stancloud.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
  private List<Employee> employees = List.of(
    new Employee(1L, 1L, 27,"Stan", "CEO")
  );

  public Employee findById(Long id) {
    return employees.stream()
      .filter(employee -> employee.id().equals(id))
      .findFirst()
      .orElseThrow();
  }

  public List<Employee> findAll() {
    return employees;
  }

  public Employee save(Employee employee) {
    employees.add(employee);
    return employee;
  }

  public List<Employee> findByDepartment(Long departmentId) {
    return employees.stream()
      .filter(employee -> employee.departmentId().equals(departmentId))
      .toList();
  }
}
