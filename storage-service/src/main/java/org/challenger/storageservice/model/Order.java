package org.challenger.storageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenger.common.dto.embedded.Address;
import org.challenger.common.dto.embedded.LineItem;
import org.challenger.common.enums.OrderStatus;
import org.challenger.common.mapper.MappedEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author u.dubok
 * @since 10/9/2022
 */
@Document(collection = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order implements MappedEntity {
    @Id
    private String id;

    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    private String userId;

    private Set<LineItem> lineItems;
    private Address shippingAddress;
    private Double subTotal;
    private OrderStatus status;
}
