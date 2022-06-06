package com.shoeshop.shoeshop.Controller;

import com.shoeshop.shoeshop.Entity.User;
import com.shoeshop.shoeshop.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;




@RestController
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/user")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/fetch/users")
    public List<User> GetAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/save") public ResponseEntity<?> persistUser(String name, String phoneNum, String email,
                                                                    boolean sub, String password){
        User user = userRepo.findUserByName(name).orElse(new User(name, phoneNum, email, sub, password));
        if(userRepo.findUserByName(name).isPresent())
        {
            return ResponseEntity.ok("Потребителя вече съществува");
        }
        user.setPassword(user.hashPassword(password));
        userRepo.save(user);
        return ResponseEntity.ok("Потребителя е запазен успешно");
    }

    @DeleteMapping("/delete")
    public Object deleteUser(String name) {
            Optional<User> user = userRepo.findUserByName(name);
            try {
                Optional.of(user)
                        .filter(a -> a.isPresent())
                        .orElseThrow(NoSuchElementException::new);
            }
            catch (NoSuchElementException e) {
                return "Потребителя не е намерен";
            }
            userRepo.delete(user.get());
            return ResponseEntity.ok("Потребителя е успешно изтрит");
    }

    @PostMapping("/notifyme")
    public ResponseEntity<?> notifyUser(@RequestParam String user)
    {
        User potrebitel = userRepo.findUserByName(user).get();
        potrebitel.setSubscription(true);
        userRepo.save(potrebitel);
        return ResponseEntity.ok("Потребителят ще получава нотификации");
    }


}
