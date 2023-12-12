package com.team4.leave_application.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReasons() {
        return Reasons;
    }

    public void setReasons(String reasons) {
        Reasons = reasons;
    }

    public String getWorkDissemination() {
        return workDissemination;
    }

    public void setWorkDissemination(String workDissemination) {
        this.workDissemination = workDissemination;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}