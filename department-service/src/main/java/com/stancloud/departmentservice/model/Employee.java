package com.stancloud.departmentservice.model;

public record Employee(
  Long id,
  Long departmentId,
  Integer age,
  String name,
  String position
) {}
