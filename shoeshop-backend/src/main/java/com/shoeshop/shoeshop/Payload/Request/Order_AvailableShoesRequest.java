package com.shoeshop.shoeshop.Payload.Request;

import com.shoeshop.shoeshop.Entity.AvailableShoes;
import com.shoeshop.shoeshop.Entity.Sizes;

import java.util.List;

public class Order_AvailableShoesRequest extends AvailableShoesRequest {

    private String user_name;
    private Integer quantity;


    public String getUser_name() {
        return user_name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
