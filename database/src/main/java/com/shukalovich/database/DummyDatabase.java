package com.shukalovich.database;


import com.shukalovich.database.entity.Brand;
import com.shukalovich.database.entity.Product;
import com.shukalovich.database.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DummyDatabase {

    private static final DummyDatabase INSTANCE = new DummyDatabase();
    private static final long ID_A52 = 2;
    private static final double SCREEN_SIZE_A52 = 6.5;
    private static final int RAM_A52 = 4;
    private static final int MEMORY_SIZE_A52 = 128;
    private static final double PRICE_A52 = 1132.3;
    private static final long ID_X9A = 3;
    private static final double SCREEN_SIZE_X9A = 6.67;
    private static final int RAM_X9A = 6;
    private static final int MEMORY_SIZE_X9A = 128;
    private static final double PRICE_X9A = 915.36;
    private static final long ID_IPHONE_11 = 4;
    private static final double SCREEN_SIZE_IPHONE_11 = 6.1;
    private static final int RAM_IPHONE_11 = 4;
    private static final int MEMORY_SIZE_IPHONE_11 = 64;
    private static final double PRICE_IPHONE_11 = 1500;
    private static final long ID_USER_1 = 1;
    private static final long ID_USER_2 = 2;

    private final Map<Long, Product> products = new HashMap<>() {{
        put(ID_A52, Product.builder()
                .id(ID_A52)
                .brand(Brand.SAMSUNG)
                .model("A52")
                .screenSize(SCREEN_SIZE_A52)
                .screenResolution("1080x2400")
                .ram(RAM_A52)
                .memorySize(MEMORY_SIZE_A52)
                .price(PRICE_A52)
                .description("Android, экран 6.5 AMOLED (1080x2400) 90 Гц, Qualcomm Snapdragon 720G, ОЗУ 4 ГБ, память 128 ГБ, поддержка карт памяти, камера 64 Мп, аккумулятор 4500 мАч, 2 SIM (nano-SIM), влагозащита IP67.")
                .build());
        put(ID_X9A, Product.builder()
                .id(ID_X9A)
                .brand(Brand.HONOR)
                .model("X9a")
                .screenSize(SCREEN_SIZE_X9A)
                .screenResolution("1080x2400")
                .ram(RAM_X9A)
                .memorySize(MEMORY_SIZE_X9A)
                .price(PRICE_X9A)
                .description("Android, экран 6.67 AMOLED (1080x2400) 120 Гц, Qualcomm Snapdragon 695, ОЗУ 6 ГБ, память 128 ГБ, камера 64 Мп, аккумулятор 5100 мАч, 2 SIM (nano-SIM).")
                .build());
        put(ID_IPHONE_11, Product.builder()
                .id(ID_IPHONE_11)
                .brand(Brand.APPLE)
                .model("Iphone 11")
                .screenSize(SCREEN_SIZE_IPHONE_11)
                .screenResolution("828x1792")
                .ram(RAM_IPHONE_11)
                .memorySize(MEMORY_SIZE_IPHONE_11)
                .price(PRICE_IPHONE_11)
                .description("Apple iOS, экран 6.1 IPS (828x1792), Apple A13 Bionic, ОЗУ 4 ГБ, память 64 ГБ,камера 12 Мп, аккумулятор 3046 мАч, 1 SIM (nano-SIM/eSIM), влагозащита IP68.")
                .build());
    }};

    private final Map<Long, User> users = new HashMap<>() {{
        put(ID_USER_1, User.builder()
                .id(ID_USER_1)
                .name("Ivan")
                .surname("Ivanov")
                .email("ivanov_ivan@gmail.com")
                .password("ivanov2000")
                .gender("Male")
                .build());
        put(ID_USER_2, User.builder()
                .id(ID_USER_2)
                .name("Petr")
                .surname("Petrov")
                .email("petrov_petr@gmail.com")
                .password("2000petr")
                .gender("Male")
                .build());
    }};

    public static DummyDatabase getInstance() {
        return INSTANCE;
    }
}