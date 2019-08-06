package com.sergeystets.backoffice.api.service;

import com.sergeystets.backoffice.api.BackOfficeApiApp;
import com.sergeystets.backoffice.api.dto.BrandDto;
import com.sergeystets.backoffice.api.dto.CreateBrandDto;
import com.sergeystets.backoffice.api.dto.QuantityDto;
import com.sergeystets.backoffice.api.dto.UpdateBrandDto;
import com.sergeystets.backoffice.api.entity.Brand;
import com.sergeystets.backoffice.api.entity.Quantity;
import com.sergeystets.backoffice.api.repository.BrandRepository;
import com.sergeystets.backoffice.api.repository.QuantityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackOfficeApiApp.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class BrandServiceTest {

    private static final String BRAND_NAME = "name";
    private static final BigInteger BRAND_ID = BigInteger.ONE;
    private static final BigInteger NON_EXISTING_BRAND_ID = BigInteger.TEN;

    @Autowired
    private BrandRepository brandRepo;
    @Autowired
    private QuantityRepository quantityRepo;
    @Autowired
    private BrandService brandService;

    @Test
    public void findOne_shouldReturnBrandById() {
        // given
        Brand brand1 = brandEntity(new BigInteger("1"), "brand1");
        Brand brand2 = brandEntity(new BigInteger("2"), "brand2");
        brandRepo.saveAll(Arrays.asList(brand1, brand2));

        // when
        Optional<BrandDto> returnedBrand = brandService.findOne(brand1.getId());

        // then
        assertThat(returnedBrand).contains(toDto(brand1));
    }

    @Test
    public void findOne_shouldReturnEmptyOptionalWhenBrandNotFound() {
        // given
        brandRepo.save(brandEntity());

        // when
        Optional<BrandDto> returnedBrand = brandService.findOne(NON_EXISTING_BRAND_ID);

        // then
        assertThat(returnedBrand).isEmpty();
    }

    @Test
    public void update_shouldReturnUpdatedBrand() {
        //given
        brandRepo.save(brandEntity());
        UpdateBrandDto update = updateBrandDto();
        String newName = update.getName();

        // when
        Optional<BrandDto> returnedBrand = brandService.update(BRAND_ID, update);

        // then
        assertThat(returnedBrand).contains(brandDto(newName));

        Optional<Brand> updatedBrand = brandRepo.findById(BRAND_ID);

        assertThat(returnedBrand).isEqualTo(toDto(updatedBrand));
    }

    @Test
    public void update_shouldReturnEmptyOptionalWhenBrandNotFound() {
        // when
        Optional<BrandDto> actual = brandService.update(NON_EXISTING_BRAND_ID, updateBrandDto());

        // then
        assertThat(actual).isEmpty();
    }

    @Test
    public void delete_shouldReturnEmptyOptionalWhenBrandNotFound() {
        // when
        Optional<BrandDto> actual = brandService.delete(NON_EXISTING_BRAND_ID);

        // then
        assertThat(actual).isEmpty();
    }

    @Test
    public void delete_shouldReturnDeletedBrand() {
        // given
        Brand brand1 = brandEntity(new BigInteger("1"), "brand1");
        Brand brand2 = brandEntity(new BigInteger("2"), "brand2");

        brandRepo.saveAll(Arrays.asList(brand1, brand2));

        // when
        Optional<BrandDto> returnedBrand = brandService.delete(brand1.getId());

        // then
        assertThat(returnedBrand).contains(toDto(brand1));
        List<Brand> remainingBrands = brandRepo.findAll();
        assertThat(remainingBrands).containsOnly(brand2);
    }

    @Test
    public void create_shouldReturnCreatedBrand() {
        // when
        BrandDto returnedBrand = brandService.create(new CreateBrandDto(BRAND_NAME));

        // then
        assertThat(returnedBrand).isNotNull();
        assertThat(returnedBrand.getName()).isEqualTo(BRAND_NAME);
        List<Brand> allBrands = brandRepo.findAll();
        assertThat(allBrands).containsOnly(toEntity(returnedBrand));
    }

    @Test
    public void quantities_shouldReturnQuantities() {
        //given
        LocalDateTime timeReceived = LocalDateTime.now();

        Quantity quantity1 = new Quantity()
                .setBrandId(BRAND_ID)
                .setQuantity(new BigInteger("42"))
                .setTimeReceived(timeReceived);

        Quantity quantity2 = new Quantity()
                .setBrandId(BRAND_ID)
                .setQuantity(new BigInteger("100"))
                .setTimeReceived(timeReceived);

        brandRepo.save(brandEntity());
        quantityRepo.saveAll(Arrays.asList(quantity1, quantity2));

        // when
        List<QuantityDto> returnedQuantities = brandService.quantities();

        // then
        QuantityDto expectedQuantity = new QuantityDto(BRAND_ID, BRAND_NAME, new BigInteger("142"));

        assertThat(returnedQuantities).containsOnly(expectedQuantity);
    }

    private static Brand toEntity(BrandDto brand) {
        return new Brand().setId(brand.getId()).setName(brand.getName());
    }

    private static BrandDto toDto(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName());
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static Optional<BrandDto> toDto(Optional<Brand> brand) {
        return brand.map(BrandServiceTest::toDto);
    }

    private static UpdateBrandDto updateBrandDto() {
        return new UpdateBrandDto("new brand");
    }

    private static Brand brandEntity() {
        return new Brand().setId(BRAND_ID).setName(BRAND_NAME);
    }

    private static Brand brandEntity(BigInteger id, String name) {
        return new Brand().setId(id).setName(name);
    }

    private static BrandDto brandDto(String name) {
        return new BrandDto(BRAND_ID, name);
    }
}
