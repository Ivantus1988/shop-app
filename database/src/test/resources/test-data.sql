ALTER TABLE users ALTER COLUMN created_at SET DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE users ALTER COLUMN updated_at SET DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE orders ALTER COLUMN created_at SET DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE orders ALTER COLUMN updated_at SET DEFAULT CURRENT_TIMESTAMP;


INSERT INTO product (brand, model, screen_size, screen_resolution, ram, memory_size, price, description)
VALUES ('APPLE', '14', 6.1, '1170*2352', 6, 128, 2623,
        'Apple iOS, 6.1" OLED screen (1170x2532) 60 Hz, Apple A15 Bionic, 6 GB RAM, 128 GB storage, 12 MP camera, 1 SIM (nano-SIM/eSIM), IP68 waterproof'),
       ('SAMSUNG', 'A52', 6.5, '1080*2400', 8, 256, 974,
        'Android, screen 6.5" AMOLED (1080x2400) 90 Hz, Qualcomm Snapdragon 720G, 8 GB RAM, 256 GB memory, memory card support, 64 MP camera, 4500 mAh battery, 2 SIM (nano-SIM), IP67 waterproof');


INSERT INTO users(name, surname, email, password, gender, role, city, street, building, flat)
VALUES ('Ivan', 'Ivanov', 'ivanov@mail.ru', '1234', 'MALE', 'USER', 'Minsk', 'P.Mstislavca', '55', '98'),
       ('Petr', 'Petrov', 'petrov@gmail.com', 'petrov', 'MALE', 'USER', 'Mogilev', 'Movchanskogo', '1', '1');
