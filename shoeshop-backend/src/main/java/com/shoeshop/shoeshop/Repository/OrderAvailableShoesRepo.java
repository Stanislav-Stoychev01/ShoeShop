package com.shoeshop.shoeshop.Repository;

import com.shoeshop.shoeshop.Entity.Order_AvailableShoes;
import com.shoeshop.shoeshop.Keys.OrderAvailableShoesCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAvailableShoesRepo extends JpaRepository<Order_AvailableShoes, OrderAvailableShoesCompositeKey> {
}
