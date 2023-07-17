package com.stancloud.employeeservice.controller;


import com.stancloud.employeeservice.model.Employee;
import com.stancloud.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
  @Autowired
  private EmployeeRepository employeeRepository;

  @PostMapping()
  public Employee save(@RequestBody Employee employee) {
    LOGGER.info("Inside save employee of EmployeeController {}", employee);
    return employeeRepository.save(employee);
  }

  @GetMapping("/{id}")
  public Employee findById(@PathVariable("id") Long id) {
    LOGGER.info("Inside find by id employee of EmployeeController {}", id);
    return employeeRepository.findById(id);
  }

  @GetMapping()
  public Iterable<Employee> findAll() {
    LOGGER.info("Inside find all employee of EmployeeController");
    return employeeRepository.findAll();
  }

  @GetMapping("/department/{departmentId}")
  public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
    LOGGER.info("Inside find by department employee of EmployeeController {}", departmentId);
    return employeeRepository.findByDepartment(departmentId);
  }
}
