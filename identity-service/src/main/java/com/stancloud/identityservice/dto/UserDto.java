package com.stancloud.identityservice.dto;

import com.stancloud.identityservice.entity.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
  private String email;
  private String password;
  private Set<UserRole> userRoles;
}
