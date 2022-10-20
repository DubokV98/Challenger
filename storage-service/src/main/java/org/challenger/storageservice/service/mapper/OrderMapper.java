package org.challenger.storageservice.service.mapper;

import org.challenger.common.dto.OrderDto;
import org.challenger.common.mapper.Mapper;
import org.challenger.storageservice.model.Order;
import org.springframework.stereotype.Component;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Component
public class OrderMapper extends Mapper<Order, OrderDto> {
}
