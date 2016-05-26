-- Table: property

-- DROP TABLE property;

CREATE TABLE property
(
  name character varying NOT NULL,
  value character varying(3800) NOT NULL,
  CONSTRAINT property_pkey PRIMARY KEY (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE property
  OWNER TO postgres;
