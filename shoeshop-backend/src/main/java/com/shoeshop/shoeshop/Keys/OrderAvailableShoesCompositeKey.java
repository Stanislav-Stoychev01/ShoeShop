package com.shoeshop.shoeshop.Keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderAvailableShoesCompositeKey implements Serializable {

    @Column(name ="order_id")
    private Long order_id;

    @Column(name = "available_shoes_id")
    private Long available_shoes_id;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getAvailable_shoes_id() {
        return available_shoes_id;
    }

    public void setAvailable_shoes_id(Long available_shoes_id) {
        this.available_shoes_id = available_shoes_id;
    }

    public OrderAvailableShoesCompositeKey() {
    }

    public OrderAvailableShoesCompositeKey(Long order_id, Long available_shoes_id) {
        this.order_id = order_id;
        this.available_shoes_id = available_shoes_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAvailableShoesCompositeKey that = (OrderAvailableShoesCompositeKey) o;
        return order_id.equals(that.order_id) && available_shoes_id.equals(that.available_shoes_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, available_shoes_id);
    }
}
