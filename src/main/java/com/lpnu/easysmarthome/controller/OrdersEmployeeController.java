package com.lpnu.easysmarthome.controller;

import com.lpnu.easysmarthome.model.Order;
import com.lpnu.easysmarthome.model.User;
import com.lpnu.easysmarthome.repository.OrderRepository;
import com.lpnu.easysmarthome.repository.UserRepository;
import com.lpnu.easysmarthome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrdersEmployeeController {

    final OrderRepository orderRepository;
    final OrderService orderService;
    final UserRepository userRepository;

    @Autowired
    public OrdersEmployeeController(OrderRepository orderRepository, UserRepository userRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("freeOrders", orderRepository.findByEmployeeIsNull());
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("myOrders", orderRepository.findByEmployeeAndActiveIsTrue(user));
        model.addAttribute("otherOrders", orderRepository.findByEmployeeAndActiveIsFalse(user));
        return "/orderListEmployee";
    }

    @PostMapping("/add/{id}")
    public String addOrder(@PathVariable("id") Order order, @RequestParam("message") String message) {
        order.setEmployee(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        order.setEmployeeMessage(message);
        order.setActive(true);
        orderService.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/done/{id}")
    public String markDoneOrder(@PathVariable("id") Order order) {
        order.setActive(false);
        orderService.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/return/{id}")
    public String returnOrder(@PathVariable("id") Order order) {
        order.setActive(true);
        orderService.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/reject/{id}")
    public String rejectOrder(@PathVariable("id") Order order) {
        order.setActive(false);
        order.setEmployeeMessage(null);
        order.setEmployee(null);
        orderService.save(order);
        return "redirect:/orders";
    }
}
