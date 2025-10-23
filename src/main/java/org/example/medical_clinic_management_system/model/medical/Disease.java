package org.example.medical_clinic_management_system.model.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disease")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disease
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "icd_code", length = 10, unique = true)
    private String icdCode;


}
