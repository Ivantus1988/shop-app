package com.shukalovich.database;

import com.shukalovich.database.entity.Address;
import com.shukalovich.database.entity.OrderEntity;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.Screen;
import com.shukalovich.database.entity.UserEntity;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import static com.shukalovich.database.entity.enam.Brand.*;
import static com.shukalovich.database.entity.enam.Gender.*;
import static com.shukalovich.database.entity.enam.Role.*;

@UtilityClass
public class TestDataImporter {

    public void importTestData(Session session) {

        ProductEntity apple14Pro = ProductEntity.builder()
                .brand(APPLE)
                .model("14 Pro")
                .ram((short) 6)
                .memorySize((short) 128)
                .screen(Screen.builder()
                        .screenResolution("1179x2556")
                        .screenSize(6.1)
                        .build())
                .price(3624.93)
                .description("Apple iOS, экран 6.1\" OLED (1179x2556) 120 Гц, " +
                             "Apple A16 Bionic, ОЗУ 6 ГБ, память 128 ГБ, камера 48 Мп, 1 SIM (nano-SIM/eSIM), " +
                             "влагозащита IP68")
                .build();
        ProductEntity honor70 = ProductEntity.builder()
                .brand(HONOR)
                .model("HONOR 70")
                .ram((short) 8)
                .memorySize((short) 256)
                .screen(Screen.builder()
                        .screenResolution("1080x2400")
                        .screenSize(6.67)
                        .build())
                .price(1248.12)
                .description("\n" +
                             "Android, экран 6.67\" OLED (1080x2400) 120 Гц, " +
                             "Qualcomm Snapdragon 778G+, ОЗУ 8 ГБ, память 256 ГБ, камера 54 Мп, аккумулятор 4800 мАч, " +
                             "2 SIM (nano-SIM)")
                .build();


        Address build = Address.builder()
                .city("Minsk")
                .street("Minskaya")
                .building("1A")
                .flat("1")
                .build();

        UserEntity ivan = UserEntity.builder()
                .name("Ivan")
                .surname("Ivanov")
                .role(USER)
                .gender(MALE)
                .email("ivanov@gmail.com")
                .password("1234")
                .address(build)
                .build();

        UserEntity petr = UserEntity.builder()
                .name("Petr")
                .surname("Petrov")
                .role(ADMIN)
                .gender(MALE)
                .email("petrov@gmail.com")
                .password("123456")
                .address(build)
                .build();

        OrderEntity orderIvan = OrderEntity.builder()
                .user(ivan)
                .product(apple14Pro)
                .build();

        OrderEntity orderPetr = OrderEntity.builder()
                .user(petr)
                .product(apple14Pro)
                .build();

        session.persist(ivan);
        session.persist(petr);
        session.persist(apple14Pro);
        session.persist(honor70);
        session.persist(orderIvan);
        session.persist(orderPetr);

    }
}
