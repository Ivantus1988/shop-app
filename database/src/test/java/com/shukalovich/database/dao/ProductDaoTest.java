package com.shukalovich.database.dao;

import com.shukalovich.database.DummyDatabase;
import com.shukalovich.database.entity.Brand;
import com.shukalovich.database.entity.Product;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductDaoTest {
    private final DummyDatabase db = DummyDatabase.getInstance();
    private static final long ID_A52 = 2;
    private static final double SCREEN_SIZE_A52 = 6.5;
    private static final int RAM_A52 = 4;
    private static final int MEMORY_SIZE_A52 = 128;
    private static final double PRICE_A52 = 1132.3;

    @Test
    public void getProduct() {
        Product product = db.getProducts().get(2L);
        assertEquals(Product.builder()
                .id(ID_A52)
                .brand(Brand.SAMSUNG)
                .model("A52")
                .screenSize(SCREEN_SIZE_A52)
                .screenResolution("1080x2400")
                .ram(RAM_A52)
                .memorySize(MEMORY_SIZE_A52)
                .price(PRICE_A52)
                .description("Android, экран 6.5 AMOLED (1080x2400) 90 Гц, Qualcomm Snapdragon 720G, ОЗУ 4 ГБ, память 128 ГБ, поддержка карт памяти, камера 64 Мп, аккумулятор 4500 мАч, 2 SIM (nano-SIM), влагозащита IP67.")
                .build(), product);
    }
}