package com.shukalovich.database.repository;

import com.shukalovich.database.config.DatabaseConfig;
import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.Screen;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.shukalovich.database.entity.enam.Brand.APPLE;
import static com.shukalovich.database.entity.enam.Brand.GOOGLE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@Sql(value = "classpath:test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:purge-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllProductsReturned() {
        String[] actual = productRepository.findAll()
                .stream()
                .map(ProductEntity::getModel)
                .toArray(String[]::new);
        String[] expected = List.of("14", "A52")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindAllByBrandInvoked_ThenAllProductsOfBrandAreReturned() {
        String[] actual = productRepository.findAllByBrand(APPLE)
                .stream()
                .map(ProductEntity::getModel)
                .toArray(String[]::new);
        String[] expected = List.of("14")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenFindAllByFilterContainsOnlyPriceInvoked_ThenAllFilteredContainsOnlyPriceProductsOfBrandReturned() {
        Double[] actual = productRepository.findByFilter(ProductFilter
                        .builder()
                        .price(974.0)
                        .build())
                .stream()
                .map(ProductEntity::getPrice)
                .toArray(Double[]::new);
        Double[] expected = List.of(974.0)
                .toArray(Double[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(4)
    void whenFindAllByFilterContainsPriceAndRamInvoked_ThenAllFilteredContainsPriceAndRamProductsOfBrandReturned() {
        String[] actual = productRepository.findByFilter(ProductFilter.builder()
                        .price(974.0)
                        .ram((short) 8)
                        .build())
                .stream()
                .map(ProductEntity::getModel)
                .toArray(String[]::new);
        String[] expected = List.of("A52")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
    

    @Test
    @Order(5)
    void whenCreatedInvokedWithProduct_ThenProductSaved() {
        ProductEntity testProduct = ProductEntity.builder()
                .brand(GOOGLE)
                .model("Pixel 6a")
                .ram((short) 6)
                .memorySize((short) 128)
                .screen(Screen.builder()
                        .screenResolution("1080*2400")
                        .screenSize(6.67)
                        .build())
                .price(1190.0)
                .build();

        productRepository.save(testProduct);
        List<String> models = productRepository.findAll()
                .stream()
                .map(ProductEntity::getModel)
                .toList();
        assertTrue(models.contains(testProduct.getModel()));

        productRepository.delete(testProduct);
    }

    @Test
    @Order(6)
    void whenFindById_ThenReturnedValidProduct() {
        Optional<ProductEntity> actual = productRepository.findByModel("14");
        assertNotNull(actual);
        assertEquals("14", actual.get().getModel());
    }
}