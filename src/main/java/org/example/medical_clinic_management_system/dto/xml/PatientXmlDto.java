package org.example.medical_clinic_management_system.dto.xml;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.medical_clinic_management_system.model.person.Patient.Gender;

import java.time.LocalDate;

@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientXmlDto {

    @XmlElement(name = "id")
    private Long id;

    @NotNull(message = "{validation.patient.userId.notNull}")
    @XmlElement(name = "userId", required = true)
    private Long userId;

    @NotNull(message = "{validation.patient.dateOfBirth.notNull}")
    @Past(message = "{validation.patient.dateOfBirth.past}")
    @XmlElement(name = "dateOfBirth", required = true)
    private LocalDate dateOfBirth;

    @NotBlank(message = "{validation.patient.address.notBlank}")
    @Size(min = 5, max = 255, message = "{validation.patient.address.size}")
    @XmlElement(name = "address", required = true)
    private String address;

    @NotBlank(message = "{validation.patient.phone.notBlank}")
    @Pattern(regexp = "^[0-9\\-+]{9,15}$", message = "{validation.patient.phone.pattern}")
    @XmlElement(name = "phone", required = true)
    private String phone;

    @NotBlank(message = "{validation.patient.pesel.notBlank}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{validation.patient.pesel.pattern}")
    @XmlElement(name = "pesel", required = true)
    private String pesel;

    @NotNull(message = "{validation.patient.gender.notNull}")
    @XmlElement(name = "gender", required = true)
    private Gender gender;

    public PatientXmlDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPesel() { return pesel; }
    public void setPesel(String pesel) { this.pesel = pesel; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
}
