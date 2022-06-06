package com.shoeshop.shoeshop.Payload.Request;

import java.util.List;

public class AvailableShoesRequest {

    private String brand;
    private String model;
    private String color;
    private Double price;
    private List<Integer> sizes;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Double getPrice() {
        return price;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

}
