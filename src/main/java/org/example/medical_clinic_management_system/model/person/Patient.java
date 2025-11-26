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

    // Relacja OneToOne z User - dane osobowe pacjenta
    // Używamy @MapsId, aby id encji Patient było jednocześnie id encji User
    // W naszym przypadku, zgodnie ze schematem, to jest prosta relacja OneToOne
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(length = 255)
    private String address;

    @Column(length = 12)
    private String phoneNumber;


    @Column(nullable = false, unique = true, length = 11)
    private String pesel;

    @Column(nullable = false, length = 10)
    private Gender gender;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", nullable = false)
    private Employee registeredBy;

    public enum Gender
    {
        MALE,
        FEMALE
    }

}

