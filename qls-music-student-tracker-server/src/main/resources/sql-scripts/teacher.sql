-- Table: public.teacher

-- DROP TABLE IF EXISTS public.teacher;

CREATE TABLE IF NOT EXISTS public.teacher
(
    id integer NOT NULL DEFAULT nextval('teacher_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    email_id character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT teacher_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.teacher
    OWNER to postgres;