package com.curady.userservice.domain.tendency.model;

import com.curady.userservice.domain.userTendency.model.UserTendency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tendency")
public class Tendency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;
    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "tendency")
    private List<UserTendency> userTendencies = new ArrayList<>();
}