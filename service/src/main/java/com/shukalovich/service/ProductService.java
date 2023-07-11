package com.shukalovich.service;

import com.shukalovich.database.dto.ProductCreationDto;
import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.dto.ProductReadDto;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.Screen;
import com.shukalovich.database.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findByFilter(ProductFilter filter) {
        return productRepository.findByFilter(filter);
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElse(ProductEntity.builder().build());
    }

    public Optional<ProductEntity> update(ProductEntity product) {
        return Optional.of(productRepository.save(product));
    }

    public Optional<ProductReadDto> update(Long id, ProductCreationDto updateProduct) {
        Optional<ProductEntity> existedProduct = productRepository.findById(id);
        if (existedProduct.isPresent()) {
            ProductEntity product = existedProduct.get();
            Screen updatedScreen = product.getScreen();
            updatedScreen.setScreenSize(updateProduct.screenSize());
            updatedScreen.setScreenResolution(updateProduct.screenResolution());
            product.setScreen(updatedScreen);
            product.setBrand(updateProduct.brand());
            product.setModel(updateProduct.model());
            product.setRam(updateProduct.ram());
            product.setMemorySize(updateProduct.memorySize());
            product.setDescription(updateProduct.description());
            product.setPrice(updateProduct.price());
            return Optional.of(toReadDto(productRepository.save(product)));
        }
        return Optional.empty();
    }

    public Optional<ProductEntity> save(ProductCreationDto product) {
        return Optional.of(productRepository.save(ProductEntity.builder()
                .model(product.model())
                .brand(product.brand())
                .screen(Screen.builder()
                        .screenSize(product.screenSize())
                        .screenResolution(product.screenResolution())
                        .build())
                .ram(product.ram())
                .memorySize(product.memorySize())
                .price(product.price())
                .description(product.description())
                .build()));
    }

    public boolean delete(Long id) {
        Optional<ProductEntity> removedProduct = productRepository.findById(id);
        removedProduct.get();
        productRepository.delete(removedProduct.get());
        return true;
    }

    private ProductReadDto toReadDto(ProductEntity product) {
        return new ProductReadDto(product.getId(),
                product.getBrand(),
                product.getModel(),
                product.getScreen().getScreenSize(),
                product.getScreen().getScreenResolution(),
                product.getRam(),
                product.getMemorySize(),
                product.getPrice(),
                product.getDescription());
    }
}
