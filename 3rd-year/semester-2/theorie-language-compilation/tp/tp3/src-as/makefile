################################################################################## 

# chemin d'acces au jar de la MAP => A ADAPTER a votre configuration
MAP_JAR=./map.jar

# repertoire ou` se trouve les sources et les .class de l'assembleur
AS_DIR=./as

# repertoire contenant les fichiers en langage d'assemblage
EX_AS_DIR=./ex-as

# repertoire ou sont ecrit les fichiers generes par l'assembleur
EX_MAP_DIR=./ex-as

# nom du jar contenant l'assembleur
AS_JAR=./as-map.jar

# sources Java
JAVA_SRC= $(AS_DIR)/Assembleur.java \
	  $(AS_DIR)/Scanner.java \
	  $(AS_DIR)/Erreur.java  \
	  $(AS_DIR)/Instruction.java \
	  $(AS_DIR)/Programme.java  \
	  $(AS_DIR)/TableEtiquettes.java \
	  $(AS_DIR)/TypeUniteLexicale.java \
	  $(AS_DIR)/UniteLexicale.java
	  
# classes java
JAVA_CLASS= $(JAVA_SRC:%.java=%.class)
	  
##################################################################################

#commande pour lancer la MAP
MAP=java -jar $(MAP_JAR)

#commande pour lancer l'assembleur
AS_MAP=java -cp $(AS_JAR):$(MAP_JAR) as.Assembleur

##################################################################################

default: 
	@echo "choisir une cible : all, jar, clean ou tests"

all : jar tests

clean :
	rm -f $(EX_AS_DIR)/*.map $(AS_DIR)/*.class $(AS_DIR)/Scanner.java $(AS_JAR)

jar : $(AS_JAR) 
		
tests : add callAdd cpt fact swap tri pgcd 

$(AS_JAR) : $(JAVA_CLASS) 
	jar -cvf $(AS_JAR) $(AS_DIR)/*.class $(JAVA_SRC) $(AS_DIR)/Scanner.lex 

$(JAVA_CLASS) : $(JAVA_SRC)
	javac -cp .:$(MAP_JAR) $(JAVA_SRC)

$(AS_DIR)/Scanner.java : $(AS_DIR)/Scanner.lex
	jflex $(AS_DIR)/Scanner.lex

add : $(EX_MAP_DIR)/add.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/add.map

$(EX_MAP_DIR)/add.map : $(EX_AS_DIR)/add.as
	$(AS_MAP) $(EX_AS_DIR)/add.as $(EX_MAP_DIR)/add.map

callAdd : $(EX_MAP_DIR)/callAdd.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/callAdd.map

$(EX_MAP_DIR)/callAdd.map : $(EX_AS_DIR)/callAdd.as
	$(AS_MAP) $(EX_AS_DIR)/callAdd.as $(EX_MAP_DIR)/callAdd.map

cpt : $(EX_MAP_DIR)/cpt.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/cpt.map

$(EX_MAP_DIR)/cpt.map : $(EX_AS_DIR)/cpt.as
	$(AS_MAP) $(EX_AS_DIR)/cpt.as $(EX_MAP_DIR)/cpt.map

fact : $(EX_MAP_DIR)/fact.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/fact.map

$(EX_MAP_DIR)/fact.map : $(EX_AS_DIR)/fact.as
	$(AS_MAP) $(EX_AS_DIR)/fact.as $(EX_MAP_DIR)/fact.map

swap : $(EX_MAP_DIR)/swap.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/swap.map

$(EX_MAP_DIR)/swap.map : $(EX_AS_DIR)/swap.as
	$(AS_MAP) $(EX_AS_DIR)/swap.as $(EX_MAP_DIR)/swap.map

tri : $(EX_MAP_DIR)/tri.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/tri.map

$(EX_MAP_DIR)/tri.map : $(EX_AS_DIR)/tri.as
	$(AS_MAP) $(EX_AS_DIR)/tri.as $(EX_MAP_DIR)/tri.map

pgcd : $(EX_MAP_DIR)/pgcd.map $(AS_JAR)
	$(MAP) $(EX_MAP_DIR)/pgcd.map

$(EX_MAP_DIR)/pgcd.map : $(EX_AS_DIR)/pgcd.as
	$(AS_MAP) $(EX_AS_DIR)/pgcd.as $(EX_MAP_DIR)/pgcd.map

