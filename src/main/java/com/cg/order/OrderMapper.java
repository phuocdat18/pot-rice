package com.cg.order;

import com.cg.order.dto.OrderCreationParam;
import com.cg.order.dto.OrderResult;
import com.cg.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order toEntity(OrderCreationParam creationParam) {
        return new Order();
    }


    public OrderResult toDTO(Order entity) {
        return new OrderResult();

    }

    public List<OrderResult> toDTOList(List<Order> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
