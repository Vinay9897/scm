package com.scm.scm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name= "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long userId;
    @Column(name = "username",length = 11,nullable = false,unique = true,updatable = true)
    private String name;
    private String emailId;
    private String password;
    private String about;

    @Column(length=10000)
    private String profilePic;
    private String gender;

    private Boolean enabled = false;
    private Boolean emailVerified = false;
    private Boolean phoneVerified = false;

    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();

}
