package com.team4.leave_application.Model;

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
    
    @Column(name = "leave_type_name", nullable = false)
    private String leaveTypeName;

    @Column(name = "staff_title", nullable = false)
    private String staffTitle;

    @Column(name = "max_leave_day", nullable = false)
    private int maxLeaveDay;

}
