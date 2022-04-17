package com.springboot.project.swagger.app.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;
    private String fullName;
    @Email
    @Column(unique = true)
    private String email;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dob;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        this.createdAt = offsetDateTime;
        this.updatedAt = offsetDateTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
