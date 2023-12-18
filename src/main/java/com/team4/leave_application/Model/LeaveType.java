package com.team4.leave_application.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "leave_type")
public class LeaveType {
    @Id
    @Column(name = "leave_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveTypeId;
    
    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;
    // comfirm something with Yk
}
