package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.MotorcycleDto;
import org.challenger.storageservice.service.MotorcycleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/motorcycle")
public class MotorcycleController {
    private final MotorcycleService motorcycleService;

    @PostMapping
    public MotorcycleDto save(@RequestBody final MotorcycleDto motorcycleDto) {
        return motorcycleService.save(motorcycleDto);
    }

    @GetMapping("/find-all")
    public List<MotorcycleDto> findAll() {
        return motorcycleService.findAll();
    }

    @GetMapping("/{token}")
    public MotorcycleDto findAllByToken(final @PathVariable String token) {
        return motorcycleService.findByToken(token);
    }

    @GetMapping("/find-all-by-brand-id")
    public List<MotorcycleDto> findAllByBrandId(final @RequestParam String brandId) {
        return motorcycleService.findAllByBrandId(brandId);
    }

    @GetMapping("/find-by-id")
    public MotorcycleDto findById(final @RequestParam String id) {
        return motorcycleService.findById(id);
    }

    @GetMapping("/find-all-by-purpose-id")
    public List<MotorcycleDto> findAllByPurposeId(final @RequestParam String purposeId) {
        return motorcycleService.findAllByPurposeId(purposeId);
    }

    @GetMapping("/find-all-by-purpose-and-brand")
    public List<MotorcycleDto> findAllByPurposeId(final @RequestParam String purposeId, final @RequestParam String brandId) {
        return motorcycleService.findAllByPurposeAndBrand(purposeId, brandId);
    }

    @PutMapping
    private MotorcycleDto update(@RequestBody final MotorcycleDto motorcycleDto) {
        return motorcycleService.update(motorcycleDto);
    }

    @GetMapping("/find-most-viewed")
    private List<MotorcycleDto> findAllSortedByTotalReviewsDesc() {
        return motorcycleService.findAllSortedByTotalReviewsDesc();
    }
}
