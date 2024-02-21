package com.project.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.entity.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "roles")
public class UserRole {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType roleType;


    private String roleName;

    @OneToMany(mappedBy = "userRole")
    @JsonIgnore
    private List<User> users;



}
