package com.stancloud.authenticationservice.user

import org.springframework.security.core.GrantedAuthority

enum class UserRole: GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    override fun getAuthority(): String = name
}
