package com.example.order_service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @GetMapping("/status")
    public String getOrderStatus() {
        return "Order service is running and registered with Eureka!";
    }

    @GetMapping("/user-info")
    public String getUserInfoFromUserService() {
        // use service name instead of IP/port
        String response = restTemplate.getForObject("http://user-service/users/hello", String.class);
        return "Order Service received: " + response;
    }

    @GetMapping("/user-info-feign")
    public String getUserInfoFromUserServiceByFeign() {
        // use service name instead of IP/port
        String response = userClient.getHelloMessage();
        return "Order Service (via Feign) received: " + response;
    }
}

