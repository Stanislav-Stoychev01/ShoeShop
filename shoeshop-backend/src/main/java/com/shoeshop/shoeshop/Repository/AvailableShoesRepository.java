package com.shoeshop.shoeshop.Repository;

import com.shoeshop.shoeshop.Entity.AvailableShoes;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AvailableShoesRepository extends JpaRepository<AvailableShoes, Long> {


    @Query("select a from AvailableShoes a where a.brand = ?1 and a.color = ?2 and a.model = ?3 and a.price = ?4")
    Optional<AvailableShoes> findAvailableShoesByBrandAndColorAndModelAndPrice(String brand, String color, String model,
                                                                               Double price);

    @Query("SELECT m " +
            "FROM AvailableShoes m " +
            "WHERE CONCAT(m.brand, '', m.color, '', m.model, '') LIKE %?1%")
    Page<AvailableShoes> filterShoes(String filter, Pageable pageable);
}