/*
 * Etienne KOA :  18/07/2024
 */

ALTER TABLE credit_card MODIFY COLUMN cvv varchar(30);

ALTER TABLE credit_card
MODIFY COLUMN expiration_date varchar(30);