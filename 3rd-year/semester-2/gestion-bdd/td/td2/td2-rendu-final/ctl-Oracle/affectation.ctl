LOAD DATA
APPEND
INTO TABLE affectation
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
idVol,
dateVol,
idPilote,
idAvion,
nbPassagers
)
