CREATE TABLE md_city
(
  id bigint NOT NULL,
  name character varying(15),
  ordering integer,
  CONSTRAINT md_city_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE md_neighborhood
(
  id bigint NOT NULL,
  name character varying(60),
  city_id bigint,
  CONSTRAINT md_neighborhood_pkey PRIMARY KEY (id),
  CONSTRAINT fk_nghb_city FOREIGN KEY (city_id) REFERENCES md_city (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);