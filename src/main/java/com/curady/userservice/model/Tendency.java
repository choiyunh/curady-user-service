package com.curady.userservice.model;

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
    private String tendencyType;
    @Column(nullable = false, length = 50, unique = true)
    private String tendencyName;

    @OneToMany(mappedBy = "tendency")
    private List<UserTendency> userTendencies = new ArrayList<>();
}