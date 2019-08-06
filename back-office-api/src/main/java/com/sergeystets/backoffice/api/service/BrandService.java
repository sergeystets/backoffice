package com.sergeystets.backoffice.api.service;

import com.sergeystets.backoffice.api.dto.BrandDto;
import com.sergeystets.backoffice.api.dto.CreateBrandDto;
import com.sergeystets.backoffice.api.dto.QuantityDto;
import com.sergeystets.backoffice.api.dto.UpdateBrandDto;
import com.sergeystets.backoffice.api.entity.Brand;
import com.sergeystets.backoffice.api.entity.projection.Quantity;
import com.sergeystets.backoffice.api.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class BrandService {

    private BrandRepository brandRepo;

    @Transactional
    public BrandDto create(CreateBrandDto brand) {
        return toDto(brandRepo.save(toEntity(brand)));
    }

    @Transactional(readOnly = true)
    public Optional<BrandDto> findOne(BigInteger id) {
        return brandRepo.findById(id).map(this::toDto);
    }

    @Transactional
    public Optional<BrandDto> update(BigInteger id, UpdateBrandDto newBrand) {
        return brandRepo.findById(id)
                .map(oldBrand -> oldBrand.setName(newBrand.getName()))
                .map(this::toDto);
    }

    @Transactional
    public Optional<BrandDto> delete(BigInteger id) {
        Optional<Brand> brandToDelete = brandRepo.findById(id);
        brandToDelete.ifPresent(brand -> brandRepo.delete(brand));
        return brandToDelete.map(this::toDto);
    }

    @Transactional(readOnly = true)
    public List<QuantityDto> quantities() {
        return brandRepo.findQuantities().stream().map(this::toDto).collect(toList());
    }

    private BrandDto toDto(Brand brandEntity) {
        if (brandEntity == null) {
            return null;
        }
        return new BrandDto(brandEntity.getId(), brandEntity.getName());
    }

    private Brand toEntity(CreateBrandDto brandDto) {
        if (brandDto == null) {
            return null;
        }
        return new Brand().setName(brandDto.getName());
    }

    private QuantityDto toDto(Quantity totalQuantity) {
        if (totalQuantity == null) {
            return null;
        }
        return new QuantityDto(
                totalQuantity.getBrandId(),
                totalQuantity.getBrandName(),
                totalQuantity.getQuantity());
    }
}
