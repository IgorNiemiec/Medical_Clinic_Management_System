Medical Clinic Management System

## Project Overview

This application is designed to streamline the management of a medical facility by integrating all key processes, from patient registration to financial settlements.

Its primary objective is to centralize user data, efficiently organize appointments, and securely store information in compliance with legal requirements.

## Key Features

The system offers comprehensive functionalities to manage a modern medical clinic:

* **Patient Management:** Enables the storage of detailed patient data (including PESEL/ID, address, visit history).
* **Staff Scheduling:** Management of medical staff schedules, considering their specializations and availability.
* **Appointment & Room Assignment:** Efficient organization and assignment of appointments to specific examination rooms based on their availability and intended use.
* **Secure Data Storage:** Secure storage of all information in compliance with legal and privacy requirements.
* **Automated Payments:** The platform automates the payment process by registering transactions, generating unique operation numbers, and tracking their status.


## Technologies Used

The project is built using the following core technologies (assuming typical Java stack):


* **Language:** Java
* **Framework:** **Spring Boot**
* **Data Access:** **Spring Data JPA**
* **Web:** **Spring Web**
* **Web:** **Spring Web**
* **Utility:** **Lombok**
* **Deployment:** **Docker Compose** 
* **Build Tool:** **Maven**


## Essential Database Tables for the System

* **User:** The base entity for all individuals accessing the system. It contains core authentication and identification details.
   **Role:** Authentication and authorization management.

* **Employee:** The supertype for all clinic staff. It abstracts common employment data.
   **Role:** Stores common employee information (e.g., hiring date, contact).

* **MedicalStaff:** The subtype for clinical personnel (Doctors, Nurses, Assistants). They are responsible for patient care, diagnoses, and procedures.
    **Role:** Clinical functions, prescription and diagnosis

* **Receptionist:** The subtype for administrative personnel. They manage non-clinical operational tasks.
    **Role:** Administrative tasks, scheduling, booking, payment processing, and patient registration.

* **Schedule:** Defines the working hours, on-call periods, or other time commitments for MedicalStaff.
    **Role:** Resource planning and availability management.

* **ExaminationRoom:** The catalog of physical locations/rooms within the clinic.
    **Role:** Asset management

* **Appointment:** Represents a scheduled patient visit with a staff member in a specific room at a specific time.
    **Role:** Core operational unit, linked to all services, notes, and payments.

* **MedicalService** The catalog of all billable medical procedures, consultations, or tests offered by the clinic.
    **Role:** Defines the standard price and scope of services.
   
* **AppointmentService** The intermediary entity that records services actually rendered during an Appointment. It is the basis for invoicing.
    **Role:** Financial tracking and breakdown of services.

* **Payment** Records the transaction details for an Appointment. The amount is derived from the sum of associated AppointmentService entries.
    **Role:** Financial accounting and payment tracking.

* **Patient** Represents a client of the clinic. The entity is linked to a system User (if they have portal access) and their medical history.
    **Role:** Core patient identity data.

* **PatientCard** The central medical record for a single patient.
    **Role:** Acts as the parent container for all clinical history.

* **ICD_Code** The catalog of officially recognized diseases and conditions, based on standard classifications ICD-10.
    **Role:** Formal diagnosis and reporting.

* **DiseaseCourse** Records a specific diagnosis and its progression for a patient, linking the condition to an official ICD_Code.
    **Role:** Clinical history tracking.

* **MedicalNote** Records all documentation created during or after a patient visit.
    **Role:** Detailed documentation of patient-physician interactions and findings.

* **Drug** The catalog of officially registered medicinal products, sourced from official governmental registers (e.g., ezdrowie.gov.pl).
    **Role:** Formal and accurate identification of medications.

* **Prescription** Records the formal issuance of a medication to a patient by a certified staff member.
    **Role:** Formal documentation of medication orders.



