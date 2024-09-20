-- Create the database
CREATE DATABASE phonebook;

-- Use the created database
USE phonebook;

-- Create the LIST_PHONEBOOK table
CREATE TABLE LIST_PHONEBOOK (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone_num VARCHAR(255) NOT NULL,
    work_phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    CONSTRAINT list_phonebook_pkey PRIMARY KEY (id)
);