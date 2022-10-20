package org.challenger.storageservice.service.impl;

import lombok.AllArgsConstructor;
import org.challenger.common.dto.BrandDto;
import org.challenger.storageservice.exception.BrandNotFoundException;
import org.challenger.storageservice.model.Brand;
import org.challenger.storageservice.repository.BrandRepository;
import org.challenger.storageservice.service.BrandService;
import org.challenger.storageservice.service.PurposeService;
import org.challenger.storageservice.service.mapper.BrandMapper;
import org.springframework.stereotype.Service;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final PurposeService purposeService;
    private final BrandMapper brandMapper;

    @Override
    public BrandDto save(final BrandDto brandDto) {
        final Brand brand = brandMapper.map(brandDto);
        return brandMapper.map(brandRepository.save(brand));
    }

    @Override
    public BrandDto findByToken(final String token) {
        return brandMapper.map(brandRepository.findByToken(token)
            .orElseThrow(BrandNotFoundException.withToken(token)));
    }

    @Override
    public BrandDto findById(final String id) {
        return brandMapper.map(brandRepository.findById(id)
            .orElseThrow(BrandNotFoundException.withId(id)));
    }

    @Override
    public BrandDto addPurposes(final BrandDto brandDto) {
        final Brand brand = brandRepository.findById(brandDto.getId())
            .orElseThrow(BrandNotFoundException.withId(brandDto.getId()));
        brand.getMotoPurposes().addAll(brandDto.getMotoPurposes());

        return save(brandMapper.map(brand));
    }

    @Override
    public BrandDto update(final BrandDto brandDto) {
        return brandMapper.map(brandRepository.save(brandMapper.map(brandDto)));
    }
}
