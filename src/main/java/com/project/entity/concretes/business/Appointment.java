package com.project.entity.concretes.business;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Randevu bilgileri
    private String appointmentDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    /*
   Bu classın sadece userlar ile alakası var. Bir doctorun birden fazla randevusu olabilir
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User doctor;


    @ManyToMany
    @JoinTable(
            name = "appointment_patient_table",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id")
    )
    private List<User> patientList;







}
