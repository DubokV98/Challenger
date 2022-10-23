package org.challenger.storageservice.repository;

import org.challenger.common.enums.OrderStatus;
import org.challenger.storageservice.model.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Repository
public interface OrderRepository extends NoSqlBaseRepository<Order> {
    /**
     * Find all by user id
     *
     * @param userId - user id
     * @return list of order dto
     */
    @Query("{userId: ?0}")
    List<Order> findAllByUserId(String userId);

    /**
     * find by user id and status
     *
     * @param userId - user id
     * @param status - order status
     * @return order dto
     */
    @Query("{userId: ?0, status: ?1}")
    Optional<Order> findAllByUserIdAndStatus(String userId, OrderStatus status);
}
