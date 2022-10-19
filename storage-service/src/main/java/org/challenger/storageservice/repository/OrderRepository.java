package org.challenger.storageservice.repository;

import org.challenger.storageservice.model.Order;
import org.springframework.stereotype.Repository;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Repository
public interface OrderRepository extends NoSqlBaseRepository<Order> {
}
