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

* **User:** Stores data for all system users (patients, medical staff, receptionists), including login credentials (email, password), role, and activity history.

* **Patient:**  Contains detailed patient information: PESEL (national ID), date of birth, address, phone, gender, linked to the User account.

* **Receptionist:** Stores receptionist-specific information, such as employment date and work phone number, linked to the User account.

* **MedicalStaff:** Holds data for medical employees (doctors, nurses), including employment date, availability, work phone number, and specializations.

* **Specialization** Defines medical specializations (e.g., cardiology, pediatrics) along with their descriptions.

* **Staff_Specialization** A join table connecting MedicalStaff with their Specializations (many-to-many relationship).

* **ExaminationRoom** Manages information about rooms in the clinic, including number, location, purpose (e.g., "consultations", "procedures"), and status (active/inactive).

* **Appointment** Stores appointment details: date, time, service type, status (scheduled/completed/canceled), description, and links to Patient, MedicalStaff, ExaminationRoom, and Receptionist.

* **Payment** Records payment for appointments, with data on amount, payment method, status, unique transaction number, and link to the specific Appointment.