package com.shoeshop.shoeshop.Repository;

import com.shoeshop.shoeshop.Entity.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SizesRepository extends JpaRepository<Sizes, Long> {

    @Query("select s from Sizes s where s.size = ?1")
    Optional<Sizes> findSizesBySize(Integer sizes);

}
