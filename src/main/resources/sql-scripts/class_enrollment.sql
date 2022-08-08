-- Table: public.class_enrollment

-- DROP TABLE IF EXISTS public.class_enrollment;

CREATE TABLE IF NOT EXISTS public.class_enrollment
(
    student_id bigint NOT NULL,
    class_id bigint NOT NULL,
    CONSTRAINT class_enrollment_pkey PRIMARY KEY (student_id, class_id),
    CONSTRAINT class_enrollment_class_id_fkey FOREIGN KEY (class_id)
        REFERENCES public.classroom (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT class_enrollment_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.class_enrollment
    OWNER to postgres;