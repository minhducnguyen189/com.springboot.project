package com.springboot.project.basicAuth.app.entity;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Email
    private String username;

    @Column(nullable = false)
    @Length(min = 6)
    private String password;
    private LocalDateTime createdDate;

}
