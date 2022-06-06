package com.shoeshop.shoeshop.Repository;

import com.shoeshop.shoeshop.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Order;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findOrderByUser(String name);
}

