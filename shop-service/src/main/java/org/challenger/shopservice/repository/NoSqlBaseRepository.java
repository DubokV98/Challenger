package org.challenger.shopservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <T> type of document
 * @author u.dubok
 * @since 10/7/2022
 */
@NoRepositoryBean
public interface NoSqlBaseRepository <T> extends MongoRepository<T, String> {
}
