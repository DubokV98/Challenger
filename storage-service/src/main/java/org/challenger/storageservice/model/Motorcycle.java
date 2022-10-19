package org.challenger.storageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenger.common.dto.embedded.EngineTransmission;
import org.challenger.common.dto.embedded.Features;
import org.challenger.common.dto.embedded.PriceHistory;
import org.challenger.common.dto.embedded.Pricing;
import org.challenger.common.dto.embedded.Statistic;
import org.challenger.common.mapper.MappedEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/9/2022
 */
@Document(collection = "motorcycle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorcycle implements MappedEntity {
    @Id
    private String id;

    @Indexed(name = "motorcycle_token_index", unique = true)
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
