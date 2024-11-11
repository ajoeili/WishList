-- Inserting sample users
INSERT INTO Users (username, email, password)
VALUES
    ('maria', 'maria@example.com', 'hashed_password_1'),
    ('jesper', 'jesper@example.com', 'hashed_password_2'),
    ('natasja', 'natasja@example.com', 'hashed_password_3');

-- Inserting sample wish lists
INSERT INTO WishLists (user_id, title, description)
VALUES
    (1, 'Christmas Wish List', 'My Christmas Wish List!'),
    (2, 'Birthday Wish List', 'Things I want for my birthday this year'),
    (3, 'New Year Wish List', 'Help me start fresh with a new year!');

-- Inserting sample items
INSERT INTO Items (wish_list_id, name, description, price, url, photo_url, manufacturer_link)
VALUES
    (1, 'Laptop', 'A powerful laptop for work and gaming', 7000.00, 'http://example.com/laptop', 'http://example.com/images/laptop.jpg', 'http://example.com/laptop-manufacturer'),
    (1, 'Headphones', 'Noise-canceling headphones for music lovers', 950.00, 'http://example.com/headphones', 'http://example.com/images/headphones.jpg', 'http://example.com/headphones-manufacturer'),
    (2, 'Smartphone', 'Latest model of a smartphone with great camera', 1200.00, 'http://example.com/smartphone', 'http://example.com/images/smartphone.jpg', 'http://example.com/smartphone-manufacturer'),
    (2, 'Book', 'A best-selling novel to read during the holidays', 150.00, 'http://example.com/book', 'http://example.com/images/book.jpg', 'http://example.com/book-manufacturer');
