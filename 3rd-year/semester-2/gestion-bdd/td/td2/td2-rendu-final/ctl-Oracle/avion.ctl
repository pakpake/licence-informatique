LOAD DATA
APPEND
INTO TABLE avion
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
idAvion,
typeAppareil,
miseEnService
)
