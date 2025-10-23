package org.example.medical_clinic_management_system.model.communication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.User;

import java.time.LocalDate;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = true)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    public enum Status {
        SCHEDULED,
        COMPLETED,
        CANCELLED
    }


    public enum Type
    {
        SYSTEM,
        REMINDER,
        ALERT,
        MESSAGE
    }

}
