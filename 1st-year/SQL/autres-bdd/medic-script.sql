--TD 2 : L1 Conception et interrogation

    --1)b)
--i) la liste des traitements donnés à Yannick Le Gall le 20/11/2010

select traitement
from PERSONNES, VISITETRAITEMENT, visites, traitements
where PERSONNES.IdP=VISITETRAITEMENT.IdV and PERSONNES.IdP=VISITETRAITEMENT.IdT and prenom='Yannick' and date_visite=20/11/2010;