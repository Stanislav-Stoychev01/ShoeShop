package com.shoeshop.shoeshop.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User
{
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name = "Name")
    private String name;

   @Column(name = "Phone_Number")
    private String PhoneNum;

   @Column(name= "email")
   private String email;

   @Column(name = "subscription")
   private boolean subscription;

   @Column(name = "password")
   private String password;

   @OneToMany(mappedBy = "user")
   @JsonManagedReference
   private Set<Orders> orders;

    public User(String name, String phoneNum, String email, boolean subscription, String password) {
        this.name = name;
        this.PhoneNum = phoneNum;
        this.email = email;
        this.subscription = subscription;
        this.password = password;
    }

    public User()
    {

    }

    public String getEmail() {
        return email;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            messageDigest.update(passwordBytes);
            byte[] bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte byte1 : bytes)
            {
                sb.append(String.format("%02x", byte1));
            }

            password = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return password;
    }

}
