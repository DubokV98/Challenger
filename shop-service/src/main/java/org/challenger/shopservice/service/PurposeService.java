package org.challenger.shopservice.service;

import org.challenger.common.dto.PurposeDto;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
public interface PurposeService {
    PurposeDto save(PurposeDto purposeDto);

    PurposeDto findByName(String name);
}
