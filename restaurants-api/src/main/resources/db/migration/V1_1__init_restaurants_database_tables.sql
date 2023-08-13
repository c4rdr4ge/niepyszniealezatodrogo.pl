CREATE TABLE kitchen_type
(
    kitchen_type_id                 SERIAL                  NOT NULL,
    kitchen_type_name               VARCHAR(64)             NOT NULL,
    PRIMARY KEY (kitchen_type_id)
);

CREATE TABLE category
(
    category_id                     SERIAL                  NOT NULL,
    category_name                   VARCHAR(32)             NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE address
(
    address_id                      SERIAL                  NOT NULL,
    address_country                 VARCHAR(32)             NOT NULL,
    address_city                    VARCHAR(64)             NOT NULL,
    address_postal_code             VARCHAR(6)              NOT NULL,
    address_street                  VARCHAR(128)            NOT NULL,
    PRIMARY KEY (address_id)
);

CREATE TABLE menu
(
    menu_id                         SERIAL                  NOT NULL,
    kitchen_type_id                 INTEGER                 NOT NULL,
    PRIMARY KEY (menu_id),
    CONSTRAINT fk_kitchen_type
        FOREIGN KEY (kitchen_type_id)
            REFERENCES kitchen_type (kitchen_type_id)
);

CREATE TABLE restaurant_owner
(
    restaurant_owner_id             SERIAL                  NOT NULL,
    restaurant_owner_name           VARCHAR(32)             NOT NULL,
    restaurant_owner_surname        VARCHAR(64)             NOT NULL,
    restaurant_owner_phone          VARCHAR(64)             NOT NULL,
    restaurant_owner_email          VARCHAR(64)             NOT NULL        UNIQUE,
    restaurant_owner_nip            VARCHAR(64)             NOT NULL        UNIQUE,
    restaurant_owner_address_id     INTEGER                 NOT NULL,
    PRIMARY KEY (restaurant_owner_id),
    CONSTRAINT fk_owner_address
        FOREIGN KEY (restaurant_owner_address_id)
            REFERENCES address (address_id)
);

CREATE TABLE restaurant
(
    restaurant_id                   SERIAL                  NOT NULL,
    restaurant_name                 VARCHAR(64)             NOT NULL,
    restaurant_phone                VARCHAR(64)             NOT NULL,
    restaurant_rating               NUMERIC(2, 1)           NOT NULL,
    kitchen_type_id                 INTEGER                 NOT NULL,
    restaurant_address_id           INTEGER                 NOT NULL,
    restaurant_menu_id              INTEGER                 NOT NULL,
    restaurant_owner_id             INTEGER                 NOT NULL,
    PRIMARY KEY (restaurant_id),
    CONSTRAINT fk_kitchen_type
        FOREIGN KEY (kitchen_type_id)
            REFERENCES kitchen_type (kitchen_type_id),
    CONSTRAINT fk_address
        FOREIGN KEY (restaurant_address_id)
            REFERENCES address (address_id),
    CONSTRAINT fk_menu
        FOREIGN KEY (restaurant_menu_id)
            REFERENCES menu (menu_id),
    CONSTRAINT fk_owner
        FOREIGN KEY (restaurant_owner_id)
            REFERENCES restaurant_owner (restaurant_owner_id)
);

CREATE TABLE dish
(
    dish_id                         SERIAL                  NOT NULL,
    dish_name                       VARCHAR(256)            NOT NULL,
    dish_description                VARCHAR(256),
    dish_weight                     INTEGER                 NOT NULL,
    dish_photo_url                  VARCHAR(256)            NOT NULL,
    dish_price                      NUMERIC(7, 2)           NOT NULL,
    dish_category_id                INTEGER                 NOT NULL,
    kitchen_type_id                 INTEGER                 NOT NULL,
    PRIMARY KEY (dish_id),
    CONSTRAINT fk_category
        FOREIGN KEY (dish_category_id)
            REFERENCES category (category_id),
    CONSTRAINT fk_kitchen_type
        FOREIGN KEY (kitchen_type_id)
            REFERENCES kitchen_type (kitchen_type_id)
);

CREATE TABLE menu_position
(
    menu_position_id                SERIAL                  NOT NULL,
    menu_id                         INTEGER                 NOT NULL,
    dish_id                         INTEGER                 NOT NULL,
    PRIMARY KEY (menu_position_id),
    CONSTRAINT fk_menu
        FOREIGN KEY (menu_id)
            REFERENCES menu (menu_id),
    CONSTRAINT fk_dish
        FOREIGN KEY (dish_id)
            REFERENCES dish (dish_id)
);

CREATE TABLE available_address
(
    available_address_id            SERIAL                  NOT NULL,
    restaurant_id                   INTEGER                 NOT NULL,
    address_id                      INTEGER                 NOT NULL,
    PRIMARY KEY (available_address_id),
    CONSTRAINT fk_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id),
    CONSTRAINT fk_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)
);
