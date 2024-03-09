DROP TABLE IF EXISTS note_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL
);

INSERT IGNORE INTO user (username) VALUES
('user1'),
('user2'),
('user3'),
('user4');

CREATE TABLE note (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    archived BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT IGNORE INTO note (user_id, title, content) VALUES
(1, 'Note 1', 'Content of Note 1'),
(1, 'Note 2', 'Content of Note 2'),
(2, 'Note 3', 'Content of Note 3'),
(3, 'Note 4', 'Content of Note 4'),
(3, 'Note 5', 'Content of Note 5'),
(4, 'Note 6', 'Content of Note 6');

CREATE TABLE tag (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

INSERT IGNORE INTO tag (name) VALUES
('Tag1'),
('Tag2'),
('Tag3'),
('Tag4'),
('Tag5');

CREATE TABLE note_tag (
    note_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (note_id, tag_id),
    FOREIGN KEY (note_id) REFERENCES note(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);

INSERT IGNORE INTO note_tag (note_id, tag_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 4),
(6, 5);
