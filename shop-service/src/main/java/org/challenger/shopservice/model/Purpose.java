package org.challenger.shopservice.model;

import lombok.Builder;
import lombok.Data;
import org.challenger.common.mapper.MappedEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author u.dubok
 * @since 10/7/2022
 */

@Builder
@Data
@Document("purpose")
public class Purpose implements MappedEntity {
    @Id
    private String id;

    private String name;
    private String token;
    private String description;
}
