CREATE TABLE accounts (
  iban VARCHAR,
  currency VARCHAR(100) NOT NULL,
  balance double NOT NULL,
  update_time TIMESTAMP,

  PRIMARY key (iban)
);
