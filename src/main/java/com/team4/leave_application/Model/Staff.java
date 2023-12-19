package com.team4.leave_application.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import java.time.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "gender", nullable = false)
    private String gender;
    
    @Column(name = "birthday")
    private LocalDate birtday;
    
    @Column(name = "email")
    private String email;

    @Column(name = "manager_id", nullable = false)
    private int managerId;

    @Column(name = "title", nullable = false)
    private String title;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveApplication> leaveApplications = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemainLeave> remainLeaves = new ArrayList<>();
}