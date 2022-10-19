package org.challenger.storageservice.service;

import org.challenger.common.dto.PurposeDto;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
public interface PurposeService {
    /**
     * save the purpose
     *
     * @param purposeDto - purpose
     * @return return saved purpose dto
     */
    PurposeDto save(PurposeDto purposeDto);

    /**
     * find purpose by token
     *
     * @param token - token
     * @return - purpose dto
     */
    PurposeDto findByToken(String token);

    /**
     * find purpose by id
     *
     * @param id - id
     * @return - purpose dto
     */
    PurposeDto findById(String id);
}
