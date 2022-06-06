package com.lpnu.easysmarthome.controller;

import com.lpnu.easysmarthome.model.Order;
import com.lpnu.easysmarthome.model.User;
import com.lpnu.easysmarthome.repository.OrderRepository;
import com.lpnu.easysmarthome.repository.UserRepository;
import com.lpnu.easysmarthome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class OrderController {
    final OrderRepository orderRepository;
    final OrderService orderService;
    final UserRepository userRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("order", new Order());
        return "/order";
    }

    @PostMapping("/{id}/new")
    public String saveOrder(@PathVariable Long id, @Valid @ModelAttribute Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/order";
        }
        order.setActive(false);
        order.setCustomer(userRepository.findById(id).orElse(null));
        orderService.save(order);
        return "redirect:/";
    }

    @GetMapping("/{id}/all")
    public String getOrderList(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("orders", orderRepository.findByCustomer(user));
        return "/orderList";
    }


}
