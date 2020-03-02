CREATE SEQUENCE public.hibernate_sequence
    INCREMENT 1
    START 0
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.hibernate_sequence
    OWNER TO postgres;

CREATE TABLE public.teacher
(
    id bigint NOT NULL,
    cash double precision,
    date date,
    language character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    photo character varying(255) COLLATE pg_catalog."default",
    score double precision,
    status boolean,
    CONSTRAINT teacher_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.teacher
    OWNER to postgres;
