-- Table: public.classroom

-- DROP TABLE IF EXISTS public.classroom;

CREATE TABLE IF NOT EXISTS public.classroom
(
    id integer NOT NULL DEFAULT nextval('classroom_id_seq'::regclass),
    teacher_id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT classroom_pkey PRIMARY KEY (id),
    CONSTRAINT classroom_teacher_id_fkey FOREIGN KEY (teacher_id)
        REFERENCES public.teacher (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.classroom
    OWNER to postgres;