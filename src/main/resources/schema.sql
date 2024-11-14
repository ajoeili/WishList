CREATE TABLE USERS (
                       USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       USERNAME VARCHAR(255),
                       EMAIL VARCHAR(255),
                       PASSWORD VARCHAR(255)
);

CREATE TABLE WISHLISTS (
                           WISHLIST_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                           USER_ID BIGINT,
                           NAME VARCHAR(255),
                           DESCRIPTION TEXT,
                           FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);


CREATE TABLE WISHITEMS (
                           WISH_ITEM_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                           NAME VARCHAR(255),
                           DESCRIPTION TEXT,
                           PRICE DECIMAL(10, 2),
                           WISHLIST_ID BIGINT NOT NULL,
                           FOREIGN KEY (WISHLIST_ID) REFERENCES wishlists(WISHLIST_ID)
);



