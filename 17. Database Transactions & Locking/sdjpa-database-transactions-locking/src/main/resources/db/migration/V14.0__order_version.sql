/*
 * Etienne KOA :  18/07/2024
 */

alter table order_header
    add column version integer;

alter table order_line
    add column version integer;