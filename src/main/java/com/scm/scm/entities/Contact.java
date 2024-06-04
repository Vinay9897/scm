package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity(name="contact")
@Table(name = "contacts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length = 10000)
    private String description;
    private Boolean favourite = false;
    @Column(length = 1000)
    private String websiteLink;
    @Column(length = 1000)
    private String linkedinLink;

    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "contact", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SocialLink> socialLinks = new ArrayList<>();

}
