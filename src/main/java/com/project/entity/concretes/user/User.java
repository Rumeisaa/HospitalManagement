package com.project.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.concretes.business.Appointment;
import com.project.entity.concretes.business.PatientInfo;
import com.project.entity.concretes.business.TreatmentPlan;
import com.project.entity.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "t_user")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @Pattern(regexp = "^(?!000|666|9\\d\\d)\\d{3}-(?!00)\\d{2}-(?!0000)\\d{4}$", message = "Invalid SSN format")
    private String ssn;


    private String name;

    private String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private String birthPlace;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String address;

    private Boolean built_in;

    private String motherName;

    private String fatherName;

    private int patientNumber;

    private boolean isActive;

    private Boolean isChiefDoctor; //Uzman Doktor mu?

    private Long patientDoctorId;  //bu hastalar icin gerekli, kendi doktornunun id si buraya yazilacak

    @Enumerated(EnumType.STRING)
    private Gender gender;



    /*
       patientInfo -> Bu bir hasta ise patientInfo bilgileri olmalı
       */
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE)  //mappedBy = "patient" de olabilir.
    // CascadeType.REMOVE ile bir user silindiğinde bu user'a ait bilgileri de silinsin.
    private List<PatientInfo> patientInfos;


     /*
       //patient yada doctor da olsa threatmentPlan bilgileri olmalı
        */
     @ManyToMany
     @JsonIgnore
     @JoinTable(
             name = "user_treatmentplan",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "treatmentplan_id")
     )
     private Set<TreatmentPlan> treatmentPlanList;

    /*
        // appointment -> Eğer user doctor da olsa patient de olsa appointment ile ilişkisi olması lazım. Benim randevularımı getir dediğinde ileriki tarihlerde olan randevuları görmesi gerekli
        */

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "appointment_patient_table",
            joinColumns = @JoinColumn(name="patient_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id")

    )
    private List<Appointment> AppointmentList;


    /*
   //userRole -> Herbir kullanıcının bir user rolü olmalıdır.
    */
    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserRole userRole;




}
