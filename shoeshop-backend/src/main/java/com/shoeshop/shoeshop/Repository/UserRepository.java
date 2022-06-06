package com.shoeshop.shoeshop.Repository;

import com.shoeshop.shoeshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
    @Query("select u from User u where u.subscription = ?1")
    List<User> findUsersBySubscription(boolean Subscription);
}
