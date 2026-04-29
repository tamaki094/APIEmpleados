package com.edri.api_empleado.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String maternalLastName;

    @Column(nullable = false)
    private String paternalLastName;

    private Integer age;

    private String sex;

    @Column(nullable = false)
    private LocalDate birthDate;

    private String position;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private Boolean isActive = true;
}
