package org.challenger.storageservice.service.impl;

import lombok.AllArgsConstructor;
import org.challenger.common.dto.OrderDto;
import org.challenger.common.enums.OrderStatus;
import org.challenger.storageservice.exception.OrderNotFoundException;
import org.challenger.storageservice.model.Order;
import org.challenger.storageservice.repository.OrderRepository;
import org.challenger.storageservice.service.MotorcycleService;
import org.challenger.storageservice.service.OrderService;
import org.challenger.storageservice.service.mapper.OrderMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final MongoTemplate mongoTemplate;
    private final MotorcycleService motorcycleService;

    @Override
    public OrderDto save(final OrderDto orderDto) {
        orderDto.setOrderDate(LocalDateTime.now());
        return orderMapper.map(orderRepository.save(orderMapper.map(orderDto)));
    }

    @Override
    public OrderDto findById(final String id) {
        return orderMapper.map(orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException.withId(id)));
    }

    @Override
    public OrderDto update(final OrderDto orderDto) {
        return orderMapper.map(orderRepository.save(orderMapper.map(orderDto)));
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
            .map(orderMapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByUserId(final String userId) {
        return orderRepository.findAllByUserId(userId)
            .stream()
            .map(orderMapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public void confirmDelivery(final String id) {
        final Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("_id").is(id));
        final Update updateQuery = new Update();
        updateQuery.set("status", OrderStatus.DELIVERED);

        mongoTemplate.updateFirst(findQuery, updateQuery, Order.class);
    }

    @Override
    public void addLineItemToOrder(final String orderId, final String lineId) {
        final Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            final Order order = orderOptional.get();
            order.getLineItems().add(lineId);
        }

    }

    @Override
    public OrderDto create(final String userId) {
        final Order order = new Order();
        order.setLineItems(new ArrayList<>());
        order.setStatus(OrderStatus.CREATED);
        order.setOrderDate(LocalDateTime.now());
        order.setSubTotal(0d);
        order.setUserId(userId);

        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public OrderDto findByUserIdAndStatus(final String userId, final OrderStatus status) {
        final Optional<Order> orderOptional = orderRepository.findAllByUserIdAndStatus(userId, status);
        if (orderOptional.isPresent()) {
            return orderMapper.map(orderOptional.get());
        }
        if (status == OrderStatus.CREATED) {
            return create(userId);
        }
        return null;
    }
}
