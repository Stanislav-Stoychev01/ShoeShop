package com.shoeshop.shoeshop.Entity;

import com.shoeshop.shoeshop.Keys.OrderAvailableShoesCompositeKey;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class ,
        property = "id"
)
public class Order_AvailableShoes {

    @EmbeddedId
    private OrderAvailableShoesCompositeKey id;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @MapsId("available_shoes_id")
    @JoinColumn(name = "available_shoes_id")
    private AvailableShoes availableShoes;

    private Integer quantity;

    public Order_AvailableShoes(OrderAvailableShoesCompositeKey id, Orders order,
                                AvailableShoes availableShoes, Integer quantity) {
        this.id = id;
        this.order = order;
        this.availableShoes = availableShoes;
        this.quantity = quantity;
    }

    public Order_AvailableShoes() {
    }

    public OrderAvailableShoesCompositeKey getId() {
        return id;
    }

    public AvailableShoes getAvailableShoes() {
        return availableShoes;
    }


}
