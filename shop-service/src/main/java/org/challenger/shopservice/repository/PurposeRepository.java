package org.challenger.shopservice.repository;

import org.challenger.shopservice.model.Purpose;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@Repository
public interface PurposeRepository extends NoSqlBaseRepository<Purpose> {

    @Query ("{name: '?0'}")
    Purpose findByName(String name);
}
