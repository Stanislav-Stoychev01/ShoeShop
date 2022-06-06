package com.shoeshop.shoeshop.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "available_shoes")
public class AvailableShoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "prices")
    private Double price;

    @OneToMany(mappedBy = "availableShoes")
    private Set<Order_AvailableShoes> timestamps;

    @ManyToMany
    @JoinTable(name = "available_shoes_sizes",
            joinColumns = @JoinColumn(name = "available_shoes_id"),
            inverseJoinColumns = @JoinColumn(name = "sizes_id")
    )
    private Set<Sizes> sizes;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public AvailableShoes(String brand, String model, String color, Double price,
                          Set<Sizes> sizes) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
        this.sizes = sizes;
    }

    public Long getId() {
        return id;
    }

    public AvailableShoes() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
