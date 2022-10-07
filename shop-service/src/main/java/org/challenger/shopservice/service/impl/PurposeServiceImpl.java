package org.challenger.shopservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.PurposeDto;
import org.challenger.shopservice.mapper.PurposeMapper;
import org.challenger.shopservice.model.Purpose;
import org.challenger.shopservice.repository.PurposeRepository;
import org.challenger.shopservice.service.PurposeService;
import org.springframework.stereotype.Service;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@Service
@RequiredArgsConstructor
public class PurposeServiceImpl implements PurposeService {
    private final PurposeRepository purposeRepository;
    private final PurposeMapper purposeMapper;

    @Override
    public PurposeDto save(final PurposeDto purposeDto){
        final Purpose purpose = purposeMapper.map(purposeDto);
        return purposeMapper.map(purposeRepository.save(purpose));
    }

    @Override
    public PurposeDto findByName(final String name){
        return purposeMapper.map(purposeRepository.findByName(name));
    }
}
