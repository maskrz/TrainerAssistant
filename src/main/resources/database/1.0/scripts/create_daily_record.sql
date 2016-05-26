-- Table: daily_record

-- DROP TABLE daily_record;

CREATE TABLE daily_record
(
  user_id integer NOT NULL,
  record_date date NOT NULL,
  t1 integer,
  t2 integer,
  t3 integer,
  wsr real,
  CONSTRAINT daily_record_pkey PRIMARY KEY (user_id, record_date),
  CONSTRAINT daily_record_user_account_fkey FOREIGN KEY (user_id)
      REFERENCES user_account (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE daily_record
  OWNER TO postgres;
