package com.stancloud.departmentservice.controller;

import com.stancloud.departmentservice.client.EmployeeClient;
import com.stancloud.departmentservice.model.Department;
import com.stancloud.departmentservice.reposotory.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private EmployeeClient employeeClient;

  @PostMapping()
  public Department save(@RequestBody Department department) {
    LOGGER.info("Inside save department of DepartmentController {}", department);
    return departmentRepository.save(department);
  }

  @GetMapping("/{id}")
  public Department findById(@PathVariable("id") Long id) {
    LOGGER.info("Inside find by id department of DepartmentController {}", id);
    return departmentRepository.findById(id);
  }

  @GetMapping()
  public List<Department> findAll() {
    LOGGER.info("Inside find all department of DepartmentController");
    return departmentRepository.findAll();
  }

  @GetMapping("/with-employees")
  public List<Department> findAllWithEmployees() {
    LOGGER.info("Find all with employees");
    List<Department> departments = departmentRepository.findAll();
    departments.forEach(d ->
        d.setEmployees(employeeClient.findByDepartment(d.getId())));

    return departments;
  }
}
