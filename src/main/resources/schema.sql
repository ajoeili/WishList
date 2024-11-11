-- 'Users' table to store user information
CREATE TABLE Users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- 'WishLists' table to store wish lists belonging to users
CREATE TABLE WishLists (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           title VARCHAR(255) NOT NULL,
                           description TEXT,
                           FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- 'Items' table to store items on the wish lists
CREATE TABLE Items (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       wish_list_id BIGINT NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       price DECIMAL(10, 2),
                       url VARCHAR(255),
                       photo_url VARCHAR(255),
                       manufacturer_link VARCHAR(255),
                       FOREIGN KEY (wish_list_id) REFERENCES WishLists(id)
);

