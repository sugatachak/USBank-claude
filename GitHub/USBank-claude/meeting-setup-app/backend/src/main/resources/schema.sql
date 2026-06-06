CREATE TABLE IF NOT EXISTS Members (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    agenda     VARCHAR(500),
    date       VARCHAR(20),
    starttime  VARCHAR(10),
    duration   INT,
    agendaitems VARCHAR(1000),
    restrictions VARCHAR(500),
    status     VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP  DEFAULT CURRENT_TIMESTAMP
);
