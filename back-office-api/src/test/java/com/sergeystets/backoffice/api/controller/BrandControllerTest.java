package com.sergeystets.backoffice.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sergeystets.backoffice.api.dto.BrandDto;
import com.sergeystets.backoffice.api.dto.CreateBrandDto;
import com.sergeystets.backoffice.api.dto.QuantityDto;
import com.sergeystets.backoffice.api.dto.UpdateBrandDto;
import com.sergeystets.backoffice.api.service.BrandService;
import com.sergeystets.backoffice.api.util.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BrandController.class)
public class BrandControllerTest {

    private static final int NAME_MAX_LENGTH = 255;
    private static final String BRAND_NAME = "name";
    private static final BigInteger BRAND_ID = BigInteger.ONE;
    private static final String BRAND_CONTROLLER_URL = "/api/brand";
    private static final String APPLICATION_XML_UTF_8 = "application/xml;charset=UTF-8";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BrandService brandService;

    @Test
    public void createBrand_shouldReturnErrorWhenNameIsEmpty() throws Exception {
        mvc.perform(post(BRAND_CONTROLLER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(new CreateBrandDto(""))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBrand_shouldReturnErrorWhenNameIsNull() throws Exception {
        mvc.perform(post(BRAND_CONTROLLER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(new CreateBrandDto(null))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBrand_shouldReturnErrorWhenNameExceedsMaxLength() throws Exception {
        String nameExceedsMaxLength = StringUtils.repeat("n", NAME_MAX_LENGTH + 1);

        mvc.perform(post(BRAND_CONTROLLER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(new CreateBrandDto(nameExceedsMaxLength))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBrand_shouldReturnSuccessfullyCreatedBrand() throws Exception {
        String name = StringUtils.repeat('n', NAME_MAX_LENGTH);
        CreateBrandDto brand = new CreateBrandDto(name);

        when(brandService.create(brand)).thenReturn(brand(name));

        String json = mvc.perform(post(BRAND_CONTROLLER_URL)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(brand)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        BrandDto actual = MapperUtils.fromJson(json, BrandDto.class);

        assertThat(actual).isEqualTo(brand(name));
    }

    @Test
    public void findOne_shouldReturnBrandById() throws Exception {
        when(brandService.findOne(BRAND_ID)).thenReturn(Optional.of(brand()));

        String json = mvc.perform(get(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        BrandDto actual = MapperUtils.fromJson(json, BrandDto.class);

        assertThat(actual).isEqualTo(brand());
    }

    @Test
    public void findOne_shouldReturnBrandByIdInXmlFormat() throws Exception {
        when(brandService.findOne(BRAND_ID)).thenReturn(Optional.of(brand()));

        String xml = mvc.perform(get(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
                .andReturn().getResponse().getContentAsString();

        BrandDto actual = MapperUtils.fromXml(xml, BrandDto.class);

        assertThat(actual).isEqualTo(brand());
    }

    @Test
    public void findOne_shouldRespondWithNotFound() throws Exception {
        mvc.perform(get(BRAND_CONTROLLER_URL + "/" + BRAND_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateBrand_shouldReturnErrorWhenNameIsEmpty() throws Exception {
        mvc.perform(put(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(new UpdateBrandDto(""))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBrand_shouldReturnErrorWhenNameIsNull() throws Exception {
        mvc.perform(put(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(new UpdateBrandDto(null))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBrand_shouldReturnSuccessfullyUpdatedBrand() throws Exception {
        String name = StringUtils.repeat('n', NAME_MAX_LENGTH);
        UpdateBrandDto brand = new UpdateBrandDto(name);

        when(brandService.update(BRAND_ID, brand)).thenReturn(Optional.of(brand(name)));

        String json = mvc.perform(put(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MapperUtils.asJson(brand)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        BrandDto actual = MapperUtils.fromJson(json, BrandDto.class);

        assertThat(actual).isEqualTo(brand(name));
    }

    @Test
    public void updateBrand_shouldReturnErrorWhenNameExceedsMaxLength() throws Exception {
        String nameExceedsMaxLength = StringUtils.repeat("n", NAME_MAX_LENGTH + 1);

        mvc.perform(put(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MapperUtils.asJson(new UpdateBrandDto(nameExceedsMaxLength))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delete_shouldReturnSuccessfullyDeletedBrand() throws Exception {
        when(brandService.delete(BRAND_ID)).thenReturn(Optional.of(brand()));

        String json = mvc.perform(delete(BRAND_CONTROLLER_URL + "/" + BRAND_ID)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        BrandDto actual = MapperUtils.fromJson(json, BrandDto.class);

        assertThat(actual).isEqualTo(brand());
    }

    @Test
    public void quantities_shouldReturnQualities() throws Exception {
        QuantityDto q1 = new QuantityDto(new BigInteger("1"), "name1", new BigInteger("2"));
        QuantityDto q2 = new QuantityDto(new BigInteger("3"), "name2", new BigInteger("4"));

        when(brandService.quantities()).thenReturn(Arrays.asList(q1, q2));

        String json = mvc.perform(get(BRAND_CONTROLLER_URL + "/quantity")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        List<QuantityDto> actual = MapperUtils.fromJson(json, new TypeReference<List<QuantityDto>>() {
        });

        assertThat(actual).containsOnly(q1, q2);
    }

    private static BrandDto brand(String brandName) {
        return new BrandDto(BRAND_ID, brandName);
    }

    private static BrandDto brand() {
        return new BrandDto(BRAND_ID, BRAND_NAME);
    }
}
