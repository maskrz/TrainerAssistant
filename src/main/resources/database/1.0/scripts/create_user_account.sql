-- Table: user_account

CREATE TABLE user_account
(
  user_id serial NOT NULL,
  create_timestamp timestamp without time zone,
  refresh_timestamp timestamp without time zone,
  active boolean,
  email character varying(255),
  first_name character varying(255),
  last_name character varying(255),
  login character varying(255),
  password character varying(255),
  password_hash character varying(255),
  CONSTRAINT user_account_pkey PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_account
  OWNER TO postgres;
