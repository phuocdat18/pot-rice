package com.cg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole code;
}
