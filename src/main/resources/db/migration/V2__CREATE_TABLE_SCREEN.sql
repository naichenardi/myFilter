
CREATE TABLE screen
(
    id UUID NOT NULL,
    name character varying(255) COLLATE pg_catalog.default,
    contentJson text,
    PRIMARY KEY (id)
);

