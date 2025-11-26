package org.example.medical_clinic_management_system.repository.payment;

import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{


    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

}
