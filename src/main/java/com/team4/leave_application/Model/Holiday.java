package com.team4.leave_application.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "holiday")
public class Holiday {
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "date", nullable = false)
	private LocalDate date;
}
