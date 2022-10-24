package org.challenger.storageservice.service.impl;

import lombok.AllArgsConstructor;
import org.challenger.common.dto.MotorcycleDto;
import org.challenger.common.dto.OrderDto;
import org.challenger.common.dto.embedded.Address;
import org.challenger.common.dto.embedded.LineItem;
import org.challenger.common.enums.OrderStatus;
import org.challenger.storageservice.exception.OrderNotFoundException;
import org.challenger.storageservice.model.Order;
import org.challenger.storageservice.repository.OrderRepository;
import org.challenger.storageservice.service.MotorcycleService;
import org.challenger.storageservice.service.OrderService;
import org.challenger.storageservice.service.mapper.LineItemMapper;
import org.challenger.storageservice.service.mapper.OrderMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
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
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final OrderRepository orderRepository;
    private final MotorcycleService motorcycleService;
    private final LineItemMapper lineItemMapper;
    private final OrderMapper orderMapper;

    private final MongoTemplate mongoTemplate;

    @Override
    public OrderDto save(final OrderDto orderDto) {
        orderDto.setOrderDate(LocalDateTime.now());
        return orderMapper.map(orderRepository.save(orderMapper.map(orderDto)));
    }

    @Override
    public Optional<Order> findById(final String id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderDto findOrderDtoById(final String id) {
        return orderMapper.map(findById(id).orElseThrow(OrderNotFoundException.withId(id)));
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
    public void deliveryConfirmation(final String id) {
        final Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("_id").is(id));
        final Update updateQuery = new Update();
        updateQuery.set("status", OrderStatus.DELIVERED);

        mongoTemplate.updateFirst(findQuery, updateQuery, Order.class);
    }

    @Override
    public void orderConfirmation(final String orderId, final String localDateTime) {
        final OrderDto orderDto = findOrderDtoById(orderId);
        orderDto.setDeliveryDate(LocalDateTime.parse(localDateTime, dateTimeFormatter));
        orderDto.setStatus(OrderStatus.READY_FOR_PAYMENT);
        save(orderDto);
    }

    @Override
    public void addLineItemToOrderProcess(final String orderId, final String lineItemId) {
        final Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            final Order order = orderOptional.get();
            addLineItemToOrder(order, lineItemId);

            final Double updatedSubTotal = countSubTotal(order);
            order.setSubTotal(updatedSubTotal);
            orderRepository.save(order);
        }
        //TODO create order if optional is not present and process
    }

    @Override
    public OrderDto create(final String userId) {
        final Order order = new Order();
        order.setLineItems(new HashSet<>());
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

    @Override
    public OrderDto addAddress(final String orderId, final Address address) {
        final OrderDto orderDto = findOrderDtoById(orderId);
        orderDto.setShippingAddress(address);
        save(orderDto);
        return orderDto;
    }

    @Override
    public void payForOrder(final String orderId, final String userId) {
        final OrderDto orderDto = findOrderDtoById(orderId);
        if (orderDto.getStatus().equals(OrderStatus.READY_FOR_PAYMENT)) {
            if (stubPay(userId)) {
                updateOrderField("_id", orderId, "status", OrderStatus.PAID.toString());
            }
        }
    }

    private LineItem findLineItemInOrderById(final Order order, final String lineItemId) {
        for (final LineItem lineItem : order.getLineItems()) {
            if (lineItem.getId().equals(lineItemId)) {
                return lineItem;
            }
        }
        return null;
    }

    private Double countSubTotal(final Order order) {
        double subTotal = 0;
        for (final LineItem lineItem : order.getLineItems()) {
            subTotal += lineItem.getPricing().getRetail() * lineItem.getQuantity();
        }
        return subTotal;
    }

    private void addLineItemToOrder(final Order order, final String lineItemId) {
        final LineItem lineItem = findLineItemInOrderById(order, lineItemId);

        if (lineItem == null) {
            final MotorcycleDto motorcycleDto = motorcycleService.findById(lineItemId);
            final LineItem newLineItem = lineItemMapper.map(motorcycleDto);
            newLineItem.setQuantity(1);
            order.getLineItems().add(newLineItem);
        } else {
            lineItem.setQuantity(lineItem.getQuantity() + 1);
        }
    }

    private boolean stubPay(final String userId) {
        return true;
    }

    private void updateOrderField(final String findKey, final String findValue,
                                  final String updateKey, final String updateValue) {
        final Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where(findKey).is(findValue));
        final Update updateQuery = new Update();
        updateQuery.set(updateKey, updateValue);

        mongoTemplate.updateFirst(findQuery, updateQuery, Order.class);
    }
}
