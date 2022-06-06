package com.shoeshop.shoeshop.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class ,
        property = "id"
)
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_request")
    private Timestamp orderedTime;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<Order_AvailableShoes> order_availableShoes;

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public Timestamp getOrderedTime() {
        return orderedTime;
    }

    public Orders(Timestamp orderedTime, User user) {
        this.orderedTime = orderedTime;
        this.user = user;
    }

    public Set<Order_AvailableShoes> getOrder_availableShoes() {
        return order_availableShoes;
    }

}
