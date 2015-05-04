CREATE TABLE md_city (
  id       BIGINT NOT NULL AUTO_INCREMENT,
  name     CHARACTER VARYING(15),
  ordering INTEGER,
  CONSTRAINT md_city_pkey PRIMARY KEY (id)
)
  ENGINE =MyISAM;

CREATE TABLE md_neighborhood (
  id      BIGINT NOT NULL AUTO_INCREMENT,
  name    CHARACTER VARYING(60),
  city_id BIGINT,
  CONSTRAINT md_neighborhood_pkey PRIMARY KEY (id),
  CONSTRAINT fk_nghb_city FOREIGN KEY (city_id) REFERENCES md_city (id)
    MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
  ENGINE =MyISAM;

CREATE TABLE tracers (
  id         BIGINT NOT NULL AUTO_INCREMENT,
  ip         BIGINT,
  user       VARCHAR(50),
  url        INT,
  city       INT,
  nbh        INT,
  priceMin   DECIMAL,
  priceMax   DECIMAL,
  createDate DATE,
  PRIMARY KEY (id)
)
  ENGINE =MyISAM;

ALTER TABLE rt_address ADD lat NUMERIC;
ALTER TABLE rt_address ADD lng NUMERIC;