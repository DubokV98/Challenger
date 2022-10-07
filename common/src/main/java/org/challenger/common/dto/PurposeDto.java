package org.challenger.common.dto;

import lombok.Builder;
import lombok.Data;
import org.challenger.common.mapper.MappedDto;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@Data
@Builder
public class PurposeDto implements MappedDto {
    private String id;

    private String name;
    private String token;
    private String description;
}
