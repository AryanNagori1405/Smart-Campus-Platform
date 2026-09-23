CREATE TABLE job_skill(
    job_id INTEGER,
    skill_id INTEGER,

    PRIMARY KEY (job_id, skill_id),

    FOREIGN KEY (job_id) REFERENCES job(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id)
)