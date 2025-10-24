package org.example.medical_clinic_management_system.repository.communication;


import org.example.medical_clinic_management_system.model.communication.Notification;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.model.communication.Notification.Status;
import org.example.medical_clinic_management_system.model.communication.Notification.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>
{

    List<Notification> findByUser(User user);

    List<Notification> findByType(Type type);

    List<Notification> findByStatus(Status status);

    List<Notification> findByDate(LocalDate date);

    List<Notification> findByUserAndStatus(User user, Status status);

    List<Notification> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    List<Notification> findByContentContainingIgnoreCase(String keyword);


}
