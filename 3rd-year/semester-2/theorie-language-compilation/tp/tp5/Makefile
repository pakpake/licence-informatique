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
# make tests
#
# ou pour créer le .jar a remettre :
#
# make jar
#
##########################################################
# modifier eventuellement les chemins ci dessous

# fichiers de test 
DIR_TEST= ex-map

# machine MAP (classes de la MAP)
DIR_MAP= map

# nom du jar contenant la machine MAP
MAP_JAR= map.jar

# sources Java
JAVA_SRC= $(DIR_MAP)/Chargeur.java \
	  $(DIR_MAP)/CodeOperation.java \
	  $(DIR_MAP)/EntreesSorties.java  \
	  $(DIR_MAP)/Instruction.java \
	  $(DIR_MAP)/Erreur.java  \
	  $(DIR_MAP)/Map.java \
	  $(DIR_MAP)/Memoire.java \
	  $(DIR_MAP)/Sequenceur.java \
	  $(DIR_MAP)/Registres.java
	  
# classes java
JAVA_CLASS= $(DIR_MAP)/*.class

# ne rien modifier en dessosu de cette ligne
##########################################################

#commande pour lancer la MAP
MAP= java -cp $(MAP_JAR) $(DIR_MAP).Map

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
	@echo "erreur ! choisissez une cible : tests jar clean"

clean :
	rm -f $(DIR_MAP)/*.class $(MAP_JAR)

eval : extract clean jar tests cat

extract : 
	jar -xvf $(MAP_JAR) 

cat	:
	cat $(DIR_MAP)/Instruction.java

tests : jar add calladd cpt tri pgcd fact fibo swap

jar : $(MAP_JAR) 
		
$(MAP_JAR) : $(JAVA_CLASS) 
	jar -cvf $(MAP_JAR) $(JAVA_CLASS) $(JAVA_SRC)  

$(JAVA_CLASS) : $(JAVA_SRC)
	javac -cp . $(JAVA_SRC)

add : 
	$(MAP) $(ADD_MAP) < test-add.txt

calladd :  
	$(MAP) $(CALLADD_MAP) < test-add.txt

cpt :  
	$(MAP) $(CPT_MAP)  

fact :  
	$(MAP) $(FACT_MAP) < test-fact.txt

swap :  
	$(MAP) $(SWAP_MAP) < test-swap.txt

fibo :  
	$(MAP) $(FIBO_MAP) < test-fibo.txt

tri :  
	$(MAP) $(TRI_MAP) < test-tri.txt

pgcd :  
	$(MAP) $(PGCD_MAP) < test-pgcd.txt





