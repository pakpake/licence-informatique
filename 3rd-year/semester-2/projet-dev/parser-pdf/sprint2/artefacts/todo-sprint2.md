# A faire

> langage de programmation : **python**

* entrée : le dossier des fichiers PDF
* sortie :
    * créer un sous dossier
    * si le sous dossier existe déjà, on le détruit et on le re-créer
    * y déposer les fichiers .txt convertit
    * les renomer avec le meme nom que les fichiers PDF d'origine

* mesurer le temps d'execution du programme avec la commande `time`


On doit convertir avec la commande (et les bonnes options) `pdftotext` ou `pdf2txt` les fichiers PDF.
La sortie de chaque conversion doit être parsée de la manière suivante :

+ Le nom du fichier d’origine (dans une ligne)

+ Le titre du papier (dans une ligne)

+ Le résumé (abstract) de l’auteur (dans une ligne)

*Considérez que les sections ne sont pas forcement toutes présentes dans les papiers.*


lance programme python > appel la commande `pdftotext` ou `pdf2txt` avec les bonnes options > enregistre en output.txt >  parse output.txt pour en ressortir uniquement (titre+résumé) > renome output.txt avec le nom du fichier PDF original

## après, test des performance


### A faire

* filename.pdf sans Corpus_2021/filenamep.pdf avec un split sur le "/" par exemple, ou avec un split en fonction du nom de dossier passé en paramètre
* vérifier que le paramètre est bien un dossier et qu'il n'est pas vide et qu'il contient QUE des PDF
* index term à enlever si on peut (à réfléchir)
* boucle/Script time  10 000 fois

(* filtrer le "1" avant introduction avec une regex et ssi 1 existe)
