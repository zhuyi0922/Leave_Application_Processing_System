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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    @Column(name = "isManager", nullable = false)
    private boolean isManager;

    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = true)
    private Staff staff;
    
    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch=FetchType.EAGER)
    @JoinTable(name = "userrole", joinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid") }, inverseJoinColumns = {
            @JoinColumn(name = "roleid", referencedColumnName = "roleid") }
    )
    private List<Role> roleSet;
}