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
    @Column(name = "leave_application_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveApplicationId;

    @Column(name="application_status", columnDefinition="ENUM('APPLIED', 'UPDATED', 'DELETED', 'CANCELLED', 'APPROVED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private LeaveApplicationEventEnum application_status;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date end_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date create_date;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "application_leavetype")
    private LeaveType leaveType;

    @Column(name = "reasons", nullable = false)
    private String Reasons;

    @Column(name = "work_dissemination")
    private String workDissemination;

    @Column(name = "contact_details")
    private String contactDetails;

    @Column(name = "cost_leave_days")
    private int costLeaveDays;

    @Column(name="response_comment")
    private String responseComment;

    @Column(name="response_date")
    private Date responseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

}