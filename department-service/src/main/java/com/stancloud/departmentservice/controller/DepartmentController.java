package com.stancloud.departmentservice.controller;

import com.stancloud.departmentservice.model.Department;
import com.stancloud.departmentservice.reposotory.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
  @Autowired
  private DepartmentRepository departmentRepository;

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
  public Iterable<Department> findAll() {
    LOGGER.info("Inside find all department of DepartmentController");
    return departmentRepository.findAll();
  }
}
