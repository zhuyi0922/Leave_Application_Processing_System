package com.team4.leave_application.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "left_annual_days", nullable = false)
    private int leftAnnualDays;
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Column
    private int remainingMedicalLeave;

    @Column(name = "manager_id", nullable = false)
    private int managerId;


    @Column(name = "is_manager", nullable = false)
    private Boolean isManager = false;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveApplication> leaveApplications = new ArrayList<>();


    public Staff(String username, String password, String email, int leftAnnualDays, String title, Boolean isManager, Integer managerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.title = title;
        this.leftAnnualDays = leftAnnualDays;
        this.managerId = managerId;
        this.isManager = isManager;
    }

}