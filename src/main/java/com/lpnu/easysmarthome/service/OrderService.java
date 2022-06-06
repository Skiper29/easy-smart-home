package com.lpnu.easysmarthome.service;

import com.lpnu.easysmarthome.model.Order;
import com.lpnu.easysmarthome.model.enums.ControlFunctions;
import com.lpnu.easysmarthome.model.enums.SecurityFunctions;
import com.lpnu.easysmarthome.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final int PRICE_SQUARE_PER_METER = 50;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        order.setPrice(calcPrice(order));
        orderRepository.save(order);
    }

    private Double calcPrice(Order order) {
        return PRICE_SQUARE_PER_METER * order.getSquareMeter() * order.getBuildingType().getCoefficient() +
                order.getControlFunctions().stream().mapToDouble(ControlFunctions::getPrice).sum() +
                order.getSecurityFunctions().stream().mapToDouble(SecurityFunctions::getPrice).sum();
    }
}
