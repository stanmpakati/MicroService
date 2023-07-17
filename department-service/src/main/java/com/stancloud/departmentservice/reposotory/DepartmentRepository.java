package com.stancloud.departmentservice.reposotory;

import com.stancloud.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {
  private List<Department> departments = List.of(
    new Department(1L, "IT"),
    new Department(2L, "MARKETING"),
    new Department(3L, "FINANCE")
  );

  public Department findById(Long id) {
    return departments.stream()
      .filter(department -> department.getId().equals(id))
      .findFirst()
      .orElseThrow();
  }

  public List<Department> findAll() {
    return departments;
  }

  public Department save(Department department) {
    departments.add(department);
    return department;
  }
}
