package org.example.medical_clinic_management_system.model.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="specialization")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialization
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,nullable = false)
    private String name;

    @Column(length = 100,nullable = true)
    private String description;


}
