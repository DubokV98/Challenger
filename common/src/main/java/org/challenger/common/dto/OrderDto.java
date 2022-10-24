package org.challenger.common.dto;

import lombok.Data;
import org.challenger.common.dto.embedded.Address;
import org.challenger.common.dto.embedded.LineItem;
import org.challenger.common.enums.OrderStatus;
import org.challenger.common.mapper.MappedDto;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Data
public class OrderDto implements MappedDto {
    private String id;

    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    private String userId;

    private Set<LineItem> lineItems;
    private Address shippingAddress;
    private Double subTotal;
    private OrderStatus status;
}
