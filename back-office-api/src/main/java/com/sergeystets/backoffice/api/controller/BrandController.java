package com.sergeystets.backoffice.api.controller;

import com.sergeystets.backoffice.api.dto.BrandDto;
import com.sergeystets.backoffice.api.dto.CreateBrandDto;
import com.sergeystets.backoffice.api.dto.QuantityDto;
import com.sergeystets.backoffice.api.dto.UpdateBrandDto;
import com.sergeystets.backoffice.api.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@Api(value = "Endpoint to work with brands")
@RestController
@RequestMapping("/api/brand")
@AllArgsConstructor
public class BrandController {

    private BrandService brandService;

    @ApiOperation(value = "Create a new brand")
    @PostMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BrandDto> create(@Valid @RequestBody CreateBrandDto brand) {
        return ResponseEntity.ok(brandService.create(brand));
    }

    @ApiOperation(value = "Get a brand by the given ID")
    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<BrandDto> findOne(@PathVariable BigInteger id) {
        return ResponseEntity.of(brandService.findOne(id));
    }

    @ApiOperation(value = "Update a brand by the given ID")
    @PutMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<BrandDto> update(@PathVariable BigInteger id, @Valid @RequestBody UpdateBrandDto brand) {
        return ResponseEntity.of(brandService.update(id, brand));
    }

    @ApiOperation(value = "Delete a brand by the given ID")
    @DeleteMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<BrandDto> delete(@PathVariable BigInteger id) {
        return ResponseEntity.of(brandService.delete(id));
    }

    @ApiOperation(value = "Get sum of all brands quantities")
    @GetMapping(value = "/quantity", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<QuantityDto> getQuantities() {
        return brandService.quantities();
    }
}
