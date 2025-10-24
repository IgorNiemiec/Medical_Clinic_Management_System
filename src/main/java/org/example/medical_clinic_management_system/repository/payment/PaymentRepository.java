package org.example.medical_clinic_management_system.repository.payment;

import org.example.medical_clinic_management_system.model.payment.Payment;
import org.example.medical_clinic_management_system.model.payment.Payment.PaymentMethod;
import org.example.medical_clinic_management_system.model.payment.Payment.Status;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{

    Optional<Payment> findByTransactionNumber(String transactionNumber);

    Optional<Payment> findByAppointment(Appointment appointment);

    List<Payment> findByStatus(Status status);

    List<Payment> findByPaymentMethod(PaymentMethod method);

    List<Payment> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Payment> findByAmountGreaterThan(BigDecimal minAmount);

    List<Payment> findByStatusAndPaymentMethod(Status status, PaymentMethod method);

}
