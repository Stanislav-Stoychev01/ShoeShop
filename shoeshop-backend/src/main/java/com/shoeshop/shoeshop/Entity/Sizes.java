package com.shoeshop.shoeshop.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Sizes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sizes")
    private Integer size;

    public Sizes() {
    }

    @ManyToMany(mappedBy = "sizes")
    Set<AvailableShoes> sizes;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sizes(Integer size) {
        this.size = size;
    }
}


