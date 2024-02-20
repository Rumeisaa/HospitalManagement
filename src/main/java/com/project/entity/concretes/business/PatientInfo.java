package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodType;

    private String allergyInfo;

    private String currentCondition;  //Hasta mevcut durumu ve saglik sorunlari

    private String treatmentHistory; //Hasta tedavi gecmisi

    private String currentMedications; //Hasta ,evcut ilaclari

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    private LocalDate upcomingAppointments;

    @ManyToOne // 1 hastanin birden fazla patientinfosu olabilir
    @JsonIgnore
    @JoinColumn(name = "patient_id")
    private User patient;  //patient adinda bir User nesnesi

    @ManyToOne // 1 doktor birden fazla infoya atanabilir
    @JoinColumn(name = "doctor_id")
    private User doctor;  //doctor adinda bir User nesnesi

    @OneToMany(mappedBy ="patientInfo" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToOne
    private TreatmentPlan treatmentPlan;
}
