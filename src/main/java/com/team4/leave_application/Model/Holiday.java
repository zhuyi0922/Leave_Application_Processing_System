package com.team4.leave_application.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "holiday")
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holiday_id")
	private int holidayId;

	@Column(name = "name", nullable = false)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
}
