package org.challenger.storageservice.service;

import org.challenger.common.dto.OrderDto;
import org.challenger.common.dto.embedded.Address;
import org.challenger.common.enums.OrderStatus;
import org.challenger.storageservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * @return optional order
     */
    Optional<Order> findById(String id);

    /**
     * find orderDto by id
     *
     * @param id - id
     * @return order dto
     */
    OrderDto findOrderDtoById(String id);

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
     * Confirm that order was delivered
     *
     * @param id - order id
     */
    void deliveryConfirmation(String id);

    /**
     * Confirm that order ready for paying
     *
     * @param orderId       - order id
     * @param localDateTime - string date of delivery
     */
    void orderConfirmation(String orderId, final String localDateTime);

    /**
     * Add line item to order
     *
     * @param orderId - order id
     * @param lineId  - motorcycle id
     */
    void addLineItemToOrderProcess(String orderId, String lineId);

    /**
     * Create new order
     *
     * @param userId - userId
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

    /**
     * Add address to order
     *
     * @param orderId - orderId
     * @param address - user address
     * @return orderDto
     */
    OrderDto addAddress(String orderId, Address address);

    /**
     * Pay for order
     *
     * @param orderId - order id
     * @param userId  - user id
     */
    void payForOrder(String orderId, String userId);
}
