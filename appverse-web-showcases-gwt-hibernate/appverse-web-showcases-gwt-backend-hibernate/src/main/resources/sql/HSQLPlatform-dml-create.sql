-- Roles

-- Sequence problem with HSQLDB and table generator
-- http://stackoverflow.com/questions/17353182/why-is-hibernate-not-using-the-next-sequence-value-in-the-sequence-table
-- http://gautirao.wordpress.com/2011/05/19/using-table-generator-in-hibernatejpa/
-- http://stackoverflow.com/questions/13303338/is-the-element-of-initialvalue-of-tablegenerator-not-supported-in-hibernate-j

INSERT INTO ROLE (ID, CREATED, CREATED_BY, NAME, STATUS, ACTIVE, UPDATED, UPDATED_BY, VERSION) VALUES (1, current_date, '', 'ROLE_FOR_TEST1', 'a', 1, current_date, '', 1);
INSERT INTO ROLE (ID, CREATED, CREATED_BY, NAME, STATUS, ACTIVE, UPDATED, UPDATED_BY, VERSION) VALUES (2, current_date, '', 'ROLE_FOR_TEST2', 'a', 1, current_date, '', 1);
INSERT INTO ROLE (ID, CREATED, CREATED_BY, NAME, STATUS, ACTIVE, UPDATED, UPDATED_BY, VERSION) VALUES (3, current_date, '', 'ROLE_FOR_TEST3', 'a', 1, current_date, '', 1);
INSERT INTO ROLE (ID, CREATED, CREATED_BY, NAME, STATUS, ACTIVE, UPDATED, UPDATED_BY, VERSION) VALUES (4, current_date, '', 'ROLE_FOR_TEST4', 'a', 1, current_date, '', 1);

-- CAUTION! Assigning a calculated sequence value using the current sequence value is not working propertly with Hibernate / hsqldb and table generator for the sequence
-- UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 2 WHERE SEQ_NAME = 'USER_SEQ';
-- Assign the value directly instead in your test scripts
UPDATE SEQUENCE SET SEQ_COUNT = 5 WHERE SEQ_NAME = 'ROLE_SEQ';


--Users
INSERT INTO USER(ID, CREATED, CREATED_BY, EMAIL, LASTNAME, NAME, PASSWORD, ACTIVE, UPDATED, UPDATED_BY, VERSION, STATUS) VALUES (1, current_date, 'GWTShowcase', 'test@email.com', '', 'Surname', 'Name', true, current_date, 'GWTShowcase', 1, 'a');
INSERT INTO USER(ID, CREATED, CREATED_BY, EMAIL, LASTNAME, NAME, PASSWORD, ACTIVE, UPDATED, UPDATED_BY, VERSION, STATUS) VALUES (2, current_date, 'GWTShowcase', 'test2@email.com', '', 'Surname2', 'Name2', true, current_date, 'GWTShowcase', 1, 'a');

-- CAUTION! Assigning a calculated sequence value using the current sequence value is not working propertly with Hibernate / hsqldb and table generator for the sequence
-- UPDATE SEQUENCE SET SEQ_COUNT = SEQ_COUNT + 2 WHERE SEQ_NAME = 'USER_SEQ';
-- Assign the value directly instead in your test scripts
UPDATE SEQUENCE SET SEQ_COUNT = 3 WHERE SEQ_NAME = 'USER_SEQ';

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 1);