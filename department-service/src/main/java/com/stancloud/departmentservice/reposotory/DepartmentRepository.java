package com.stancloud.departmentservice.reposotory;

import com.stancloud.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {
  private List<Department> departments = List.of(
    new Department(1L, "Stancloud"),
    new Department(2L, "Stancloud2"),
    new Department(3L, "Stancloud3")
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
