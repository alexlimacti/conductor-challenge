--CREATE SEQUENCE terminal_id_seq AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE TABLE tb_terminal
(
    id      BIGINT AUTO_INCREMENT,
    logic   INTEGER     NOT NULL,
    serial  VARCHAR(80) NOT NULL,
    model   VARCHAR(11) NOT NULL,
    sam     INTEGER,
    ptid    VARCHAR(80),
    plat    INTEGER,
    version VARCHAR(80) NOT NULL,
    mxr     INTEGER,
    mxf     INTEGER,
    verfm   VARCHAR(80)
);