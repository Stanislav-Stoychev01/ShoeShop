package com.shoeshop.shoeshop.Payload.Request;

import com.shoeshop.shoeshop.Entity.AvailableShoes;

import java.util.List;

public class UsernameAndQuantitiesRequest {

    public String username;
    public List<Integer> quantities;
    private List<AvailableShoes> availableShoes;

    public List<AvailableShoes> getAvailableShoes() {
        return availableShoes;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public String getUsername() {
        return username;
    }

}
