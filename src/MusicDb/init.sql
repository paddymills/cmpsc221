-- slqite dialect

-- remove tables if they exist
DROP TABLE IF EXISTS musicTitles;
DROP TABLE IF EXISTS musicAwards;

CREATE TABLE musicTitles (
    musicID INT NOT NULL PRIMARY KEY,
    genre VARCHAR(30) NOT NULL,
    artist VARCHAR(30) NOT NULL,
    title VARCHAR(30) NOT NULL,
    album VARCHAR(50) NOT NULL,
    releaseDate INT NOT NULL
);

CREATE TABLE musicAwards (
    musicID INT NOT NULL PRIMARY KEY,
    award VARCHAR(50) NOT NULL,
    awardYear INT NOT NULL
);

-- insert songs
INSERT INTO musicTitles (musicID, genre, artist, title, album, releaseDate)
VALUES
    (1, 'Rock', 'Pink Floyd', 'Learning to Fly', 'A Momentary Lapse of Reason', 1987),
    (2, 'Pop', 'Halsey', 'Without Me', 'Manic', 2018),
    (3, 'Alternative Rock', 'Gorillaz', 'Clint Eastwood', 'Gorillaz', 2001),
    (4, 'Death Metal', 'Arch Enemy', 'As the Pages Burn', 'War Eternal', 2014),
    (5, 'Dance Pop', 'Bag Raiders', 'Shooting Stars', 'Bag Raiders', 2009),
    (6, 'Pop', 'Eric Clapton', 'Change the World', 'Phenomenon', 1996),
    (7, 'Alternative Rock', 'Blue October', 'Into the Ocean', 'Foled', 2006),
    (8, 'Rock', 'U2', 'City of Blinding Lights', 'How to Dismantle an Atomic Bomb', 2004),
    (9, 'Trance', 'Arty & Mat Zo', 'Rebound', 'AnjunaBeats Presents Mat Zo 01', 2011),
    (10, 'Alternative Rock', 'Coldplay', 'Speed of Sound', 'X&Y', 2005);

-- insert song awards
INSERT INTO musicAwards (musicID, award, awardYear)
VALUES
    (1, 'MTV Videa Music Award: Best Concept Video', 1988),
    (8, 'Grammy: Best Rock Song', 2006),
    (6, 'Grammy: Song of the Year', 1997),
    (2, 'American Music Award: Favorite Pop/Rock Song', 2019),
    (3, 'MTV Europe Music Award: Best Song', 2001),
    (10, 'Brit Award: Song of the Year', 2006);
