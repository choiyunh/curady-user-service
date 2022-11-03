package com.curady.userservice.domain.userTendency.model;

import com.curady.userservice.domain.tendency.model.Tendency;
import com.curady.userservice.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
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

    public UserTendency(User user, Tendency tendency) {
        this.user = user;
        this.tendency = tendency;
    }
}
