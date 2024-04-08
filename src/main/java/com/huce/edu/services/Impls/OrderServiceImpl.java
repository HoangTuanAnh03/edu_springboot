package com.huce.edu.services.Impls;

import com.huce.edu.entities.OrderEntity;
import com.huce.edu.entities.ProductEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.models.dto.OrderDto;
import com.huce.edu.repositories.OrderRepo;
import com.huce.edu.repositories.ProductRepo;
import com.huce.edu.repositories.UserAccountRepo;
import com.huce.edu.services.OrderService;
import com.huce.edu.shareds.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final UserAccountRepo userAccountRepo;

    @Override
    @Transactional
    public OrderEntity add(OrderDto orderDto, UserEntity user) {
        ProductEntity product = productRepo.findFirstByPid(orderDto.getPid());

        OrderEntity newOrder = OrderEntity.create(
                0,
                user.getUid(),
                Constants.getCurrentDay(),
                orderDto.getAddress(),
                orderDto.getPid(),
                product.getPrice(),
                orderDto.getQuantity(),
                orderDto.getPhone()
        );
        orderRepo.save(newOrder);

        user.setCoin(user.getCoin()-(orderDto.getQuantity()*product.getPrice()));
        userAccountRepo.save(user);

        product.setRemain(product.getRemain()-orderDto.getQuantity());
        productRepo.save(product);

        return newOrder;
    }
}
