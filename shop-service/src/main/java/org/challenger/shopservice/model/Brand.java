package org.challenger.shopservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@Data
@Builder
@Document("brand")
public class Brand {
    @Id
    private String id;

    private String token;
    private String name;
    private String country;

    private List<Purpose> motoPurposes;
}
