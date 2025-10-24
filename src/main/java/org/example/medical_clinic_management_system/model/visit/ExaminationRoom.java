package org.example.medical_clinic_management_system.model.visit;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "examination_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExaminationRoom
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,nullable = true, unique = true)
    private String number;

    @Column(length = 20,nullable = true, columnDefinition = "TEXT")
    private String purpose;

    @Column(length = 100,nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status
    {
      ACTIVE,
        INACTIVE
    }

}
