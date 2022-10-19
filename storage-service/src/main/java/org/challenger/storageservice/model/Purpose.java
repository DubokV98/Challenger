package org.challenger.storageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenger.common.mapper.MappedEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author u.dubok
 * @since 10/7/2022
 */

@Data
@Document(collection = "purpose")
@NoArgsConstructor
@AllArgsConstructor
public class Purpose implements MappedEntity {
    @Id
    private String id;

    private String name;
    @Indexed(name = "purpose_token_index", unique = true)
    private String token;
    private String description;
}
