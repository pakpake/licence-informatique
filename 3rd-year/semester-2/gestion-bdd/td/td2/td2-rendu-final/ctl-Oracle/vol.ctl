LOAD DATA
APPEND
INTO TABLE vol
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
idVol,
villeDepart,
villeArrivee,
heureDepart,
heureArrivee,
jour,
decalage
)
