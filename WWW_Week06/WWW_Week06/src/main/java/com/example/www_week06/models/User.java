package com.example.www_week06.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Table(name = "user")
@Entity
@Getter @Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private String passwordHash;
    private LocalDate registeredAt;
    private LocalDate lastLogin;
    @Column(columnDefinition = "TINYTEXT")
    private String intro;
    @Column(columnDefinition = "text")
    private String profile;

    @Column(columnDefinition = "text")
    private String token;
    @Column(columnDefinition = "text")
    private LocalDate experiedDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<PostComment> postComments;

    public User(String firstName, String middleName, String lastName, String mobile,
            String email, String passwordHash, LocalDate registeredAt,
            LocalDate lastLogin, String intro, String profile) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registeredAt = registeredAt;
        this.intro = intro;
        this.profile = profile;
    }

    public User() {
    }
}
