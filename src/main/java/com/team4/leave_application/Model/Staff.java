package com.team4.leave_application.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "staff")
public class Staff implements Serializable {
    private static final long serialVersionUID = 6529685098267757680L;
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
    
    @Column(name = "gender")
    private String gender;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    
    @Column(name = "email")
    private String email;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "ot_hours", columnDefinition = "integer default 0")
    private Integer otHours = 0;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<LeaveApplication> leaveApplications = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<RemainLeave> remainLeaves = new ArrayList<>();
    
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<OvertimeWork> overTimeWork = new ArrayList<>();

}