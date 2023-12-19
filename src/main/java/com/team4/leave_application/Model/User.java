package com.team4.leave_application.Model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = true)
    private Staff staff;
    
    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch=FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id") }
    )
    private List<Role> roleSet;
}