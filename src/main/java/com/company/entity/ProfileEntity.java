package com.company.entity;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileEntity {
//     1. Profile
//            id,name,surname,email,photo,role,status
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String password;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column
    private Boolean visible = Boolean.TRUE;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private AttachEntity photo;

    public ProfileEntity( ) {
    }

    public ProfileEntity(Integer id) {
        this.id = id;
    }
}
