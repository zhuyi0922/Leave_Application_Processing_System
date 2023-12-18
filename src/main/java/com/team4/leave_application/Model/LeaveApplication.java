package com.team4.leave_application.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "leave_application")
public class LeaveApplication {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "state", nullable = false)
    private String state;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date end_date;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "reasons", nullable = false)
    private String Reasons;

    @Column(name = "work_dissemination")
    private String workDissemination;

    @Column(name = "contact_details")
    private String contactDetails;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employees_id", nullable = false)
    private Staff staff;

}