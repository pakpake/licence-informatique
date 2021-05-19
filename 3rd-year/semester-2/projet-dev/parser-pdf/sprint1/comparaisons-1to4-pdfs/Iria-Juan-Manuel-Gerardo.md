# 1 - pdftotext

`-layout`

PRO
respect des tableaux
CONS
moins compacte, mais c'est la meilleure facon de comprendre les tableaux
double colonne

`-raw`

non respect des tableaux (fautes aux espaces)
le plus compacte de tous

# 2 - pdf2txt

unique colone
moins bon respect des tableaux

## CCL

les tableaux (et formules de maths sont toujours un pb à causes des espaces)

* si les tableaux sont les plus important => `pdftotext -layout`

* sinon le plus compacte est le mieux => `pdftotext -raw`
