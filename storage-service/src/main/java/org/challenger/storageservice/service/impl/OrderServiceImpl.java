package org.challenger.storageservice.service.impl;

import lombok.AllArgsConstructor;
import org.challenger.common.dto.OrderDto;
import org.challenger.storageservice.exception.OrderNotFoundException;
import org.challenger.storageservice.repository.OrderRepository;
import org.challenger.storageservice.service.OrderService;
import org.challenger.storageservice.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
}
