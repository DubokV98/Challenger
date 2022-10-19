package org.challenger.storageservice.repository;

import org.challenger.storageservice.model.Brand;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Repository
public interface BrandRepository extends NoSqlBaseRepository<Brand> {

    /**
     * find by token
     *
     * @param token - token
     * @return the entity with the given token or Optional#empty() if none found.
     */
    @Query("{token: '?0'}")
    Optional<Brand> findByToken(final String token);
}
