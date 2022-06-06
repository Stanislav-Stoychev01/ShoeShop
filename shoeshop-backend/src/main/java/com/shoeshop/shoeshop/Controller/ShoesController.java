package com.shoeshop.shoeshop.Controller;

import com.shoeshop.shoeshop.Entity.*;
import com.shoeshop.shoeshop.Keys.OrderAvailableShoesCompositeKey;
import com.shoeshop.shoeshop.MailService.MailService;
import com.shoeshop.shoeshop.Payload.Request.AvailableShoesRequest;
import com.shoeshop.shoeshop.Payload.Request.DiscountRequest;
import com.shoeshop.shoeshop.Payload.Request.Order_AvailableShoesRequest;
import com.shoeshop.shoeshop.Payload.Request.UsernameAndQuantitiesRequest;
import com.shoeshop.shoeshop.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/shoes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ShoesController {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final SizesRepository sizeRepo;
    private final AvailableShoesRepository availableShoesRepo;
    private final OrderAvailableShoesRepo orderAvailableShoesRepo;
    private MailService mailService;

    public ShoesController(OrderRepository orderRepo, UserRepository userRepo, SizesRepository sizeRepo,
                           AvailableShoesRepository availableShoesRepo, MailService mailService,
                           OrderAvailableShoesRepo orderAvailableShoesRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.sizeRepo = sizeRepo;
        this.availableShoesRepo = availableShoesRepo;
        this.orderAvailableShoesRepo = orderAvailableShoesRepo;
        this.mailService = mailService;
    }

    @PostMapping("/save/sizes")
    public ResponseEntity<?> persistSizes(Integer size) {
        Sizes sizes = sizeRepo.findSizesBySize
                (size).orElse(new Sizes(size));
        if(sizeRepo.findSizesBySize(size).isPresent())
        {
            return ResponseEntity.ok("Размера вече съществува");
        }
        sizeRepo.save(sizes);
        return ResponseEntity.ok("Размера е записан успешно");
    }

    @PostMapping("/save/shoes")
    public ResponseEntity<?> persistShoes(@RequestBody AvailableShoesRequest availableShoesRequest) {
        Set<Sizes> sizeSet = new HashSet<>();
        for (Integer size : availableShoesRequest.getSizes()) {
            if (!sizeRepo.findSizesBySize(size).isPresent()) {
                sizeRepo.save(new Sizes(size));
            }
            sizeSet.add(sizeRepo.findSizesBySize(size).get());
        }
        AvailableShoes availableShoes = new AvailableShoes(availableShoesRequest.getBrand(),
                availableShoesRequest.getModel(), availableShoesRequest.getColor(),
                availableShoesRequest.getPrice(), sizeSet);

    if(availableShoesRepo.findAvailableShoesByBrandAndColorAndModelAndPrice(availableShoesRequest.getBrand(),
            availableShoesRequest.getColor(), availableShoesRequest.getModel(),
            availableShoesRequest.getPrice()).isPresent())
    {
        return ResponseEntity.ok("Елемента вече съществува");
    }
    else
        return ResponseEntity.ok(availableShoesRepo.save(availableShoes));
    }

    @GetMapping("/all/shoes")
    public List<AvailableShoes> allShoes()
    {
        return availableShoesRepo.findAll();
    }


    @GetMapping("/all/orders")
    public List<Orders> fetchAll()
    {
        return orderRepo.findAll();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrder(String name) {
        Optional<Orders> result = orderRepo.findOrderByUser(name);

        if (result.isEmpty()) {
            return ResponseEntity.ok("Пооръчката не е намерена");
        }
        orderRepo.delete(result.get());
        return ResponseEntity.ok("Поръчката е успешно изтрита");
    }


        @DeleteMapping("/delete/size")
        public ResponseEntity<?> deleteSize(Integer sizes) {
            Optional<Sizes> result = sizeRepo.findSizesBySize(sizes);

            if (result.isEmpty()) {
                return ResponseEntity.ok("Пооръчката не е намерена");
            }
            sizeRepo.delete(result.get());
            return ResponseEntity.ok("Размерът е успешно изтрит");
        }

    @PostMapping("/save/order/1-item")
    public ResponseEntity<?> persistOrder1Item(@RequestBody Order_AvailableShoesRequest order_availableShoesRequest)
    {
        User user = userRepo.findUserByName(order_availableShoesRequest.getUser_name()).get();
        AvailableShoes availableShoes = availableShoesRepo.findAvailableShoesByBrandAndColorAndModelAndPrice
                (
                        order_availableShoesRequest.getBrand(), order_availableShoesRequest.getColor(),
                        order_availableShoesRequest.getModel(), order_availableShoesRequest.getPrice()
                ).get();
        Orders order = orderRepo.save(new Orders(new Timestamp(System.currentTimeMillis()), user));
        Order_AvailableShoes order_availableShoes = orderAvailableShoesRepo.save(
                new Order_AvailableShoes(
                        new OrderAvailableShoesCompositeKey(order.getId() ,availableShoes.getId()),
                        order,
                        availableShoes,
                        order_availableShoesRequest.getQuantity()   ));

        return ResponseEntity.ok("Поръчка с ай ди: " + order_availableShoes.getId().getOrder_id() +
                " е направена успешно!");
    }

    @PostMapping("/save/order/multiple-items")
        public ResponseEntity<?> persistOrderItems(@RequestBody UsernameAndQuantitiesRequest usernameAndQuantitiesRequest)
    {
        Integer counter = 0;
        User user = userRepo.findUserByName(usernameAndQuantitiesRequest.getUsername()).get();
        Orders order = orderRepo.save(new Orders(new Timestamp(System.currentTimeMillis()), user));
        for (AvailableShoes item : usernameAndQuantitiesRequest.getAvailableShoes())
        {
             item = availableShoesRepo.findAvailableShoesByBrandAndColorAndModelAndPrice
                     (
                             item.getBrand(), item.getColor(), item.getModel(), item.getPrice()
                     ).get();

             Order_AvailableShoes order_availableShoes = orderAvailableShoesRepo.save
                     (
                             new Order_AvailableShoes((
                                     new OrderAvailableShoesCompositeKey(order.getId(), item.getId())),
                                     order,
                                     item,
                                     usernameAndQuantitiesRequest.getQuantities().get(counter)
                                     ));
            counter++;
        }

        return ResponseEntity.ok("Поръчка с ай ди " + order.getId() + " е направена успешно");
    }

    @PostMapping("/applydiscount")
    public ResponseEntity<?> applyDiscount(@RequestBody DiscountRequest discountRequest)
        {
            AvailableShoes availableShoes = availableShoesRepo.findAvailableShoesByBrandAndColorAndModelAndPrice
                    (
                            discountRequest.getBrand(), discountRequest.getColor(),
                            discountRequest.getModel(), discountRequest.getPrice()
                    ).get();

            availableShoes.setPrice(availableShoes.getPrice() * (discountRequest.getDiscount() / 100));

            for (User user : userRepo.findUsersBySubscription(true))
            {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("shoeshopssbg@gmail.com");
                message.setTo(user.getEmail());
                message.setSubject("discount");
                message.setText("Здравейте. Пращаме ви съобщение в връзка с нашата промоция");
                mailService.getJavaMailSender().send(message);
            }

            return ResponseEntity.ok("Цената на обувки " + discountRequest.getBrand() + " " +
                                        discountRequest.getModel() + " " + "беше променена и абонирните " +
                                        "потребители получиха съобщение за това");

        }

        @GetMapping("/pages")
        public ResponseEntity<?> getMenuPages(@RequestParam(defaultValue = "2") int perPage,
                                              @RequestParam(defaultValue = "1") int currentPage,
                                              @RequestParam(defaultValue = "") String filter)
        {
            Pageable pageable = PageRequest.of(currentPage - 1, perPage);
            Page<AvailableShoes> availableShoesPage = availableShoesRepo.
                    filterShoes(filter, pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", currentPage);
            response.put("totalElements", availableShoesPage.getTotalElements());
            response.put("totalPages", availableShoesPage.getTotalPages());
            response.put("availableShoes", availableShoesPage.getContent());
            return ResponseEntity.ok(response);
        }
        
}

