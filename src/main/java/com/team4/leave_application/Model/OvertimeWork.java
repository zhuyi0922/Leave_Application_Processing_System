package com.team4.leave_application.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="overtime_work")
public class OvertimeWork {
	
	@Id
	@Column(name="otid")
	private String otId;

	@NotBlank
	private String staffId;

}
