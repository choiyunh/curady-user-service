package com.curady.userservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_tendency")
public class UserTendency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tendency_id")
    private Tendency tendency;
}
