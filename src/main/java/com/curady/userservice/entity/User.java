package com.curady.userservice.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String encryptedPwd;
    @Column()
    private String imageUrl;
    @Column(length = 50, unique = true)
    private String nickname;
    @Column()
    private boolean emailAuth;

    @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date createdAt;
    @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Date updatedAt;

    private String roles;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void verifyEmail() {
        this.emailAuth = true;
    }

    @OneToMany(mappedBy = "user")
    private List<UserTendency> userTendencies = new ArrayList<>();
}
