package org.challenger.storageservice.service.mapper;

import org.challenger.common.dto.MotorcycleDto;
import org.challenger.common.dto.embedded.LineItem;
import org.springframework.stereotype.Component;

/**
 * @author u.dubok
 * @since 10/24/2022
 */
@Component
public class LineItemMapper {
    /**
     * map Motorcycle dto to LineItem
     *
     * @param motorcycleDto - motorcycle dto
     * @return line item
     */
    public LineItem map(final MotorcycleDto motorcycleDto) {
        final LineItem lineItem = new LineItem();

        lineItem.setId(motorcycleDto.getId());
        lineItem.setModel(motorcycleDto.getModel());
        lineItem.setPricing(motorcycleDto.getPricing());
        lineItem.setToken(motorcycleDto.getToken());
        lineItem.setYear(motorcycleDto.getYear());
        return lineItem;
    }
}
