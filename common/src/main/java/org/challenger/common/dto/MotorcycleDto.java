package org.challenger.common.dto;

import lombok.Data;
import org.challenger.common.dto.embedded.EngineTransmission;
import org.challenger.common.dto.embedded.Features;
import org.challenger.common.dto.embedded.PriceHistory;
import org.challenger.common.dto.embedded.Pricing;
import org.challenger.common.dto.embedded.Statistic;
import org.challenger.common.mapper.MappedDto;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Data
public class MotorcycleDto implements MappedDto {
    private String id;

    private String token;

    private String model;
    private String performance;
    private Integer year;
    private String description;

    private String brandId;
    private List<String> purposeId;

    private EngineTransmission engineTransmission;
    private Features features;
    private Statistic statistic;
    private Pricing pricing;

    private List<PriceHistory> priceHistory;
}
