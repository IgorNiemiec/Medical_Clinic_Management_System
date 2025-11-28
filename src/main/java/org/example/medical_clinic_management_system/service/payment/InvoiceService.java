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
import org.example.medical_clinic_management_system.xml.dto.InvoiceItemXmlDto;
import org.example.medical_clinic_management_system.xml.dto.InvoiceXmlDto;
import org.example.medical_clinic_management_system.xml.service.XmlService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService
{

    private final InvoiceRepository invoiceRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMedicalServiceRepository amsRepository;
    private final InvoiceMapper invoiceMapper;
    private final XmlService xmlService;

    private static final String INVOICE_NOT_FOUND = "Invoice not found with ID: ";


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


        invoice.setStatus(newStatus);
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDetailsDto(updatedInvoice);
    }

    private BigDecimal[] calculateTotals(List<AppointmentMedicalService> items) {
        BigDecimal totalNet = BigDecimal.ZERO;
        BigDecimal totalGross = BigDecimal.ZERO;

        for (AppointmentMedicalService item : items) {
            BigDecimal itemNetValue = item.getPriceAtTime().multiply(new BigDecimal(item.getQuantity()));

            totalNet = totalNet.add(itemNetValue);
            totalGross = totalGross.add(itemNetValue);
        }

        return new BigDecimal[]{
                totalNet.setScale(2, RoundingMode.HALF_UP),
                totalGross.setScale(2, RoundingMode.HALF_UP)
        };
    }

    public BigDecimal calculateTotalGross(List<AppointmentMedicalService> items) {
        return calculateTotals(items)[1];
    }

    private String generateNextInvoiceNumber() {
        long currentCount = invoiceRepository.count();
        String sequentialNumber = String.format("%04d", currentCount + 1);
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));

        return datePart + "/" + sequentialNumber;
    }


    private InvoiceXmlDto mapToInvoiceXmlDto(Invoice invoice) {
        List<AppointmentMedicalService> services = invoice.getItems() != null && !invoice.getItems().isEmpty()
                ? invoice.getItems()
                : amsRepository.findByInvoiceId(invoice.getId());


        List<InvoiceItemXmlDto> items = services.stream().map(service -> {

            BigDecimal netValue = service.getPriceAtTime().multiply(new BigDecimal(service.getQuantity()));

            BigDecimal grossValue = netValue;

            return InvoiceItemXmlDto.builder()
                    .serviceTitle(service.getServiceTitleSnapshot() != null ? service.getServiceTitleSnapshot() : service.getMedicalService().getTitle())
                    .quantity(service.getQuantity())
                    .unitPrice(service.getPriceAtTime())
                    .netValue(netValue.setScale(2, RoundingMode.HALF_UP))
                    .grossValue(grossValue.setScale(2, RoundingMode.HALF_UP))
                    .build();
        }).collect(Collectors.toList());

        BigDecimal totalGrossAmount = items.stream()
                .map(InvoiceItemXmlDto::getGrossValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return InvoiceXmlDto.builder()
                .invoiceNumber(invoice.getInvoiceNumber())
                .issueDate(invoice.getIssueDate().toLocalDate())
                .dueDate(invoice.getDueDate().toLocalDate())
                .payerName(invoice.getPayerName())
                .payerAddress(invoice.getPayerAddress())
                .payerNip(invoice.getPayerNip())
                .totalGrossAmount(totalGrossAmount)
                .items(items)
                .build();
    }

    @Transactional
    public String exportInvoiceToXml(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException(INVOICE_NOT_FOUND + invoiceId));

        InvoiceXmlDto xmlDto = mapToInvoiceXmlDto(invoice);

        return xmlService.marshalInvoiceToXml(xmlDto);
    }

    public InvoiceXmlDto importInvoiceFromXml(String xmlContent) {
        return xmlService.unmarshalInvoiceFromXml(xmlContent);
    }





}
