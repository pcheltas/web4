package com.pcheltas.web4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hits")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private float x;

    @Column
    private float y;

    @Column
    private float R;

    @Column
    private String nowTime;

    @Column
    private String executionTime;

    @Column
    private boolean isHit;

    @Column
    private String username;
}