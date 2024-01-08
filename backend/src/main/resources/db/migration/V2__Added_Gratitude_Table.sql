CREATE TABLE gratitude
(
    id          SERIAL PRIMARY KEY,
    begin       TIMESTAMP NOT NULL,
    "end"        TIMESTAMP NOT NULL,
    customer_id INT       NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);