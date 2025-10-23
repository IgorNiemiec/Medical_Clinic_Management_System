package org.example.medical_clinic_management_system.model.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medical_equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalEquipment
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 255, nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Type {
        DIAGNOSTIC,
        THERAPEUTIC,
        SURGICAL,
        SUPPORT
    }

    public enum Status {
        AVAILABLE,
        IN_USE,
        MAINTENANCE,
        DISPOSED
    }

}
