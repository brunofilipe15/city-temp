
CREATE TABLE cities (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE temperatures (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  temp FLOAT NOT NULL,
  date TIMESTAMP WITH TIME ZONE NOT NULL
);
