package org.challenger.storageservice.service.impl;

import lombok.AllArgsConstructor;
import org.challenger.common.dto.MotorcycleDto;
import org.challenger.storageservice.exception.MotorcycleNotFoundException;
import org.challenger.storageservice.model.Motorcycle;
import org.challenger.storageservice.repository.MotorcycleRepository;
import org.challenger.storageservice.service.MotorcycleService;
import org.challenger.storageservice.service.mapper.MotorcycleMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Service
@AllArgsConstructor
public class MotorcycleServiceImpl implements MotorcycleService {
    private final MotorcycleRepository motorcycleRepository;
    private final MotorcycleMapper motorcycleMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public MotorcycleDto save(final MotorcycleDto motorcycleDto) {
        motorcycleDto.setToken(motorcycleDto.getModel() + "-" + motorcycleDto.getYear());
        return motorcycleMapper.map(motorcycleRepository.save(motorcycleMapper.map(motorcycleDto)));
    }

    @Override
    public List<MotorcycleDto> findAll() {
        return motorcycleRepository.findAll().stream()
            .map(motorcycleMapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public MotorcycleDto findByToken(final String token) {
        final MotorcycleDto motorcycleDto = motorcycleMapper.map(motorcycleRepository.findAllByToken(token)
            .orElseThrow(MotorcycleNotFoundException.withToken(token)));
        updateStatisticTotalReviews(motorcycleDto.getId(), motorcycleDto.getStatistic().getTotalReviews() + 1);
        return motorcycleDto;
    }

    @Override
    public List<MotorcycleDto> findAllByBrandId(final String brandId) {
        return motorcycleRepository.findAllByBrand(brandId)
            .stream()
            .map(motorcycleMapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public List<MotorcycleDto> findAllByPurposeId(final String purposeId) {
        return motorcycleRepository.findAllByPurpose(purposeId)
            .stream()
            .map(motorcycleMapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public MotorcycleDto findById(final String id) {
        final MotorcycleDto motorcycleDto = motorcycleMapper.map(motorcycleRepository.findById(id)
            .orElseThrow(MotorcycleNotFoundException.withId(id)));
        updateStatisticTotalReviews(motorcycleDto.getId(), motorcycleDto.getStatistic().getTotalReviews() + 1);
        return motorcycleDto;
    }

    @Override
    public List<MotorcycleDto> findAllByPurposeAndBrand(final String purposeId, final String brandId) {
        return motorcycleRepository.findAllByPurposeAndBrand(purposeId, brandId)
            .stream()
            .map(motorcycleMapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public MotorcycleDto update(final MotorcycleDto motorcycleDto) {
        return motorcycleMapper.map(motorcycleRepository.save(motorcycleMapper.map(motorcycleDto)));
    }

    @Override
    public List<MotorcycleDto> findAllSortedByTotalReviewsDesc() {

        final Sort sort = Sort.by("statistic.totalReviews").descending();
        final Query query = new Query();
        query.with(sort);
        return mongoTemplate.find(query, Motorcycle.class).stream().map(motorcycleMapper::map)
            .collect(Collectors.toList());
    }

    private void updateStatisticTotalReviews(final String id, final Long newValue) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        final Update updateQuery = new Update();
        updateQuery.set("statistic.totalReviews", newValue);
        mongoTemplate.updateFirst(query, updateQuery, Motorcycle.class);
    }
}
