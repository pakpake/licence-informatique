/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.erreur;

/**
 * Tous les messages d'erreur générés par le compilateur
 */
public enum Messages {

  DOT_WRITE("échec lors de l'écriture du fichier dot %s"), 
  DOT_EXEC("échec lors de l'exécution de dot %s"), 
  DOT_OPEN("impossible d'ouvrir le fichier %s en écriture"), 
  OKULAR_ERROR("échec lors de l'exécution de okular %s"), 
  TARGET_WRITE("problème d'écriture sur le fichier cible %s"),
  TARGET_OPEN("impossible d'ouvrir le fichier cible %s en écriture"), 
  PARAM_SRC("manque le nom du fichier source PTS"), 
  PARAM_SUFFIX("suffixe de fichier inconnu : \" %s \""),
  PARAM_UNKNOW("paramètre inconnu : \" %s \""),
  SOURCE_OPEN("impossible de lire le fichier source %s"), 
  PARSING("erreur de syntaxe (%s)"),
  IDENTIFIER_UNKNOW("identificateur %s inconnu ligne %d"),
  IDENTIFIER_ALREADY_DEFINED("identificateur %s (ligne %d) déja défini localement"),
  TYPE_PARAM("le paramètre %s (ligne %d) n'est pas d'un type élémentaire"),
  TYPE_ASSIGN("types incompatibles dans l'affectation ligne %d"), 
  TYPE_READ("le type de la variable lue ligne %d n'est pas d'un type élémentaire"),
  TYPE_WRITE("le type de l'expression affichée ligne %d n'est pas d'un type élémentaire"),
  TYPE_CONDITION("le type de la condition ligne %d n'est pas booléen"),	//TODO: case (identique au if)
  TYPE_BASIC_AWAITED("type scalaire attendu ligne %d"),
  TYPE_BOOLEAN_AWAITED("type booléen attendu ligne %d"), 
  TYPE_INTEGER_AWAITED("type entier attendu ligne %d"), 
  TYPE_ARRAY_AWAITED("indexation d'un identificateur qui n'est pas un tableau ligne %d"),
  TYPE_FUNCTION_AWAITED("appel sur un identificateur qui n'est pas une fonction ligne %d"),
  TYPE_ARRAY_INDEX("l'index utilisé ligne %d n'est pas un entier"),
  TYPE_ARGS_NUMBER("nombre d'argument incorrect ligne %d"),
  TYPE_ARG("argument utilisé ligne %d de type non basique"),
  TYPE_ARG_MISMATCH("argument utilisé ligne %d de type incompatible avec le paramètre formel"),
  TYPE_RETURN("la valeur retournée ligne %d ne correspond pas au type de retour de la fonction"),
  TYPE_LOGICAL_EXP("valeur non booléenne ligne %d dans l'expression logique"),
  TYPE_RELATIONAL_EXP("comparaison ligne %d entre types incompatibles dans l'expression relationnelle"),
  TYPE_ARITHMETIC_EXP("opération ligne %d entre types incompatibles dans l'expression arithmétique"),
  ;
  
  // texte en clair du message
  String msg;
  
  Messages(String m){
    msg= m;
  }
  
  /**
   * Chaine contenant le texte du message
   * @see java.lang.Enum#toString()
   */
  public String toString(){
    return msg;
  }
}
