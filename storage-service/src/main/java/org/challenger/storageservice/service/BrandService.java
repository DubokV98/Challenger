package org.challenger.storageservice.service;

import org.challenger.common.dto.BrandDto;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
public interface BrandService {
    /**
     * save brand dto
     *
     * @param brandDto - brand dto to save
     * @return saved brandDto
     */
    BrandDto save(BrandDto brandDto);

    /**
     * find brand by token
     *
     * @param token - token
     * @return founded brandDto
     */
    BrandDto findByToken(String token);

    /**
     * find brand by id
     *
     * @param id - id
     * @return founded brandDto
     */
    BrandDto findById(String id);

    /**
     * add purposes to brand
     *
     * @param brandDto - contain _id and list of purposes
     * @return brand dto with list of purposes
     */
    BrandDto addPurposes(BrandDto brandDto);

    /**
     * Update Brand dto
     *
     * @param brandDto - Brand dto for update
     * @return updated brand dto
     */
    BrandDto update(BrandDto brandDto);
}
