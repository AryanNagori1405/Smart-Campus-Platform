CREATE TABLE student_skill (
    student_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,

    PRIMARY KEY (student_id, skill_id),

    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id)
);