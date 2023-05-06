package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.PurposeDto;
import org.challenger.storageservice.service.PurposeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/purpose")
@CrossOrigin(origins = "http://localhost:3000")
public class PurposeController {

    private final PurposeService purposeService;

    @GetMapping
    public List<PurposeDto> findAll() {
        return purposeService.findAll();
    }

    @GetMapping("/{id}")
    public PurposeDto findById(@PathVariable final String id) {
        return purposeService.findById(id);
    }

    @PostMapping
    public PurposeDto save(@RequestBody final PurposeDto purposeDto) {
        return purposeService.save(purposeDto);
    }

    @PutMapping
    public PurposeDto update(@RequestBody final PurposeDto purposeDto) {
        return purposeService.update(purposeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePurposeById(@PathVariable final String id) {
        purposeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
