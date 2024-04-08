package com.huce.edu.services;

import com.huce.edu.entities.OrderEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.dto.OrderDto;

public interface OrderService {
    OrderEntity add(OrderDto orderDto, UserEntity user);
}
