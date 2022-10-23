package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.OrderDto;
import org.challenger.common.enums.OrderStatus;
import org.challenger.storageservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto save(@RequestBody final OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @GetMapping("/find-all")
    public List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/find-by-id")
    public OrderDto findById(@RequestParam final String id) {
        return orderService.findById(id);
    }

    @PutMapping
    private OrderDto update(@RequestBody final OrderDto orderDto) {
        return orderService.update(orderDto);
    }

    @GetMapping("/find-by-user-and-status")
    private OrderDto findByUserAndStatus(@RequestParam final String userId, @RequestParam final OrderStatus status) {
        return orderService.findByUserIdAndStatus(userId, status);
    }

    @PutMapping("/{orderId}/add-line")
    public void addLineItems(@PathVariable final String orderId, @RequestBody final String lineId) {
        orderService.addLineItemToOrder(orderId, lineId);
    }

    @PutMapping("/{id}/confirmation")
    public void confirmDelivery(@PathVariable final String id) {
        orderService.confirmDelivery(id);
    }

    @PostMapping("/create")
    public OrderDto createForUser(@RequestParam final String userId) {
        return orderService.create(userId);
    }
}
