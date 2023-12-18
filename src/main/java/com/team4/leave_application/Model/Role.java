package com.team4.leave_application.Model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role implements Serializable {
	 @Id
	 @Column(name = "roleid")
	 private String roleId;
	  
	 @Column(name = "name")
	 private String name;
	  
	 @Column(name = "description")
	 private String description;
}
