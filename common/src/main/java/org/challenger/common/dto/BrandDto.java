package org.challenger.common.dto;

import lombok.Data;
import org.challenger.common.mapper.MappedDto;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Data
public class BrandDto implements MappedDto {
    private String id;

    private String token;
    private String name;
    private String country;

    private List<String> motoPurposes;
}
