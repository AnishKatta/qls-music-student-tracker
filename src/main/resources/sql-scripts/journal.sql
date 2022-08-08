-- Table: public.journal

-- DROP TABLE IF EXISTS public.journal;

CREATE TABLE IF NOT EXISTS public.journal
(
    journal_id bigint NOT NULL,
    student_id bigint NOT NULL,
    text character varying COLLATE pg_catalog."default" NOT NULL,
    feedback_text character varying COLLATE pg_catalog."default",
    submitted_date date,
    CONSTRAINT journal_pkey PRIMARY KEY (journal_id, student_id),
    CONSTRAINT journal_journal_id_fkey FOREIGN KEY (journal_id)
        REFERENCES public.journal_master (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT journal_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.journal
    OWNER to postgres;