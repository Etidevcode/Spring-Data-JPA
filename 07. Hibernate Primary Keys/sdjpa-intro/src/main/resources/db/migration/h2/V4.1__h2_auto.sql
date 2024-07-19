/*
 * Etienne KOA :  18/07/2024
 */

-- Définir les placeholders pour les types de colonnes id
-- Les valeurs de ces placeholders seront définies dans les fichiers de configuration Spring

-- Supprimer les tables si elles existent déjà
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

-- Créer la table book
CREATE TABLE book (
    id ${id_type} NOT NULL,
    isbn VARCHAR(255),
    publisher VARCHAR(255),
    title VARCHAR(255),
    author_id BIGINT,
    PRIMARY KEY (id)
);

-- Créer la table author
CREATE TABLE author (
    id ${id_type} NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    PRIMARY KEY (id)
);
