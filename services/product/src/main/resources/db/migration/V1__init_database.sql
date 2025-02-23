CREATE SEQUENCE IF NOT EXISTS category_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS product_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE category
(
    id          INTEGER NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                 INTEGER          NOT NULL,
    name               VARCHAR(255),
    description        VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price              NUMERIC(38, 2)   NOT NULL,
    category_id        INTEGER,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT fdfsdf1213fdfdsfsfvcxvcjj FOREIGN KEY (category_id) REFERENCES category (id);