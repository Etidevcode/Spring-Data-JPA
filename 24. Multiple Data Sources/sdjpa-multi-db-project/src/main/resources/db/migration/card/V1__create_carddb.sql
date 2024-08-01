/*
 * Etienne KOA :  18/07/2024
 */

DROP TABLE IF EXISTS credit_card;

CREATE TABLE credit_card (
    id bigint not null auto_increment,
    cvv varchar(20),
    expiration_date varchar(20),
    PRIMARY KEY (id)
);