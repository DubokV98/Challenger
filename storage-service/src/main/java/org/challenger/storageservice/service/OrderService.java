package org.challenger.storageservice.service;

import org.challenger.common.dto.OrderDto;
import org.challenger.common.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Service
public interface OrderService {
    /**
     * save order dto
     *
     * @param orderDto - order dto to save
     * @return saved brandDto
     */
    OrderDto save(OrderDto orderDto);

    /**
     * find order by id
     *
     * @param id - id
     * @return founded orderDto
     */
    OrderDto findById(String id);

    /**
     * Update Brand dto
     *
     * @param orderDto - order dto for update
     * @return updated order dto
     */
    OrderDto update(OrderDto orderDto);

    /**
     * Find all
     *
     * @return list of order dto
     */
    List<OrderDto> findAll();


    /**
     * Find all by user id
     *
     * @param userId - user id
     * @return list of order dto
     */
    List<OrderDto> findAllByUserId(final String userId);

    /**
     * Confirm delivery
     *
     * @param id - order id
     */
    void confirmDelivery(String id);

    /**
     * Add line item to order
     *
     * @param orderId - order id
     * @param lineId - motorcycle id
     */
    void addLineItemToOrder(String orderId, String lineId);

    /**
     * Create new order
     *
     * @param userId - user id
     * @return created order
     */
    OrderDto create(String userId);

    /**
     * find by user id and status
     *
     * @param userId - user id
     * @param status - order status
     * @return order dto
     */
    OrderDto findByUserIdAndStatus(String userId, OrderStatus status);
}
