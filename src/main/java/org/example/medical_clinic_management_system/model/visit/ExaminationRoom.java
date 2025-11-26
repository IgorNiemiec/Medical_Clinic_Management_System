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

    @Column(nullable = false, unique = true, length = 10)
    private String number;

    @Column(columnDefinition = "TEXT")
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExaminationRoomStatus status;


    public enum ExaminationRoomStatus
    {
        AVAILABLE,
        OCCUPIED,
        CLEANING,
        MAINTENANCE
    }



}
