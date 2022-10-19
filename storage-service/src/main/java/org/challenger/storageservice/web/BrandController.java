package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.BrandDto;
import org.challenger.storageservice.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/brand")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/{token}")
    public BrandDto findByToken(@PathVariable final String token) {
        return brandService.findByToken(token);
    }

    @GetMapping("/find-by-id")
    public BrandDto findById(@RequestParam final String id) {
        return brandService.findById(id);
    }

    @PostMapping
    public BrandDto save(@RequestBody final BrandDto brandDto) {
        return brandService.save(brandDto);
    }

    @PostMapping("/add-purposes")
    public BrandDto addPurpose(@RequestBody final BrandDto brandDto) {
        return brandService.addPurposes(brandDto);
    }
}

