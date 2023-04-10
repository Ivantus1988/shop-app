package com.shukalovich.database.dao;

import com.shukalovich.database.entity.Brand;
import com.shukalovich.database.entity.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDaoTest {

    private static final int ID = 1;
    private static final double PRICE = 2000.2;

    @Test
    public void getProduct() {
        ProductDao productDao = ProductDao.getInstance();
        assertEquals(Product.builder()
                .id(ID)
                .model("S22")
                .price(PRICE)
                .brand(Brand.SAMSUNG)
                .build(), productDao.getProduct());
    }
}