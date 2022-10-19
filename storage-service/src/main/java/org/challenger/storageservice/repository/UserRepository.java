package org.challenger.storageservice.repository;

import org.challenger.storageservice.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Repository
public interface UserRepository extends NoSqlBaseRepository<User> {

    /**
     * find by username
     *
     * @param username - username
     * @return the entity with the given token or Optional#empty() if none found.
     */
    @Query("{username: ?0}")
    Optional<User> findByUsername(String username);
}
