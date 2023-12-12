package com.team4.leave_application.Model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LeaveApplicationDTO {
        @DateTimeFormat(pattern="yyyy-MM-dd")
        @Temporal(TemporalType.DATE)
        @NotNull(message = "category不能为空")
        private Date start_date;

        @DateTimeFormat(pattern="yyyy-MM-dd")
        @Temporal(TemporalType.DATE)
        @NotNull(message = "category不能为空")
        private Date end_date;

        @NotNull(message = "category不能为空")
        private String category;

        @NotNull(message = "category不能为空")
        private String Reasons;

        private String workDissemination;

        private String contactDetails;


}
