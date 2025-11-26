package org.example.medical_clinic_management_system.service.medicalService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceRequestDto;
import org.example.medical_clinic_management_system.mapper.medicalService.AppointmentMedicalServiceMapper;
import org.example.medical_clinic_management_system.model.medicalService.AppointmentMedicalService;
import org.example.medical_clinic_management_system.model.medicalService.MedicalService;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.repository.medicalService.AppointmentMedicalServiceRepository;
import org.example.medical_clinic_management_system.repository.medicalService.MedicalServiceRepository;
import org.example.medical_clinic_management_system.repository.payment.InvoiceRepository;
import org.example.medical_clinic_management_system.repository.visit.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentMedicalServiceService
{

    private final AppointmentMedicalServiceRepository appointmentMedicalServiceRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalServiceRepository medicalServiceRepository;
    private final InvoiceRepository invoiceRepository;
    private final AppointmentMedicalServiceMapper appointmentMedicalServiceMapper;

    @Transactional
    public AppointmentMedicalServiceDetailsDto addServiceToAppointment(AppointmentMedicalServiceRequestDto requestDto)
    {


        Appointment appointment = appointmentRepository.findById(requestDto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Wizyta"));

        MedicalService medicalService = medicalServiceRepository.findById(requestDto.getMedicalServiceId())
                .orElseThrow(() -> new RuntimeException("Usługa Medyczna (Katalog)"));

        Invoice invoice = invoiceRepository.findById(requestDto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice"));


        BigDecimal priceToUse = requestDto.getUnitPrice() != null
                ? requestDto.getUnitPrice()
                : medicalService.getPrice();

        if (priceToUse.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cena usługi nie może być ujemna.");
        }


        AppointmentMedicalService appointmentMedicalService = AppointmentMedicalService.builder()
                .appointment(appointment)
                .medicalService(medicalService)
                .invoice(invoice)
                .quantity(requestDto.getQuantity())
                .priceAtTime(priceToUse)
                .billingNote(requestDto.getBillingNote())
                .serviceTitleSnapshot(medicalService.getTitle())
                .build();

        AppointmentMedicalService savedService = appointmentMedicalServiceRepository.save(appointmentMedicalService);

        return appointmentMedicalServiceMapper.toDetailsDto(savedService);
    }


    @Transactional
    public AppointmentMedicalServiceDetailsDto getAppointmentServiceById(Long id) {
        AppointmentMedicalService service = appointmentMedicalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pozycja Usługi Wizyty"));
        return appointmentMedicalServiceMapper.toDetailsDto(service);
    }

    @Transactional
    public List<AppointmentMedicalServiceDetailsDto> getServicesByAppointmentId(Long appointmentId) {
        List<AppointmentMedicalService> services = appointmentMedicalServiceRepository.findByAppointmentId(appointmentId);

        if (services.isEmpty() && !appointmentRepository.existsById(appointmentId)) {
            throw new RuntimeException("Wizyta");
        }

        return appointmentMedicalServiceMapper.toDetailsDtoList(services);
    }

    @Transactional
    public BigDecimal calculateTotalCost(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new RuntimeException("Wizyta");
        }
        return appointmentMedicalServiceRepository.calculateTotalCostForAppointment(appointmentId);
    }


    @Transactional
    public void deleteAppointmentService(Long id) {
        if (!appointmentMedicalServiceRepository.existsById(id)) {
            throw new RuntimeException("Pozycja Usługi Wizyty");
        }
        appointmentMedicalServiceRepository.deleteById(id);
    }




}
