package com.team4.leave_application.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="overtime_work")
public class OvertimeWork {
	
	@Id
	@Column(name="otid",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer otId;

	@Column(name="Ottime",nullable = false)
	private String otTime;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "ot_date", nullable = false)
	private Date otDate;

	@Column(name="status",nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = false)
	private Staff staff;


}
