package org.challenger.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenger.common.mapper.MappedDto;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@Data
@NoArgsConstructor
public class PurposeDto implements MappedDto {
    private String id;

    private String name;
    private String token;
    private String description;
}
