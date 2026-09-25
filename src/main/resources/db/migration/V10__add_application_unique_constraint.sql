ALTER TABLE application
    ADD CONSTRAINT uk_application_student_job
        UNIQUE (student_id, job_id);