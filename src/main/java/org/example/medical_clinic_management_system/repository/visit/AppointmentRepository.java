package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{

    List<Appointment> findByPatientIdOrderByTimeDesc(Long patientId);

    List<Appointment> findByMedicalStaffIdAndDateOrderByTimeAsc(Long medicalStaffId, LocalDate date);

    List<Appointment> findByExaminationRoomIdAndDateOrderByTimeAsc(Long examinationRoomId, LocalDate date);

    @Query("SELECT a FROM Appointment a " +
            "WHERE a.id <> :excludedAppointmentId AND a.status IN ('SCHEDULED', 'CONFIRMED') AND (" +
            "(a.medicalStaff.id = :medicalStaffId) OR " +
            "(a.examinationRoom.id = :examinationRoomId)) AND (" +
            "a.time < :endDateTime AND :startDateTime < a.time)")
    List<Appointment> findConflictingAppointments(
            @Param("medicalStaffId") Long medicalStaffId,
            @Param("examinationRoomId") Long examinationRoomId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("excludedAppointmentId") Long excludedAppointmentId);

    List<Appointment> findByDateBeforeAndStatus(LocalDate date, Appointment.AppointmentStatus status);

    @Query("SELECT SUM(ams.medicalService.price) FROM AppointmentMedicalService ams WHERE ams.appointment.id = :appointmentId")
    BigDecimal calculateTotalCostForAppointment(@Param("appointmentId") Long appointmentId);

}
