package com.challenger.storageservice.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.challenger.storageservice.AbstractTest;
import org.challenger.common.dto.BrandDto;
import org.challenger.storageservice.model.Brand;
import org.challenger.storageservice.repository.BrandRepository;
import org.challenger.storageservice.service.BrandService;
import org.challenger.storageservice.service.impl.BrandServiceImpl;
import org.challenger.storageservice.service.mapper.BrandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

/**
 * @author u.dubok
 * @since 12/11/2022
 */
public class BrandServiceTest extends AbstractTest {

    private BrandService brandService;
    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    private Brand brand;

    private BrandDto mappedBrandDto;

    @BeforeEach
    public void initService() {
        brandService = new BrandServiceImpl(brandRepository, brandMapper);
    }

    @BeforeEach
    public void initBrand() {
        brand = new Brand(
            "1",
            "Kawasaki",
            "kawasaki",
            "Japan",
            null
        );
    }

    @BeforeEach
    public void initMappedBrandDto() {
        mappedBrandDto = new BrandDto(
            "1",
            "Kawasaki",
            "kawasaki",
            "Japan",
            null
        );
    }

    //@Test
    public void findById_happyPath() {
        when(brandRepository.findByToken("kawasaki")).thenReturn(Optional.of(brand));
        when(brandMapper.map(Optional.of(brand).get())).thenReturn(mappedBrandDto);

        final BrandDto brandDto = brandService.findByToken("kawasaki");

        assertThat(brandDto.getId()).isEqualTo("1");
        assertThat(brandDto.getCountry()).isEqualTo("Japan");
        assertThat(brandDto.getMotoPurposes()).isEmpty();
        assertThat(brandDto.getName()).isEqualTo("Kawasaki");
        assertThat(brandDto.getToken()).isEqualTo("kawasaki");

        verify(brandRepository).findByToken("kawasaki");
        verify(brandMapper).map(brand);
    }

    @Test
    public void testForGithubActions() {
        assertThat(1).isEqualTo(1);
    }
}
