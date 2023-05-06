package org.challenger.storageservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.PurposeDto;
import org.challenger.storageservice.exception.PurposeNotFoundException;
import org.challenger.storageservice.model.Purpose;
import org.challenger.storageservice.repository.PurposeRepository;
import org.challenger.storageservice.service.PurposeService;
import org.challenger.storageservice.service.mapper.PurposeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public PurposeDto save(final PurposeDto purposeDto) {
        purposeDto.setToken(purposeDto.getName().toLowerCase());
        final Purpose purpose = purposeMapper.map(purposeDto);
        return purposeMapper.map(purposeRepository.save(purpose));
    }

    @Override
    public List<PurposeDto> findAll() {
        return purposeRepository.findAll().stream().map(purposeMapper::map).collect(Collectors.toList());
    }

    @Override
    public PurposeDto findByToken(final String token) {

        return purposeMapper.map(purposeRepository.findByToken(token)
            .orElseThrow(PurposeNotFoundException.withToken(token)));
    }

    @Override
    public PurposeDto findById(final String id) {
        return purposeMapper.map(purposeRepository.findById(id)
            .orElseThrow(PurposeNotFoundException.withId(id)));
    }

    @Override
    public PurposeDto update(final PurposeDto purposeDto) {
        return purposeMapper.map(purposeRepository.save(purposeMapper.map(purposeDto)));
    }

    @Override
    public void deleteById(final String id) {
        purposeRepository.deleteById(id);
    }
}
