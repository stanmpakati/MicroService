package com.stancloud.authenticationservice.model

import com.stancloud.authenticationservice.user.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    @Column(unique = true)
    val email: String,
    private val password: String,
    private val isEnabled: Boolean?,
    private val isCredentialsNonExpired: Boolean?,
    private val isAccountNonExpired: Boolean?,
    private val isAccountNonLocked: Boolean?,
    @ElementCollection(targetClass = UserRole::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val roles: Set<UserRole>,
    @CreationTimestamp
    val createdAt: LocalDateTime,
    @UpdateTimestamp
    val updatedAt: LocalDateTime,
) : UserDetails {

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        roles: Set<UserRole>,
    ) : this(
        null,
        firstName,
        lastName,
        email,
        password,
        true,
        true,
        true,
        true,
        roles,
        LocalDateTime.now(),
        LocalDateTime.now(),
    )

    override fun getUsername(): String = email

    override fun getAuthorities(): Set<GrantedAuthority> {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun toString(): String {
        return "User(id=$id, firstName=$firstName, lastName=$lastName, email=$email, password=$password, isEnabled=$isEnabled, isCredentialsNonExpired=$isCredentialsNonExpired, isAccountNonExpired=$isAccountNonExpired, isAccountNonLocked=$isAccountNonLocked, authorities=$authorities, role=$roles)"
    }
}

//val userBuilder = UserBuilder()
//userBuilder.email("johndoe@example.com")
//userBuilder.password("password123")
//userBuilder.role(com.stancloud.authenticationservice.user.UserRole.ADMIN)
//val user = userBuilder.build()

