-- Increase password column size for BCrypt (60 characters)
ALTER TABLE USERS 
MODIFY COLUMN password VARCHAR(60) NOT NULL;
-- Remove salt column (if exists)
ALTER TABLE USERS DROP COLUMN salt;