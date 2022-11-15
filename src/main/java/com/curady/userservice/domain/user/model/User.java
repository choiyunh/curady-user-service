package com.curady.userservice.domain.user.model;

import com.curady.userservice.domain.role.model.Role;
import com.curady.userservice.domain.userTendency.model.UserTendency;
import com.curady.userservice.domain.user.dto.RequestUserInfo;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String encryptedPwd;
    @Column
    private String imageUrl;
    @Column(length = 50, unique = true)
    private String nickname;
    @Column
    private String gitUrl;
    @Column
    private String blogUrl;
    @Column(length = 1000)
    private String description;
    @Column
    private String provider;
    @Column
    private boolean isEmailAuth;
    @Column
    private String refreshToken;
    private boolean withdraw = false;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date createdAt;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "user")
    private List<UserTendency> userTendencies = new ArrayList<>();
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Builder
    public User(String email, String encryptedPwd, Boolean isEmailAuth) {
        this.email = email;
        this.encryptedPwd = encryptedPwd;
        this.roles = Collections.singletonList(Role.ROLE_USER);
        this.isEmailAuth = isEmailAuth;
    }

    public void updateUserInfo(RequestUserInfo request) {
        this.nickname = request.getNickname();
        this.gitUrl = request.getGitUrl();
        this.blogUrl = request.getBlogUrl();
        this.description = request.getDescription();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void verifyEmail() {
        this.isEmailAuth = true;
    }

    public void setDefaultNickname(Long id) {
        this.nickname = "익명이 " + id;
    }
    public void withdraw() {
        this.withdraw = true;
        this.email = "탈퇴한 이메일 " + id;
        this.nickname = "탈퇴한 회원 " + id;
        this.imageUrl = null;
    }
}
