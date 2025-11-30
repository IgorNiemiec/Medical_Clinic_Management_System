package org.example.medical_clinic_management_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MedicalClinicManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicManagementSystemApplication.class, args);
	}



}
