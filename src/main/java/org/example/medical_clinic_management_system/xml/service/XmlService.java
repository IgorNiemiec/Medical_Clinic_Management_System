package org.example.medical_clinic_management_system.xml.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.example.medical_clinic_management_system.xml.dto.InvoiceXmlDto;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;

@Service
@Slf4j
public class XmlService
{

    private final JAXBContext invoiceContext;


    public XmlService() {
        try {

            this.invoiceContext = JAXBContext.newInstance(InvoiceXmlDto.class);
        } catch (JAXBException e) {
            log.error("Failed to initialize JAXBContext for InvoiceXmlDto.", e);
            throw new ExceptionInInitializerError("JAXB context initialization failed: " + e.getMessage());
        }
    }

    public String marshalInvoiceToXml(InvoiceXmlDto invoiceXmlDto) {
        try {
            Marshaller marshaller = invoiceContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // Ustawienie kodowania, aby obsłużyć polskie znaki

            StringWriter sw = new StringWriter();
            marshaller.marshal(invoiceXmlDto, sw);
            return sw.toString();
        } catch (JAXBException e) {
            log.error("XML Marshalling failed for Invoice ID: {}", invoiceXmlDto.getInvoiceNumber(), e);
            throw new RuntimeException("marshalling.failed", e);
        }
    }

    public InvoiceXmlDto unmarshalInvoiceFromXml(String xmlString) {
        try {
            Unmarshaller unmarshaller = invoiceContext.createUnmarshaller();
            StringReader sr = new StringReader(xmlString);
            return (InvoiceXmlDto) unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            log.error("XML Unmarshalling failed for provided XML string.", e);
            throw new RuntimeException("unmarshalling.failed", e);
        }
    }



}
