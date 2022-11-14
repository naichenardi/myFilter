ALTER TABLE myFilter
ADD COLUMN created_at timestamp,
ADD COLUMN updated_at timestamp;

ALTER TABLE myfilter_audit
    ADD COLUMN created_at timestamp,
    ADD COLUMN updated_at timestamp;