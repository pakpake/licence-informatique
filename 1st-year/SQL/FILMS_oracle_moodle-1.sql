drop table LESACTEURS;
drop table LESFILMS;

CREATE TABLE LESFILMS  (
       IdF VARCHAR2(4) CONSTRAINT pklesfilms PRIMARY KEY,
       titre VARCHAR2(100),
       pays VARCHAR2(50),
       annee NUMBER,
       realisateur VARCHAR2(50),
       duree NUMBER 
);

CREATE TABLE LESACTEURS (
       IdF VARCHAR2(4)
       	   CONSTRAINT fklesacteurslesfilms REFERENCES LESFILMS(IdF),
       acteur VARCHAR2(50),
       CONSTRAINT pklesacteurs  PRIMARY KEY (IdF,acteur)
);




INSERT INTO LESFILMS VALUES ('1' , 'A Armes égales' , 'USA' , 1982 , 'Frankenheimer', 108 );
INSERT INTO LESFILMS VALUES ('2' , 'A Armes égales' , 'USA',1998,'Ridley Scott',125 );
INSERT INTO LESFILMS VALUES ('3','ABC Africa','Iran',2001,'Kiarostami',83 );
INSERT INTO LESFILMS VALUES ('4','A bout de souffle','France',1960,'Godard',90 );
INSERT INTO LESFILMS VALUES ('5','Accattone','Italie',1961,'Pasolini',115 );
INSERT INTO LESFILMS VALUES ('6','Les acteurs','France',2000,'Blier',103 );
INSERT INTO LESFILMS VALUES ('7','Adieu Bonaparte','France-Egypte',1985,'Chahine',120 );
INSERT INTO LESFILMS VALUES ('8','L affaire Ciceron','USA',1952,'Mankiewicz',108 );
INSERT INTO LESFILMS VALUES ('9','A l attaque','France',2000,'Guediguian',90 );
INSERT INTO LESFILMS VALUES ('10','Le bal des casse-pieds','France',1992,'Robert',98 );
INSERT INTO LESFILMS VALUES ('11','Médée','Italie',1969,'Pasolini',110 );
INSERT INTO LESFILMS VALUES ('12','Le guépard','Italie',1963,'Visconti',205 );
INSERT INTO LESFILMS VALUES ('13','Le samourai','France',1967,'J-P. Melville',105 );

INSERT INTO LESACTEURS VALUES ('1','Scott Glenn'); 
INSERT INTO LESACTEURS VALUES ('8','D. Darrieux'); 
INSERT INTO LESACTEURS VALUES ('2','Derni More');
INSERT INTO LESACTEURS VALUES ('8' , 'James Mason');
INSERT INTO LESACTEURS VALUES ('2' , 'Viggo Mortensen');
INSERT INTO LESACTEURS VALUES ('9' , 'J-P. Darroussin');
INSERT INTO LESACTEURS VALUES ('4' , 'Belmondo');
INSERT INTO LESACTEURS VALUES ('9' , 'A. Ascaride');
INSERT INTO LESACTEURS VALUES ('4' , 'pakpake Seberg');
INSERT INTO LESACTEURS VALUES ('10' , 'Piccoli');
INSERT INTO LESACTEURS VALUES ('4' , 'J-P. Melville');
INSERT INTO LESACTEURS VALUES ('10' , 'J. Rochefort');
INSERT INTO LESACTEURS VALUES ('5' , 'Franco Citti');
INSERT INTO LESACTEURS VALUES ('10' , ' Miou-Miou');
INSERT INTO LESACTEURS VALUES ( '6' , 'J-P. Marielle');
INSERT INTO LESACTEURS VALUES ('11' , 'Maria Callas');
INSERT INTO LESACTEURS VALUES ('6' , 'J. Villeret');
INSERT INTO LESACTEURS VALUES ('11' , 'M. Girotti');
INSERT INTO LESACTEURS VALUES ('6' , 'Piccoli');
INSERT INTO LESACTEURS VALUES ('12' , 'A. Delon');
INSERT INTO LESACTEURS VALUES ('6' , 'A. Delon');
INSERT INTO LESACTEURS VALUES ('12' , 'B. Lancaster');
INSERT INTO LESACTEURS VALUES ('6' , 'Belmondo');
INSERT INTO LESACTEURS VALUES ('13' , 'A. Delon');
INSERT INTO LESACTEURS VALUES ('7' , 'Piccoli');
