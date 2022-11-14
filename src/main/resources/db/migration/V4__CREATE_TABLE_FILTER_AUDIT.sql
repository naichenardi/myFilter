CREATE TABLE myfilter_audit
(
    id integer,
    filter_id UUID NOT NULL,
    user_id UUID NOT NULL,
    parent_id UUID,
    updated_by UUID,
    created_by UUID,
    screen_id UUID,
    name character varying(255) COLLATE pg_catalog.default,
    Data text,
    outputFilter character varying(255) COLLATE pg_catalog.default,
    status varchar(30),
    CONSTRAINT fk_filter_id FOREIGN KEY (filter_id) REFERENCES myfilter(id),
    CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES myfilter(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_created_by FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT fk_updated_by FOREIGN KEY (updated_by) REFERENCES users(id),
    CONSTRAINT fk_screen FOREIGN KEY (screen_id) REFERENCES screen(id)
)