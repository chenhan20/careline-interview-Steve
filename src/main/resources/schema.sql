set mode MySQL;

DROP TABLE IF EXISTS tmp;
CREATE TABLE tmp (
    key VARCHAR(50) NOT NULL, 
    value VARCHAR(200) DEFAULT NULL, 
    desc VARCHAR(200) DEFAULT NULL, 
    PRIMARY KEY (key)
);

DROP TABLE IF EXISTS H_USER ;
CREATE TABLE H_USER (
    ID VARCHAR(50) NOT NULL, 
    EMAIL VARCHAR(200) NOT NULL, 
    NAME VARCHAR(200) NOT NULL, 
    PASSWORD VARCHAR(200) NOT NULL, 
    PRIMARY KEY (ID)
);