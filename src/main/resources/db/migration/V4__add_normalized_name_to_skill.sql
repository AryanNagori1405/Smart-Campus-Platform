ALTER TABLE skill
    ADD COLUMN normalized_name VARCHAR(255);

UPDATE skill
    SET normalized_name = LOWER(TRIM(name));

ALTER TABLE skill
    ALTER COLUMN normalized_name SET NOT NULL;

ALTER TABLE skill
    ADD CONSTRAINT uk_skill_normalized_name UNIQUE (normalized_name);