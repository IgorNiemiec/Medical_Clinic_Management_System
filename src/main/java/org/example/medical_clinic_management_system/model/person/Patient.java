package org.example.medical_clinic_management_system.model.person;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    private User user;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 11, nullable = false, unique = true)
    private String pesel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public enum Gender
    {
        MALE,FEMALE
    }



}
