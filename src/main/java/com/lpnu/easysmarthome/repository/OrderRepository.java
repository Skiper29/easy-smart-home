package com.lpnu.easysmarthome.repository;

import com.lpnu.easysmarthome.model.Order;
import com.lpnu.easysmarthome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(User user);

    List<Order> findByEmployeeIsNull();
    List<Order> findByEmployeeAndActiveIsTrue(User user);
    List<Order> findByEmployeeAndActiveIsFalse(User user);
}
