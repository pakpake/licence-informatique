/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import map.Map;
import pts.ast.ArbreSyntaxique;
import pts.erreur.Erreur;
import pts.erreur.Messages;
import pts.javacc.AnalyseurSyntaxique;
import pts.javacc.ParseException;
import pts.symb.TableSymboles;
import as.Assembleur;
import as.Instruction;

/**
 * Compilateur du langage PTS.
 * Enchaine les différentes étapes de la compilation.
 * Exécute à la demande l'assemblage et l'exécution pour la MAP.
 */
public class Compilateur {
  
  /** version du compilateur */
  private static final String PTS_VERSION = "5.0";
  /** aide sur la ligne de commande */
  private static final String PTS_HELP =
      "usage: pts.Compilateur <option>* <pts_file>\n"+
      "   where <option> is one of :\n"+
      "     -d1 to print the syntax tree after the syntax analysis\n"+
      "     -d2 to print the syntax tree after the identifiers checking\n"+
      "     -d3 to print the syntax tree after the types checking\n"+
      "     -d4 to print the generated assembly code\n"+
      "     -v to display information messages between compilation stages\n"+
      "     -a to assemble the assembly code and produce map code (launch the MAP assembler)\n"+
      "     -e to exec the map code (launch the MAP virtual machine)\n"+
      "     -h to display this help\n";
  /**
   * options sur la ligne de commande
   */
  private static final String PARAM_DEBUG_SYNTAX = "-d1";
  private static final String PARAM_DEBUG_IDENT = "-d2";
  private static final String PARAM_DEBUG_TYPES = "-d3";
  private static final String PARAM_DEBUG_GEN = "-d4";
  private static final String PARAM_VERBOSE = "-v";
  private static final String PARAM_EXEC = "-e";
  private static final String PARAM_AS = "-a";
  private static final String PARAM_HELP = "-h";
  /**
   * suffixes des fichiers sources et cibles
   */
  private static final String SUFFIX_PTS = ".pts";
  private static final String SUFFIX_AS = ".as";
  private static final String SUFFIX_MAP = ".map";
  /**
   * indicateurs d'affichage des informations de débogage 
   */
  public static boolean debug_syntax_flag= false;
  public static boolean debug_ident_flag= false;
  public static boolean debug_types_flag= false;
  public static boolean debug_gen_flag= false;
  /**
   * indicateur d'affichage des informations sur le déroulement de la compilation
   */
  public static boolean verbose_flag= false;
  /**
   * indicateur d'execution du code produit avec la machine MAP
   * (implique as_flag)
   */
  public static boolean exec_flag= false;
  /**
   * indicateur d'assemblage du code produit avec AS
   */
  public static boolean as_flag= false;
  /**
   * nom du fichier source
   */
  public static String nom_base;
  /**
   * emplacement du fichier source
   */
  public static String nom_repertoire;
  /** 
   * nom du fichier source 
   */
  public static String fichier_source;
  /**
   * nom du fichier cible (en langage d'assemblage)
   */
  public static String fichier_cible_as;
  /**
   * nom du fichier cible (en langage de la MAP)
   */
  public static String fichier_cible_map;
  /**
   * AST du programme
   */
  public ArbreSyntaxique arbre_syntaxique;
  /**
   * Code cible (liste d'instructions dans le langage d'assemblage de la MAP)
   */
  public LinkedList<Instruction> code_cible;
  /**
   * Table des symboles du programme
   */
  public TableSymboles table_symboles;
  /**
   * Creation à partir du fichier source
   * @param rep répertoire contenant le source
   * @param fich nom du fichier source sans suffixe
   */
  public Compilateur(String rep, String fich) {
    
	String separateur= FileSystems.getDefault().getSeparator();
	fichier_source = rep + separateur + fich + SUFFIX_PTS;
    fichier_cible_as = rep + separateur + fich + SUFFIX_AS;
    fichier_cible_map = rep + separateur + fich + SUFFIX_MAP;
    table_symboles= new TableSymboles();
  }
  /**
   *  vérification de la syntaxe et production de l'AST
   */
  public void verifSyntaxe() {
    
    AnalyseurSyntaxique parser = null;
    try {
      parser = new AnalyseurSyntaxique(new FileInputStream(fichier_source));
    } catch (FileNotFoundException e) {
      Erreur.parametre(Messages.SOURCE_OPEN,fichier_source);
    }
    try {
      arbre_syntaxique = new ArbreSyntaxique(parser.Program());
    } catch (ParseException e) {
      Erreur.compilation(Messages.PARSING,e.getMessage());
    }
  }
  /**
   * vérification des déclarations et des références des identificateurs
   */
  public void verifIdentificateurs() {
    
    AnalyseurIdentificateurs analyseur= new AnalyseurIdentificateurs();
    analyseur.parcours(arbre_syntaxique.racine,table_symboles);
  }
  /**
   * vérifications du type des expressions
   */
  public void verifTypes() {
    
    AnalyseurTypes analyseur= new AnalyseurTypes();
    analyseur.parcours(arbre_syntaxique.racine);
  }
  /**
   * génération de code assembleur pour la MAP
   */
  public void genCode() {
    
    GenerateurCode generateur= new GenerateurCode();
    code_cible= generateur.parcours(arbre_syntaxique.racine,false,null,null,null);
  }
  /**
   * ecriture du code assembleur dans le fichier cible
   */
  public void emetCodeAs(){
    
    FileWriter fichier = null;
    try {
      fichier = new FileWriter(fichier_cible_as);
    } catch (IOException e) {
      Erreur.parametre(Messages.TARGET_OPEN,fichier_cible_as);
    }
    Iterator<Instruction> iter= code_cible.iterator();
    while(iter.hasNext()){
      Instruction ins= iter.next();
      try {
        fichier.write(ins.toString());
        fichier.write("\n");
        if (debug_gen_flag) displayMessage(ins.toString());
      } catch (IOException e2) {
        Erreur.systeme(Messages.TARGET_WRITE,fichier_cible_as);
      }
    }
    try {
      fichier.write("\n");
      fichier.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
  /**
   * ecriture du code MAP dans le fichier cible
   */
  public void emetCodeMap(){
    
    Assembleur.main(new String[]{fichier_cible_as,fichier_cible_map});
  }
  /**
   * execution du code cible par la MAP
   */
  public void execCodeMap(){
    
    Map.main(new String[]{fichier_cible_map});
  }
  /**
   * Vérifie et analyse les arguments de la ligne de commande. 
   * Détermine les paramètres globaux de la compilation.
   * @param args paramètres de la ligne de commande
   */
  private static void verifParametres(String[] args) {
    
    String nom_fichier= null;
    for (int i = 0; i < args.length; i++) {
      switch(args[i]) {
      case PARAM_DEBUG_SYNTAX:
        debug_syntax_flag = true;
        break;
      case PARAM_DEBUG_IDENT : {
        debug_ident_flag = true;
        break;
      }
      case PARAM_DEBUG_TYPES : {
        debug_types_flag = true;
        break;
      } 
      case PARAM_DEBUG_GEN : {
        debug_gen_flag = true;
        break;
      } 
      case PARAM_VERBOSE : {
        verbose_flag = true;
        break;
      } 
      case PARAM_EXEC : {
        exec_flag = true;
        as_flag = true;
        break;
      } 
      case PARAM_AS : {
        as_flag = true;
        break;
      } 
      case PARAM_HELP : {
        displayMessage(PTS_HELP);
        finCompilation(0);
        break;
      } 
      default : 
        if (args[i].startsWith("-")) {
        Erreur.parametre(Messages.PARAM_UNKNOW,args[i]);
        } else if (args[i].endsWith(SUFFIX_PTS)) {
          nom_fichier = args[i].substring(0,args[i].lastIndexOf('.'));
          Path chemin_fichier= FileSystems.getDefault().getPath(nom_fichier);
          nom_base = chemin_fichier.getFileName().toString();
          nom_repertoire = chemin_fichier.getParent().toString();
        } else {
          Erreur.parametre(Messages.PARAM_SUFFIX,args[i]);
        }
      }
    }
    if (nom_fichier == null) {
      Erreur.parametre(Messages.PARAM_SRC);
    }
  }
  /**
   * Affichage d'un message d'information sur la ligne de commande
   * @param msg contenu du message à afficher
   */
  static void displayMessage(String msg){
    
    System.out.println(msg);
  }
  /**
   * Opérations à réaliser au démarrage du compilateur
   */
  static void debutCompilation(){
    
    displayMessage("Compilateur PTS version "+PTS_VERSION+"\n");
  }
  /**
   * Opérations à réaliser à l'arrêt du compilateur
   * @param val code de sortie (0 si fin de la compilation sans erreur)
   */
  public static void finCompilation(int val){
    
    displayMessage("\nsortie du compilateur avec le code "+val);
    System.exit(val);
  }
  /**
   * lanceur des différentes étapes du compilateur
   * @param args arguments de la ligne de commande
   */
  public static void main(String[] args) {

    debutCompilation();
    verifParametres(args);
    Compilateur compilo= new Compilateur(nom_repertoire,nom_base);
    if (verbose_flag) displayMessage("- fichier source : "+fichier_source);
    if (verbose_flag) displayMessage(" - analyse syntaxique");
    compilo.verifSyntaxe();
    if (debug_syntax_flag) compilo.arbre_syntaxique.toDot(nom_repertoire,"ast1_"+nom_base);
    if (verbose_flag) displayMessage(" - vérification des identificateurs");
    compilo.verifIdentificateurs();
    if (debug_ident_flag) compilo.arbre_syntaxique.toDot(nom_repertoire,"ast2_"+nom_base);
    if (verbose_flag) displayMessage(" - analyse des types");
    compilo.verifTypes();
    if (debug_types_flag) compilo.arbre_syntaxique.toDot(nom_repertoire,"ast3_"+nom_base);
    if (verbose_flag) displayMessage(" - génération de code assembleur");
    compilo.genCode();
    if (verbose_flag) displayMessage(" - émission du code assembleur");
    compilo.emetCodeAs();
    displayMessage("  => fichier AS généré : "+fichier_cible_as);
    if (as_flag){
      if (verbose_flag) displayMessage("- génération du code MAP");
      compilo.emetCodeMap();
      displayMessage("  => fichier MAP généré :"+fichier_cible_map);
    }
    if (exec_flag){
      if (verbose_flag) displayMessage(" - exécution du code MAP");
      compilo.execCodeMap();
      displayMessage("  => fin de l'exécution");
    }
    finCompilation(0);
  }
}
