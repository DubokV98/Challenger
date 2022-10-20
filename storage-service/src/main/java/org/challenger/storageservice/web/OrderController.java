package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.OrderDto;
import org.challenger.storageservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
