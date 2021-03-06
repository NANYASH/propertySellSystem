CREATE TABLE ADVERT2 (
  ID         NUMBER PRIMARY KEY,
  TITLE                  VARCHAR(50) NOT NULL,
  DESCRIPTION            VARCHAR(200) NOT NULL,
  PRICE                  NUMBER NOT NULL,
  CURRENCY               NVARCHAR2(50) NOT NULL CHECK(CURRENCY = 'UAN' OR CURRENCY = 'EUR' OR CURRENCY = 'USD'),
  CREATE_DATE            DATE NOT NULL,
  UPDATE_DATE            DATE NOT NULL,
  AVAILABLE_FROM_DATE    DATE NOT NULL,
  AVAILABLE_TO_DATE      DATE NOT NULL,
  ID_USER                NUMBER,
  ID_PROPERTY            NUMBER,
  FOREIGN KEY(ID_USER) REFERENCES USER2(ID),
  FOREIGN KEY(ID_PROPERTY) REFERENCES PROPERTY(ID)
)