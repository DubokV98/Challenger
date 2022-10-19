package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.storageservice.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/order")
public class OrderController {
    private final OrderService orderService;
}
