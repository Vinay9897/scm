package com.scm.scm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name= "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(name = "username",length = 11,nullable = false,unique = true,updatable = true)
    private String name;
    private String emailId;
    private String password;
    private String about;
    private String phoneNo;

    @Column(length=10000)
    private String profilePic;
    private String gender;

    private Boolean enabled = false;
    private Boolean emailVerified = false;
    private Boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();

}
