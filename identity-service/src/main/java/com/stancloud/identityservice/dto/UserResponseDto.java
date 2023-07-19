package com.stancloud.identityservice.dto;

import com.stancloud.identityservice.entity.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponseDto {
  private Long id;
  private String email;
  private Set<UserRole> userRoles;
}
