package org.example.medical_clinic_management_system.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "patients")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientsWrapperDto {

    @XmlElement(name = "patient")
    private List<PatientXmlDto> patients = new ArrayList<>();

    public PatientsWrapperDto() {}

    public List<PatientXmlDto> getPatients() { return patients; }
    public void setPatients(List<PatientXmlDto> patients) { this.patients = patients; }
}
