-- V100: create items and order_item tables
CREATE TABLE items
(
    id    SERIAL PRIMARY KEY,
    uid   uuid          NOT NULL,
    name  VARCHAR(1024) NOT NULL,
    count INT           NOT NULL
);

CREATE UNIQUE INDEX idx_items_uid ON items (uid);
CREATE UNIQUE INDEX idx_items_name ON items (name);

CREATE TABLE order_items
(
    id        SERIAL PRIMARY KEY,
    order_uid uuid NOT NULL
);

CREATE UNIQUE INDEX idx_order_items_order_uid ON order_items (order_uid);

CREATE TABLE order_items_cross
(
    items_id      INT
        CONSTRAINT fk_order_items_cross_items_id REFERENCES items (id),
    order_item_id INT
        CONSTRAINT fk_order_items_cross_order_item_id REFERENCES order_items (id)
);

CREATE UNIQUE INDEX idx_order_items_cross_order_item_id ON order_items_cross(order_item_id);
CREATE UNIQUE INDEX idx_order_items_cross_items_id ON order_items_cross(items_id);