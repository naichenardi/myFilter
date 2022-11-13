CREATE TABLE myfilter
(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    name character varying(255) COLLATE pg_catalog.default,
    Data text,
    outputFilter character varying(255) COLLATE pg_catalog.default,
    screen_id UUID,
    PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_screen FOREIGN KEY (screen_id) REFERENCES screen(id)
)