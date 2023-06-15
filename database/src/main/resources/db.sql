DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS factory;
DROP TABLE IF EXISTS product_factory;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS pruducer;


CREATE TABLE product
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    brand             VARCHAR(32) NOT NULL,
    model             VARCHAR(32) NOT NULL,
    screen_size       REAL CHECK ( screen_size > 1 ),
    screen_resolution VARCHAR(32) NOT NULL,
    ram               SMALLINT CHECK ( ram > 0 ),
    memory_size       SMALLINT CHECK ( memory_size > 0 ),
    price             REAL CHECK ( price > 0 ),
    description       TEXT        NOT NULL
);

INSERT INTO product (brand, model, screen_size, screen_resolution, ram, memory_size, price, description)
VALUES ('APPLE', '14', 6.1, '1170*2352', 6, 128, 2623,
        'Apple iOS, 6.1" OLED screen (1170x2532) 60 Hz, Apple A15 Bionic, 6 GB RAM, 128 GB storage, 12 MP camera, 1 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('APPLE', '13', 6.1, '1170*2351', 4, 128, 2407.28,
        'Apple iOS, 6.1" OLED screen (1170x2532) 60 Hz, Apple A15 Bionic, 4 GB RAM, 128 GB memory, 12 MP camera, 3227 mAh battery, 1 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('APPLE', '14 Pro', 6.1, '1179*2556', 6, 128, 3624,
        'Apple iOS, 6.1" OLED screen (1179x2556) 120 Hz, Apple A16 Bionic, 6 GB RAM, 128 GB storage, 48 MP camera, 1 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('APPLE', '14 Pro', 6.1, '1179*2556', 6, 1000, 5243,
        'Apple iOS, 6.1" OLED screen (1179x2556) 120 Hz, Apple A16 Bionic, 6 GB RAM, 1 TB storage, 48 MP camera, 1 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('SAMSUNG', 'A52', 6.5, '1080*2400', 4, 128, 780,
        'Android, screen 6.5" AMOLED (1080x2400) 90 Hz, Qualcomm Snapdragon 720G, 4 GB RAM, 128 GB memory, memory card support, 64 MP camera, 4500 mAh battery, 2 SIM (nano-SIM), IP67 waterproof'),
       ('SAMSUNG', 'A52', 6.5, '1080*2400', 8, 128, 974,
        'Android, screen 6.5" AMOLED (1080x2400) 90 Hz, Qualcomm Snapdragon 720G, 8 GB RAM, 128 GB memory, memory card support, 64 MP camera, 4500 mAh battery, 2 SIM (nano-SIM), IP67 waterproof'),
       ('SAMSUNG', 'A52', 6.5, '1080*2400', 8, 256, 974,
        'Android, screen 6.5" AMOLED (1080x2400) 90 Hz, Qualcomm Snapdragon 720G, 8 GB RAM, 256 GB memory, memory card support, 64 MP camera, 4500 mAh battery, 2 SIM (nano-SIM), IP67 waterproof'),
       ('SAMSUNG', 'S23', 6.1, '1080*2340', 8, 128, 2530,
        'Android, screen 6.1" AMOLED (1080x2340) 120 Hz, Qualcomm Snapdragon 8 Gen2 SM8550, 8 GB RAM, 128 GB memory, 50 MP camera, 3900 mAh battery, 2 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('SAMSUNG', 'S23', 6.1, '1080*2340', 8, 256, 2700,
        'Android, screen 6.1" AMOLED (1080x2340) 120 Hz, Qualcomm Snapdragon 8 Gen2 SM8550, 8 GB RAM, 256 GB memory, 50 MP camera, 3900 mAh battery, 2 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('XIAOMI', '10 Pro', 6.67, '1080*2400', 8, 128, 825,
        'Android, screen 6.67" AMOLED (1080x2400) 120 Hz, Qualcomm Snapdragon 732G, 8 GB RAM, 128 GB memory, memory card support, 108 MP camera, 5020 mAh battery, 2 SIM (nano-SIM), IP53 waterproof'),
       ('XIAOMI', '10 Pro', 6.67, '1080*2400', 8, 256, 840,
        'Android, screen 6.67" AMOLED (1080x2400) 120 Hz, Qualcomm Snapdragon 732G, 8 GB RAM, 256 GB memory, memory card support, 108 MP camera, 5020 mAh battery, 2 SIM (nano-SIM), IP53 waterproof'),
       ('HONOR', '70', 6.67, '1080*2400', 8, 256, 1248,
        'Android, screen 6.67" OLED (1080x2400) 120 Hz, Qualcomm Snapdragon 778G+, 8 GB RAM, 256 GB memory, 54 MP camera, 4800 mAh battery, 2 SIM (nano-SIM)'),
       ('HONOR', 'X9A', 6.67, '1080*2400', 6, 128, 840,
        'Android, screen 6.67" AMOLED (1080x2400) 120 Hz, Qualcomm Snapdragon 695, 6 GB RAM, 128 GB memory, 64 MP camera, 5100 mAh battery, 2 SIM (nano-SIM)'),
       ('HUAWEI', 'P30', 6.15, '1080*2312', 4, 128, 520,
        'Android, screen 6.15" IPS (1080x2312), HiSilicon Kirin 710, 4 GB RAM, 128 GB memory, memory card support, 24 MP camera, 3340 mAh battery, 2 SIM (nano-SIM)'),
       ('HUAWEI', 'nova 10', 6.67, '1080*2400', 8, 128, 844,
        'Android, screen 6.67" OLED (1080x2400) 90 Hz, Qualcomm Snapdragon 680, 8 GB RAM, 128 GB memory, 108 MP camera, 4500 mAh battery, 2 SIM (nano-SIM)'),
       ('GOOGLE', 'Pixel 7', 6.3, '1080*2400', 8, 128, 1900,
        'Android (without shell), screen 6.3" AMOLED (1080x2400) 90 Hz, Google Tensor G2, 8 GB RAM, 128 GB memory, 50 MP camera, 4355 mAh battery, 2 SIM (nano-SIM / eSIM), IP68 waterproof'),
       ('GOOGLE', 'Pixel 6a', 6.1, '1080*2400', 6, 128, 1190,
        'Android (without shell), screen 6.1" OLED (1080x2400) 60 Hz, Google Tensor, 6 GB RAM, 128 GB memory, 12.2 MP camera, 4410 mAh battery, 2 SIM (nano-SIM / eSIM), IP67 waterproof');

