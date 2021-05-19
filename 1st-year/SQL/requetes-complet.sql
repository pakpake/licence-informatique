drop TABLE LESACTEURS;
drop TABLE SALLESCINEMA;
drop TABLE FILMS_SALLES;
drop TABLE LESFILMS;
CREATE TABLE LESFILMS  (
       IdF NUMBER(2) CONSTRAINT pklesfilms PRIMARY KEY,
       titre VARCHAR(100),
       pays VARCHAR(50),
       annee NUMBER(2),
       realisateur VARCHAR(50),
       duree NUMBER(2) 
);

CREATE TABLE LESACTEURS (
       IdF VARCHAR2(4)
       	   CONSTRAINT fklesacteurslesfilms REFERENCES LESFILMS(IdF),
       acteur VARCHAR(50),
       CONSTRAINT pklesacteurs  PRIMARY KEY (IdF,acteur)
);

CREATE TABLE SALLESCINEMA (
		IdS VARCHAR2(4) CONSTRAINT pk_sallescinema PRIMARY KEY,
		NOM VARCHAR(100),
		VILLE VARCHAR(50)
);

CREATE TABLE FILMS_SALLES (
        IdS VARCHAR2(4) CONSTRAINT fk_films_sallessallescinema REFERENCES SALLESCINEMA(IdS),
        IdF VARCHAR2(4) CONSTRAINT fkfilms_salleslesfilms REFERENCES LESFILMS(IdF),
		--contrainte table
        CONSTRAINT pkfilms_salles PRIMARY KEY (IdS, IdF)
);
