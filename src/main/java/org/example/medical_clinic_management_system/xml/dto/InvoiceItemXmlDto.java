package org.example.medical_clinic_management_system.xml.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "invoice_item")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceItemXmlDto
{

    @XmlElement(name = "service_title", required = true)
    private String serviceTitle;

    @XmlElement(name = "quantity", required = true)
    private Integer quantity;

    @XmlElement(name = "unit_price", required = true)
    private BigDecimal unitPrice;

    @XmlElement(name = "net_value", required = true)
    private BigDecimal netValue;

    @XmlElement(name = "gross_value", required = true)
    private BigDecimal grossValue;

}