CREATE TABLE users
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(32) NOT NULL,
    surname    VARCHAR(32) NOT NULL,
    email      VARCHAR(32) UNIQUE,
    password   VARCHAR(32) NOT NULL,
    gender     VARCHAR(32) NULL,
    role       VARCHAR(32) NULL,
    city       VARCHAR(32) NULL,
    street     VARCHAR(32) NULL,
    building   VARCHAR(32) NULL,
    flat       VARCHAR(32) NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users(name, surname, email, password, gender, role, city, street, building, flat)
VALUES ('Ivan', 'Ivanov', 'ivanov@mail.ru', '1234', 'MALE', 'USER', 'Minsk', 'P.Mstislavca', '55', '98'),
       ('Pert', 'Petrov', 'petrov@gmail.com', 'petrov', 'MALE', 'USER', 'Mogilev', 'Movchanskogo', '1', '1'),
       ('Sergey', 'Sergeev', 'sergey@mail.ru', 'sergrey123', 'MALE', 'ADMIN', 'Gomel', 'Y. Kolasa', '12', '12'),
       ('Ecaterina', 'Ecaterinova', 'katenka@ya.ru', '123', 'FEMALE', 'USER', 'Grodno', 'Skaryna', '5', '5'),
       ('Alexandra', 'Alexandrova', 'sanya75@list.ru', 'A12345a', 'FEMALE', 'ADMIN', 'Brest', 'Kirova', '15A', '18');

CREATE TABLE factory
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    location VARCHAR(150) NOT NULL UNIQUE,
    contact  VARCHAR(32) NOT NULL UNIQUE
);

INSERT INTO factory(location, contact)
VALUES ('Building 6, Block C, 88 Maji Road, China (Shanghai) Pilot Free Trade Zone, Shanghai 200131, China. Apple India Private Ltd.',
        '(800) 538–9697'),
       ('Industrial Complex, Chenjiang Town, Huizhou, Guangdong, China, 516229', '(800) 538–9696'),
       ('Beijing, #019, 9th Floor, Building 6, 33 Xi''erqi Middle Road, Haidian District, China', '(800) 538–9695'),
       ('Unit 1301, 13/F, Paramount Building, 12 Ka Yip Street, Chai Wan, Hong Kong', '(800) 538–9694'),
       ('9 Queen''s Road Central, 810, Unit 1908 19/F, Hong Kong', '(800) 538–9693');

CREATE TABLE product_factory
(
    product_id BIGINT REFERENCES product (id),
    factory_id BIGINT REFERENCES factory (id)
);

INSERT INTO product_factory(product_id, factory_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 3),
       (11, 3),
       (12, 4),
       (13, 4),
       (14, 5);

CREATE TABLE orders
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id    BIGINT    NOT NULL REFERENCES users (id),
    product_id BIGINT    NOT NULL REFERENCES product (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO orders(user_id, product_id)
VALUES (1, 1),
       (2, 1),
       (3, 3),
       (5, 6),
       (4, 8),
       (5, 2),
       (1, 10),
       (1, 3),
       (3, 5),
       (4, 9);

CREATE TABLE producer
(
    product_id             BIGINT REFERENCES product (id),
    number_of_the_contract VARCHAR(32) NOT NULL UNIQUE
);

INSERT INTO producer(product_id, number_of_the_contract)
VALUES (1, '1-2'),
       (2, '2-2'),
       (3, '3-3'),
       (4, '4-2'),
       (5, '5-2'),
       (6, '6-2'),
       (7, '7-2'),
       (8, '8-2'),
       (9, '9-2'),
       (10, '10-2'),
       (11, '11-2'),
       (12, '12-2'),
       (13, '13-2'),
       (14, '14-2'),
       (15, '15-2'),
       (16, '16-2'),
       (17, '17-2');

