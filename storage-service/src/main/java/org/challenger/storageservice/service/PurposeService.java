package org.challenger.storageservice.service;

import org.challenger.common.dto.PurposeDto;

import java.util.List;

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
     * find all
     *
     * @return list of purpose dtos
     */
    List<PurposeDto> findAll();

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

    /**
     * Update purpose dto
     *
     * @param purposeDto - purpose dto for update
     * @return updated purpose dto
     */
    PurposeDto update(PurposeDto purposeDto);

    /**
     * Delete purpose dto by id
     *
     * @param id id purpose
     */
    void deleteById(String id);
}
