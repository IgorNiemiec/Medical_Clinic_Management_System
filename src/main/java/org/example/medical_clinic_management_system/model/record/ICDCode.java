package org.example.medical_clinic_management_system.model.record;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "icd_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ICDCode
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false, length = 255)
    private String namePl;

    @Column(length = 255)
    private String nameEn;


}
