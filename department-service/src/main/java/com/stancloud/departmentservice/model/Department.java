package com.stancloud.departmentservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {
  private Long id;
  private String name;
  private List<Employee> employees = List.of();

  public Department(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
