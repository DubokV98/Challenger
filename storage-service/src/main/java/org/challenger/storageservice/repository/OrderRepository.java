package org.challenger.storageservice.repository;

import org.challenger.storageservice.model.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
