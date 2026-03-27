CREATE TABLE friend (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    paid BOOLEAN DEFAULT FALSE
);

CREATE TABLE item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price DOUBLE
);

CREATE TABLE item_friend (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    item_id BIGINT,
    friend_id BIGINT,
    FOREIGN KEY (item_id) REFERENCES item(id),
    FOREIGN KEY (friend_id) REFERENCES friend(id)
);