ALTER TABLE myfilter
    ADD deleted_by uuid,
    ADD CONSTRAINT fk_deletedBy FOREIGN KEY (deleted_by) REFERENCES users(id);
