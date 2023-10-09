CREATE TABLE user_table
(
    user_id                         SERIAL                      NOT NULL,
    user_username                   VARCHAR(32)                 NOT NULL        UNIQUE,
    user_password                   VARCHAR(256)                NOT NULL,
    user_name                       VARCHAR(32)                 NOT NULL,
    user_surname                    VARCHAR(64)                 NOT NULL,
    user_phone                      VARCHAR(32)                 NOT NULL,
    user_email                      VARCHAR(64)                 NOT NULL        UNIQUE,
    user_address_id                 INTEGER                     NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE order_table
(
    order_id                        SERIAL                      NOT NULL,
    order_number                    VARCHAR(16)                 NOT NULL,
    order_address_id                INTEGER                     NOT NULL,
    order_date_time                 TIMESTAMP WITH TIME ZONE    NOT NULL,
    order_delivered                 BOOLEAN                     NOT NULL,
    order_delivered_date_time       TIMESTAMP WITH TIME ZONE,
    order_add_info                  VARCHAR(256),
    order_restaurant_id             INTEGER                     NOT NULL,
    user_id                         INTEGER                     NOT NULL,
    PRIMARY KEY (order_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES user_table (user_id)
);

CREATE TABLE role
(
    role_id                         SERIAL                      NOT NULL,
    role_name                       VARCHAR(16)                 NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE user_role
(
    user_role_id                    SERIAL                      NOT NULL,
    user_id                         INTEGER                     NOT NULL,
    role_id                         INTEGER                     NOT NULL,
    PRIMARY KEY (user_role_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES user_table (user_id),
    CONSTRAINT fk_role
        FOREIGN KEY (role_id)
            REFERENCES role (role_id)
);

CREATE TABLE order_history
(
    order_history_id                SERIAL                      NOT NULL,
    user_id                         INTEGER                     NOT NULL,
    order_id                        INTEGER                     NOT NULL,
    PRIMARY KEY (order_history_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES user_table (user_id),
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
            REFERENCES order_table (order_id)
);

CREATE TABLE order_menu_position
(
    order_menu_position_id          SERIAL                      NOT NULL,
    menu_position_id                INTEGER                     NOT NULL,
    order_id                        INTEGER                     NOT NULL,
    PRIMARY KEY (order_menu_position_id),
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
            REFERENCES order_table (order_id)
);





