package org.example.medical_clinic_management_system.model.specialization;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffSpecializationId implements Serializable {

    private Long staffId;

    private Long specializationId;

}