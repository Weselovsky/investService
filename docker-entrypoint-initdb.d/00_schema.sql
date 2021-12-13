CREATE TABLE papers
(
    id      BIGSERIAL PRIMARY KEY,
    name    TEXT        NOT NULL,
    type    TEXT,
    ticker  TEXT        NOT NULL,
    price   INT         NOT NULL CHECK ( price > 0 ),
    qty     INT         NOT NULL CHECK ( qty > 0 ),
    profit  INT         NOT NULL,
    sector  TEXT        NOT NULL,
    image   TEXT        NOT NULL,
    removed BOOLEAN     NOT NULL DEFAULT FALSE,
    created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP

);
CREATE TABLE sales
(
    id       BIGSERIAL PRIMARY KEY,
    paper_id BIGINT      NOT NULL REFERENCES papers,
    name     TEXT        NOT NULL,
    price    INT         NOT NULL CHECK ( price > 0 ),
    qty      INT         NOT NULL CHECK ( qty > 0 ),
    type     TEXT        NOT NULL CHECK ( type IN ('sale', 'buy') ),
    removed  BOOLEAN     NOT NULL DEFAULT FALSE,
    created  timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

