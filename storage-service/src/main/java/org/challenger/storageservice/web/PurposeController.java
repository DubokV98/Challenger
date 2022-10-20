package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.PurposeDto;
import org.challenger.storageservice.service.PurposeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/purpose")
public class PurposeController {

    private final PurposeService purposeService;

    @GetMapping("/{token}")
    public PurposeDto findByToken(@PathVariable final String token) {
        return purposeService.findByToken(token);
    }

    @GetMapping("/find-by-id")
    public PurposeDto findById(@RequestParam final String id) {
        return purposeService.findById(id);
    }

    @PostMapping
    public PurposeDto save(@RequestBody final PurposeDto purposeDto) {
        return purposeService.save(purposeDto);
    }

    @PutMapping
    private PurposeDto update(@RequestBody final PurposeDto purposeDto) {
        return purposeService.update(purposeDto);
    }
}
