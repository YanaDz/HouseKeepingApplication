CREATE TABLE house
(
    id             BIGSERIAL PRIMARY KEY,
    house_name     VARCHAR(255) NOT NULL,
    house_address  VARCHAR(255) NOT NULL,
    entranceNumber INTEGER      NOT NULL,
    version        BIGINT                DEFAULT 0,
    created        TIMESTAMP    NOT NULL DEFAULT now(),
    modified       TIMESTAMP
);

CREATE TABLE entrance
(
    id            BIGSERIAL PRIMARY KEY,
    entrance_name VARCHAR(255) NOT NULL,
    floor_number  INTEGER      NOT NULL,
    house_id      BIGINT       NOT NULL REFERENCES house (id),
    version       BIGINT                DEFAULT 0,
    created       TIMESTAMP    NOT NULL DEFAULT now(),
    modified      TIMESTAMP
);

CREATE TABLE floor
(
    id           BIGSERIAL PRIMARY KEY,
    floor_number VARCHAR(255) NOT NULL,
    entrance_id  BIGINT       NOT NULL REFERENCES entrance (id),
    version      BIGINT                DEFAULT 0,
    created      TIMESTAMP    NOT NULL DEFAULT now(),
    modified     TIMESTAMP
);

CREATE TABLE flat
(
    id            BIGSERIAL PRIMARY KEY,
    flat_number   VARCHAR(255)  NOT NULL,
    flat_type     INTEGER       NOT NULL DEFAULT 1,
    full_area     NUMERIC(5, 2) NOT NULL,
    register_area NUMERIC(5, 2) NOT NULL,
    floor_id  BIGINT       NOT NULL REFERENCES floor (id),
    version       BIGINT                 DEFAULT 0,
    created       TIMESTAMP     NOT NULL DEFAULT now(),
    modified      TIMESTAMP
);

CREATE TABLE occupant
(
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    second_name   VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255) NOT NULL,
    role          INTEGER      NOT NULL DEFAULT 2,
    status        INTEGER      NOT NULL DEFAULT 1,
    occupant_type INTEGER      NOT NULL DEFAULT 0,
    version       BIGINT                DEFAULT 0,
    created       TIMESTAMP    NOT NULL DEFAULT now(),
    modified      TIMESTAMP
);

CREATE TABLE occupant_flat
(
    occupant_id BIGINT NOT NULL REFERENCES occupant (id),
    flat_id     BIGINT NOT NULL REFERENCES flat (id),
    PRIMARY KEY (occupant_id, flat_id)
);

