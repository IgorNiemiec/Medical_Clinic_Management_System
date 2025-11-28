package org.example.medical_clinic_management_system.repository.medicalService;

import org.example.medical_clinic_management_system.model.medicalService.AppointmentMedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AppointmentMedicalServiceRepository extends JpaRepository<AppointmentMedicalService, Long>
{

    List<AppointmentMedicalService> findByAppointmentId(Long appointmentId);

    List<AppointmentMedicalService> findByInvoiceId(Long invoiceId);

    @Query("SELECT COALESCE(SUM(ams.priceAtTime * ams.quantity), 0.00) " +
            "FROM AppointmentMedicalService ams " +
            "WHERE ams.appointment.id = :appointmentId")
    BigDecimal calculateTotalCostForAppointment(@Param("appointmentId") Long appointmentId);




}
