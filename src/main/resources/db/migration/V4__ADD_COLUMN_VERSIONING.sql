ALTER TABLE myfilter
    ADD version INTEGER NOT NULL,
    ADD updated_at TIMESTAMP,
    ADD deleted_at TIMESTAMP;