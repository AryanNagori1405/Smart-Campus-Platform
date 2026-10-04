ALTER TABLE student
    ADD COLUMN account_id INTEGER UNIQUE;

ALTER TABLE student
    ADD CONSTRAINT fk_student_account
        FOREIGN KEY (account_id)
            REFERENCES account(id);