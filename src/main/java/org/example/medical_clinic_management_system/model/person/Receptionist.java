package org.example.medical_clinic_management_system.model.person;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "receptionist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receptionist
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee_id",referencedColumnName = "id", nullable = false)
    private Employee employee;


}
