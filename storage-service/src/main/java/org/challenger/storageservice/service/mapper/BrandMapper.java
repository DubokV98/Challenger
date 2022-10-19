package org.challenger.storageservice.service.mapper;

import org.challenger.common.dto.BrandDto;
import org.challenger.common.mapper.Mapper;
import org.challenger.storageservice.model.Brand;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Component
public class BrandMapper extends Mapper<Brand, BrandDto> {
    /**
     * Override original method for transfer id from Brand dto to Brand
     *
     * @param from source object
     * @param to   target object after the conversion
     */
    public void customConversion(final Brand from, final Brand to) {
        Optional.ofNullable(from.getId()).ifPresent(to::setId);
    }
}
