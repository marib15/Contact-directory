CREATE TABLE "PERSON" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "NAME" VARCHAR(50),
    "SURNAME" VARCHAR(50),   
    "NOTE" VARCHAR(255)
);

CREATE TABLE "CONTACT" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "PERSONID" BIGINT REFERENCES PERSON (ID),    
    "TYPE" SMALLINT NOT NULL,
    "NOTE" VARCHAR(255)
);

CREATE TABLE "MAILCONTACT" (
    "CONTACTID" BIGINT NOT NULL REFERENCES CONTACT (ID),
    "MAILADDRESS" VARCHAR(70),
    PRIMARY KEY(CONTACTID, MAILADDRESS)
);

CREATE TABLE "PHONECONTACT" (
    "CONTACTID" BIGINT NOT NULL REFERENCES CONTACT (ID),
    "PHONENUMBER" VARCHAR(20),
    PRIMARY KEY(CONTACTID, PHONENUMBER)
);