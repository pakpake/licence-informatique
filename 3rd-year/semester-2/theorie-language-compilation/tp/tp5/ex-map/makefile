##########################################################
# makefile pour le test de la MAP
#
# s'utilise dans le repertoire ou se trouve le repertoire
# "map" des classes de la MAP et le repertoire "ex-map" 
# des exemples de programmes ecrit dans le binaire de la MAP 
# 
# commandes pour lancer le makefile:
#
# make add
# make calladd
# ...
# make swap
#
# ou bien pour enchainer tous les tests :
#
# make all
##########################################################
# modifier eventuellement les chemins ci dessous

# repertoire racine (ou se trouve ce makefile)
DIR_BASE=.

# fichiers de test 
DIR_TEST= $(DIR_BASE)/ex-map

# machine MAP (classes de la MAP)
DIR_MAP= $(DIR_BASE)

# ne rien modifier en dessosu de cette ligne
##########################################################

#commande pour lancer la JVM avec les classes de la MAP
JAVA= java -classpath $(DIR_MAP)

#commande pour lancer la MAP
MAP= $(JAVA) map.Map 

##########################################################

#fichiers pour les tests
ADD_MAP=$(DIR_TEST)/add.map
CALLADD_MAP=$(DIR_TEST)/callAdd.map
CPT_MAP=$(DIR_TEST)/cpt.map
TRI_MAP=$(DIR_TEST)/tri.map
PGCD_MAP=$(DIR_TEST)/pgcd.map
FACT_MAP=$(DIR_TEST)/fact.map
FIBO_MAP=$(DIR_TEST)/fibo.map
SWAP_MAP=$(DIR_TEST)/swap.map

###########################################################

default:
	@echo "erreur ! choisissez une cible : make all ou make add ou make calladd ou ..."

all: add calladd cpt tri pgcd fact fibo swap

add:	
	$(MAP) $(ADD_MAP)

calladd:	
	$(MAP) $(CALLADD_MAP)

cpt:	
	$(MAP) $(CPT_MAP)

tri:	
	$(MAP) $(TRI_MAP)

pgcd:	
	$(MAP) $(PGCD_MAP)

fact:	
	$(MAP) $(FACT_MAP)

fibo:	
	$(MAP) $(FIBO_MAP)

swap:	
	$(MAP) $(SWAP_MAP)

clean :





