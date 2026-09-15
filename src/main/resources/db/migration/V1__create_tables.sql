CREATE TABLE store
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    relay_device_id  VARCHAR(100),
    sensor_device_id VARCHAR(100),
    entry_token      VARCHAR(100) NOT NULL,
    door_open_sec    INT          NOT NULL,
    created_at       DATETIME     NOT NULL,
    CONSTRAINT uq_store_entry_token UNIQUE (entry_token)
);

CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    name          VARCHAR(100) NOT NULL,
    role          VARCHAR(20)  NOT NULL,
    store_id      BIGINT,
    created_at    DATETIME     NOT NULL,
    CONSTRAINT uq_users_email UNIQUE (email),
    CONSTRAINT fk_users_store FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE access_log
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    store_id      BIGINT      NOT NULL,
    actor_type    VARCHAR(20) NOT NULL,
    actor_user_id BIGINT,
    contact       VARCHAR(30),
    event_type    VARCHAR(20) NOT NULL,
    result        VARCHAR(20) NOT NULL,
    occurred_at   DATETIME    NOT NULL,
    CONSTRAINT fk_access_log_store FOREIGN KEY (store_id) REFERENCES store (id),
    CONSTRAINT fk_access_log_actor_user FOREIGN KEY (actor_user_id) REFERENCES users (id)
);

CREATE TABLE otp_code
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    store_id      BIGINT       NOT NULL,
    phone         VARCHAR(20)  NOT NULL,
    code_hash     VARCHAR(255) NOT NULL,
    attempt_count INT          NOT NULL,
    verified      BOOLEAN      NOT NULL,
    expires_at    DATETIME     NOT NULL,
    created_at    DATETIME     NOT NULL,
    CONSTRAINT fk_otp_code_store FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE door_state
(
    store_id   BIGINT PRIMARY KEY,
    status     VARCHAR(20) NOT NULL,
    changed_at DATETIME    NOT NULL,
    CONSTRAINT fk_door_state_store FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE alert
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    store_id   BIGINT       NOT NULL,
    type       VARCHAR(30)  NOT NULL,
    message    VARCHAR(500) NOT NULL,
    resolved   BOOLEAN      NOT NULL,
    created_at DATETIME     NOT NULL,
    CONSTRAINT fk_alert_store FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE inquiry
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100)  NOT NULL,
    contact    VARCHAR(100)  NOT NULL,
    message    VARCHAR(1000) NOT NULL,
    status     VARCHAR(20)   NOT NULL,
    created_at DATETIME      NOT NULL
);