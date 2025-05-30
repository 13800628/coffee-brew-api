-- src/main/resources/schema.sql
CREATE TABLE coffee_bean (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    origin VARCHAR(255),
    flavor VARCHAR(255)
);