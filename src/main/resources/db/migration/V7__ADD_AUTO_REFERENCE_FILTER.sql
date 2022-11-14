ALTER TABLE myfilter
    ADD parent_id uuid,
    ADD CONSTRAINT fk_parentId FOREIGN KEY (parent_id) REFERENCES myfilter(id);
