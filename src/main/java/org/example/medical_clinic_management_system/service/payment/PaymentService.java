package org.example.medical_clinic_management_system.service.payment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.PaymentDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.PaymentRequestDto;
import org.example.medical_clinic_management_system.mapper.payment.PaymentMapper;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.model.payment.Payment;
import org.example.medical_clinic_management_system.repository.payment.InvoiceRepository;
import org.example.medical_clinic_management_system.repository.payment.PaymentRepository;
import org.example.medical_clinic_management_system.repository.visit.AppointmentRepository;
import org.example.medical_clinic_management_system.service.medicalService.AppointmentMedicalServiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService
{

    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentMapper paymentMapper;
    private final AppointmentMedicalServiceService appointmentMedicalServiceService;



    @Transactional
    public PaymentDetailsDto registerPayment(PaymentRequestDto requestDto) {


        Invoice invoice = invoiceRepository.findById(requestDto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice"));


        BigDecimal totalCost = appointmentMedicalServiceService.calculateTotalCost(requestDto.getInvoiceId());
        BigDecimal totalPaid = calculateTotalPaid(requestDto.getInvoiceId());
        BigDecimal outstandingBalance = totalCost.subtract(totalPaid);

        if (requestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Kwota płatności musi być większa niż zero.");
        }


        if (requestDto.getAmount().compareTo(outstandingBalance) > 0 && outstandingBalance.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("OSTRZEŻENIE: Płatność ( " + requestDto.getAmount() + " ) przekracza pozostałe saldo ( " + outstandingBalance + " ) dla faktury " + requestDto.getInvoiceId());
        }


        Payment payment = Payment.builder()
                .invoice(invoice)
                .amount(requestDto.getAmount())
                .paymentMethod(requestDto.getPaymentMethod())
                .paymentDateTime(requestDto.getPaymentDateTime() != null ? requestDto.getPaymentDateTime() : LocalDateTime.now())
                .transactionReference(requestDto.getTransactionReference())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toDetailsDto(savedPayment);
    }

    @Transactional
    public PaymentDetailsDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Płatność"));
        return paymentMapper.toDetailsDto(payment);
    }


    @Transactional
    public List<PaymentDetailsDto> getPaymentsByInvoiceId(Long invoiceId)
    {
        if (!invoiceRepository.existsById(invoiceId))
        {
            throw new RuntimeException("Invoice");
        }
        List<Payment> payments = paymentRepository.findByInvoiceId(invoiceId);
        return paymentMapper.toDetailsDtoList(payments);
    }



    @Transactional
    public BigDecimal calculateTotalPaid(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new RuntimeException("Wizyta");
        }
        return paymentRepository.calculateTotalPaymentsForInvoice(appointmentId).setScale(2, RoundingMode.HALF_UP);
    }

    @Transactional
    public BigDecimal calculateOutstandingBalance(Long appointmentId) {

        BigDecimal totalCost = appointmentMedicalServiceService.calculateTotalCost(appointmentId);

        BigDecimal totalPaid = calculateTotalPaid(appointmentId);

        return totalCost.subtract(totalPaid).setScale(2, RoundingMode.HALF_UP);
    }


    @Transactional
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Płatność");
        }
        paymentRepository.deleteById(id);
    }





}
