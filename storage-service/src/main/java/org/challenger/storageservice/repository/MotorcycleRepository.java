package org.challenger.storageservice.repository;

import org.challenger.storageservice.model.Motorcycle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Repository
public interface MotorcycleRepository extends NoSqlBaseRepository<Motorcycle> {
    /**
     * Find all by purpose id
     *
     * @param purposeId - purpose id
     * @return list of motorcycle with purpose
     */
    @Query("{purposeId: ?0}")
    List<Motorcycle> findAllByPurpose(String purposeId);

    /**
     * Find all by brand id
     *
     * @param brandId - brand id
     * @return list of motorcycle with brand
     */
    @Query("{brandId: ?0}")
    List<Motorcycle> findAllByBrand(String brandId);

    /**
     * Find by token
     *
     * @param token - token
     * @return optional of entity
     */
    @Query("{token: ?0}")
    Optional<Motorcycle> findAllByToken(String token);

    /**
     * Find all by brand id and purpose id
     *
     * @param brandId   - brand id
     * @param purposeId - purpose id
     * @return list of motorcycle with brand and purpose
     */
    @Query("{purposeId: ?0, branId: ?1}")
    List<Motorcycle> findAllByPurposeAndBrand(String purposeId, String brandId);
}
