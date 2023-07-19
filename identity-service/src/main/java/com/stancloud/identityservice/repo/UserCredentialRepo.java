package com.stancloud.identityservice.repo;

import com.stancloud.identityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepo extends JpaRepository<UserCredential, Long> {
  Optional<UserCredential> findByEmail(String email);

}
