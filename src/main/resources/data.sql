DROP TABLE IF EXISTS movie_genres;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS genres;

CREATE TABLE movies (
    id INT IDENTITY PRIMARY KEY,
    original_title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    released_at DATE,
    created_at SMALLDATETIME NOT NULL DEFAULT(CURRENT_TIMESTAMP),
    updated_at SMALLDATETIME
);

CREATE TABLE genres (
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at SMALLDATETIME NOT NULL DEFAULT(CURRENT_TIMESTAMP),
    updated_at SMALLDATETIME
);

CREATE TABLE movie_genres (
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

INSERT INTO movies (
    original_title,
    description,
    released_at
) VALUES (
    'Avengers: Endgame',
    'After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos'' actions and restore balance to the universe.',
    '2019-04-25'
), (
    'Avengers: Infinity War',
    'The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.',
    '2018-04-26'
);

INSERT INTO genres (name) VALUES ('Action'), ('Adventure'), ('Sci-Fi'), ('Drama');

INSERT INTO movie_genres (
    movie_id,
    genre_id
) VALUES (
    1,
    1
), (
    1,
    2
), (
    1,
    4
), (
    2,
    1
), (
    2,
    2
), (
    2,
    3
);