package com.cg.order;

import com.cg.location.LocationMapper;
import com.cg.location.LocationRegionRepository;
import com.cg.model.LocationRegion;
import com.cg.model.Order;
import com.cg.model.OrderStatus;
import com.cg.order.dto.OrderCreationParam;
import com.cg.order.dto.OrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rananu.shared.exceptions.NotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final LocationRegionRepository locationRegionRepository;
    private final OrderMapper orderMapper;
    private final LocationMapper locationMapper;

    @Override
    public List<OrderResult> findAll() {
        List<Order> entities = orderRepository.findAll();
        return orderMapper.toDTOList(entities);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("id ko hop le"));
    }

    @Override
    public OrderResult getById(Long id) {
        Order entity = findById(id);
        return orderMapper.toDTO(entity);

    }

    @Override
    public List<OrderResult> findAllByStatus(String status) {
        OrderStatus orderStatus = OrderStatus.parse(status);
        List<Order> entities = orderRepository.findAllByStatus(orderStatus);
        return orderMapper.toDTOList(entities);
    }


    @Override
    public List<OrderResult> findAllByUserId(Long userId) {
        List<Order> entities = orderRepository.findAllByUserId(userId);
        return orderMapper.toDTOList(entities);
    }

    @Override
    public List<?> findAllByUserIdAndStatus(Long userId, String status) {
        OrderStatus orderStatus = OrderStatus.parse(status);
        List<Order> entities = orderRepository.findAllByUserIdAndStatus(userId, orderStatus);
        return orderMapper.toDTOList(entities);
    }

    @Override
    public List<?> findAllByCreatedAt(LocalDate createdAt) {
        List<Order> entities = orderRepository.findAllByCreatedAtAndStatus(createdAt, OrderStatus.ORDER);
        return orderMapper.toDTOList(entities);
    }

    @Transactional
    public OrderResult order(Long userId, OrderCreationParam creationParam) {

        LocationRegion location = locationMapper.toEntity(creationParam.getLocationRegion());
        location = locationRegionRepository.save(location);

        Order order = orderMapper.toEntity(creationParam);
        order.setLocationRegionId(location.getId());
        order.setStatus(OrderStatus.ORDER);
        order = orderRepository.save(order);

        return orderMapper.toDTO(order);
    }
}