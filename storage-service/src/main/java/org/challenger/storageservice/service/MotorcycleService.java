package org.challenger.storageservice.service;

import org.challenger.common.dto.MotorcycleDto;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
public interface MotorcycleService {
    /**
     * Save motorcycle dto
     *
     * @param motorcycleDto - dto to save
     * @return saved motorcycle dto
     */
    MotorcycleDto save(MotorcycleDto motorcycleDto);

    /**
     * Find all
     *
     * @return list of motorcycle dto
     */
    List<MotorcycleDto> findAll();

    /**
     * Find by token
     *
     * @param token - token
     * @return motorcycle dto
     */
    MotorcycleDto findByToken(String token);

    /**
     * Find all by brand id
     *
     * @param brandId - brand id
     * @return list of motorcycle with brand
     */
    List<MotorcycleDto> findAllByBrandId(String brandId);

    /**
     * Find all by purpose id
     *
     * @param purposeId - purpose id
     * @return list of motorcycle with purpose
     */
    List<MotorcycleDto> findAllByPurposeId(String purposeId);

    /**
     * Find by id
     *
     * @param id - id
     * @return motorcycle dto
     */
    MotorcycleDto findById(String id);

    /**
     * Find all by brand id and purpose id
     *
     * @param brandId   - brand id
     * @param purposeId - purpose id
     * @return list of motorcycle with brand and purpose
     */
    List<MotorcycleDto> findAllByPurposeAndBrand(String purposeId, String brandId);
}
