package com.shoeshop.shoeshop.Entity;

import javax.persistence.*;

@Entity
public class Sizes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sizes")
    private Integer size;

    public Sizes() {
    }

    public Sizes(Integer size) {
        this.size = size;
    }
}


