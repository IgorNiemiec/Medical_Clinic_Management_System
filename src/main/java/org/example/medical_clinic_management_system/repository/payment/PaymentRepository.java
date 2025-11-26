package org.example.medical_clinic_management_system.repository.payment;

import org.example.medical_clinic_management_system.model.payment.Payment;
import org.example.medical_clinic_management_system.model.payment.Payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>
{

    List<Payment> findByInvoiceId(Long inboiceId);

    @Query("SELECT COALESCE(SUM(p.amount), 0.00) FROM Payment p WHERE p.invoice.id = :invoiceId")
    BigDecimal calculateTotalPaymentsForInvoice(@Param("invoiceId") Long invoiceId);


}
