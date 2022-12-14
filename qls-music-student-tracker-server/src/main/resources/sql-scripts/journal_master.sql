-- Table: public.journal_master

-- DROP TABLE IF EXISTS public.journal_master;

CREATE TABLE IF NOT EXISTS public.journal_master
(
    id integer NOT NULL DEFAULT nextval('journal_master_id_seq'::regclass),
    class_id bigint NOT NULL,
    prompt character varying COLLATE pg_catalog."default" NOT NULL,
    assigned_date date,
    due_date date,
    max_points smallint NOT NULL DEFAULT 100,
    CONSTRAINT journal_master_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.journal_master
    OWNER to postgres;