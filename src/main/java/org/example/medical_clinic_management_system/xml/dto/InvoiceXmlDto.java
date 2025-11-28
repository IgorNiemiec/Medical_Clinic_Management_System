package org.example.medical_clinic_management_system.xml.dto;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.xml.adapter.LocalDateAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceXmlDto
{

    @XmlElement(name = "invoice_number", required = true)
    private String invoiceNumber;


    @XmlElement(name = "issue_date", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate issueDate;

    @XmlElement(name = "due_date", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dueDate;

    @XmlElement(name = "payer_name", required = true)
    private String payerName;

    @XmlElement(name = "payer_address", required = true)
    private String payerAddress;

    @XmlElement(name = "payer_nip")
    private String payerNip;

    @XmlElement(name = "total_gross_amount", required = true)
    private BigDecimal totalGrossAmount;

    @XmlElementWrapper(name = "invoice_items")
    @XmlElement(name = "item")
    private List<InvoiceItemXmlDto> items;



}
