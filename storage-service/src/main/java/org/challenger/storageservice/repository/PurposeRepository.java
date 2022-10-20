package org.challenger.storageservice.repository;

import org.challenger.storageservice.model.Purpose;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@Repository
public interface PurposeRepository extends NoSqlBaseRepository<Purpose> {

    /**
     * find by token
     *
     * @param token - token
     * @return the entity with the given token or Optional#empty() if none found.
     */
    @Query("{token: '?0'}")
    Optional<Purpose> findByToken(String token);
}
