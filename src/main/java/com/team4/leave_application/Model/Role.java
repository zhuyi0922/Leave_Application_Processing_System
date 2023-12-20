package com.team4.leave_application.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role implements Serializable {
	 private static final long serialVersionUID = 6529685098267757690L;
	 @Id
	 @Column(name = "role_id")
	 private String roleId;
	  
	 @Column(name = "name")
	 private String name;
	  
	 @Column(name = "description")
	 private String description;
	 // professional administrative
}
