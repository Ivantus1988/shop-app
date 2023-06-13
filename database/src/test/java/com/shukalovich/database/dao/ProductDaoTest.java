package com.shukalovich.database.dao;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.Screen;
import com.shukalovich.database.hibernate.HibernateFactory;
import lombok.Cleanup;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static com.shukalovich.database.entity.enam.Brand.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductDaoTest {

    private static final ProductDao productDao = ProductDao.getInstance();
    private static final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllProductsReturned() {
        @Cleanup var session = hibernateFactory.getSession();
        String[] actual = productDao.findAll(session)
                .stream()
                .map(ProductEntity::getModel)
                .toArray(String[]::new);
        String[] expected = List.of("14 Pro", "HONOR 70")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindAllByBrandInvoked_ThenAllProductsOfBrandAreReturned() {
        @Cleanup var session = hibernateFactory.getSession();
        String[] actual = productDao.findAllByBrand(session, APPLE)
                .stream()
                .map(ProductEntity::getModel)
                .toArray(String[]::new);
        String[] expected = List.of("14 Pro")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenFindAllByFilterContainsOnlyPriceInvoked_ThenAllFilteredContainsOnlyPriceProductsOfBrandReturned() {
        @Cleanup var session = hibernateFactory.getSession();
        Double[] actual = productDao.findByFilter(session, ProductFilter
                        .builder()
                        .price(1248.12)
                        .build())
                .stream()
                .map(ProductEntity::getPrice)
                .toArray(Double[]::new);
        Double[] expected = List.of(1248.12)
                .toArray(Double[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(4)
    void whenFindAllByFilterContainsPriceAndRamInvoked_ThenAllFilteredContainsPriceAndRamProductsOfBrandReturned() {
        @Cleanup var session = hibernateFactory.getSession();
        String[] actual = productDao.findByFilter(session, ProductFilter.builder()
                        .price(1248.12)
                        .ram((short) 8)
                        .build())
                .stream()
                .map(ProductEntity::getModel)
                .toArray(String[]::new);
        String[] expected = List.of("HONOR 70")
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

        @Cleanup var session = hibernateFactory.getSession();
        session.beginTransaction();
        productDao.save(session, testProduct);
        session.getTransaction().commit();
        List<String> models = productDao.findAll(session)
                .stream()
                .map(ProductEntity::getModel)
                .toList();
        assertTrue(models.contains(testProduct.getModel()));

        session.beginTransaction();
        productDao.delete(session, testProduct.getId());
        session.getTransaction().commit();
    }

    @Test
    @Order(6)
    void whenFindById_ThenReturnedValidProduct() {
        @Cleanup var session = hibernateFactory.getSession();
        ProductEntity actual = productDao.findById(session, 1l);
        assertNotNull(actual);
        ;
        assertEquals("14 Pro", actual.getModel());
    }
}