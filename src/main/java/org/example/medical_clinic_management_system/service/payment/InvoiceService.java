package org.example.medical_clinic_management_system.service.payment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.InvoiceDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.InvoiceRequestDto;
import org.example.medical_clinic_management_system.mapper.payment.InvoiceMapper;
import org.example.medical_clinic_management_system.model.medicalService.AppointmentMedicalService;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.repository.medicalService.AppointmentMedicalServiceRepository;
import org.example.medical_clinic_management_system.repository.payment.InvoiceRepository;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService
{

    private final InvoiceRepository invoiceRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMedicalServiceRepository amsRepository;
    private final InvoiceMapper invoiceMapper;


    @Transactional
    public InvoiceDetailsDto generateInvoice(InvoiceRequestDto requestDto) {

        Patient patient = patientRepository.findById(requestDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Pacjent (Nabywca)"));

        List<AppointmentMedicalService> items = amsRepository.findAllById(requestDto.getAppointmentMedicalServiceIds());

        if (items.size() != requestDto.getAppointmentMedicalServiceIds().size()) {
            throw new RuntimeException("Jedna lub więcej wybranych pozycji rozliczeniowych nie istnieje.");
        }


        boolean alreadyInvoiced = items.stream().anyMatch(ams -> ams.getInvoice() != null);
        if (alreadyInvoiced) {
            throw new RuntimeException("Jedna lub więcej wybranych pozycji jest już przypisana do innej faktury.");
        }


        BigDecimal[] totals = calculateTotals(items);
        BigDecimal totalNet = totals[0];
        BigDecimal totalGross = totals[1];


        String invoiceNumber = generateNextInvoiceNumber();


        LocalDateTime issueDate = LocalDateTime.now();
        Invoice invoice = Invoice.builder()
                .invoiceNumber(invoiceNumber)
                .issueDate(issueDate)
                .dueDate(requestDto.getDueDate())
                .status(Invoice.InvoiceStatus.ISSUED)
                .totalNet(totalNet)
                .totalGross(totalGross)
                .patient(patient)
                .payerName(requestDto.getPayerName())
                .payerAddress(requestDto.getPayerAddress())
                .payerNip(requestDto.getPayerNip())
                .build();


        Invoice savedInvoice = invoiceRepository.save(invoice);


        for (AppointmentMedicalService item : items) {
            item.setInvoice(savedInvoice);
        }
        amsRepository.saveAll(items);

        savedInvoice.setItems(items);

        return invoiceMapper.toDetailsDto(savedInvoice);
    }


    @Transactional
    public InvoiceDetailsDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faktura"));
        return invoiceMapper.toDetailsDto(invoice);
    }

    @Transactional
    public List<InvoiceDetailsDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoiceMapper.toDetailsDtoList(invoices);
    }

    @Transactional
    public InvoiceDetailsDto updateInvoiceStatus(Long id, Invoice.InvoiceStatus newStatus) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faktura"));

        // Tutaj można dodać logikę walidacyjną przejścia statusów, np.
        // Fakturę wystawioną można tylko opłacić lub anulować.
        // Nie można przejść ze statusu ANULOWANA na OPŁACONA.

        invoice.setStatus(newStatus);
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDetailsDto(updatedInvoice);
    }

    private BigDecimal[] calculateTotals(List<AppointmentMedicalService> items) {
        BigDecimal totalNet = BigDecimal.ZERO;
        BigDecimal totalGross = BigDecimal.ZERO;

        for (AppointmentMedicalService item : items) {
            totalNet = totalNet.add(item.getNetPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            totalGross = totalGross.add(item.getGrossPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        return new BigDecimal[]{
                totalNet.setScale(2, RoundingMode.HALF_UP),
                totalGross.setScale(2, RoundingMode.HALF_UP)
        };
    }

    private String generateNextInvoiceNumber() {
        long currentCount = invoiceRepository.count();
        String sequentialNumber = String.format("%04d", currentCount + 1);
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));

        return datePart + "/" + sequentialNumber;
    }





}
