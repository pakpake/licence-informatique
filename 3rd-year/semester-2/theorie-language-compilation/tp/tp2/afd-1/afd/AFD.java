package afd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implantation d'un Automate à nombre Fini d'états et Déterministe
 * Les états sont des entiers dans [0,n[ où n est le nombre total d'états fixé à la construction. 
 * Les transitions sont mémorisées dans un tableau de table de hachage (une table par état). 
 * Les symboles d'entrée sont des caractères. 
 * Les actions sont mémorisées sous la forme d'un entier à interpréter en amont par l'application.
 */
public class AFD {

  // A ADAPTER A VOTRE ENVIRONNEMENT SYSTEME (CMD et PATH)
  
  // Commandes pour l'utilisation de dot et d'un viewer PDF sous LINUX (suppose l'installation du package graphviz et okular)
  public static String LINUX_DOT_CMD="dot -Tpdf -o %s %s";
  //public static String LINUX_VIEWPDF_CMD="nohup okular %s";
  public static String LINUX_VIEWPDF_CMD="nohup evince %s";
  
  //Commandes pour l'utilisation de dot et d'un viewer PDF sous WINDOWS (suppose l'installation du package graphviz et acrobat)
  public static String WIN_DOT_CMD="cmd.exe /C \"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe\" -Tpdf %s > %s";
  public static String WIN_VIEWPDF_CMD="cmd.exe /C \"C:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe\" %s";
    
  /**
   * Détermination de l'OS pour lancement de commande système
   * @return "linux" ou "windows" ou "inconnu"
   */
  private String getSysteme() {
      if (System.getProperty("os.name").startsWith("Linux")) return "linux";
      else if (System.getProperty("os.name").startsWith("Windows")) return "windows";
      else return "unknow"; 
  }
  
  // pour debug
  private static boolean TRACE= !true;
  
  /**
   * table des transitions (fonction delta)
   * transition[i]= fonction de transition à partir de l'état i
   */
  HashMap<Character,Integer> [] transition;
  
  /**
   * table des actions
   * action[i]= actions à effectuer à partir de l'état i
   */
  HashMap<Character,Integer> [] action;

  // état actuel de l'AFD 
  private int etat_courant;
  // état initial de l'AFD
  private int etat_initial;
  // nombre total d'états de l'AFD (fixé à la création)
  private int nb_etats;
  // ensemble des états finaux
  private Set<Integer> etat_finaux;
  
  /**
   * Création d'un AFD vide
   * @param n nombre total d'états (une fois la construction terminée)
   */
  public AFD(int n){
    action= new HashMap[n];
    transition= new HashMap[n];
    for (int i=0;i<n;i++){
      transition[i]= new HashMap<Character,Integer>();
      action[i]= new HashMap<Character,Integer>();
    }
    etat_finaux= new HashSet<Integer>();
    etat_initial= -1;
    nb_etats= n;
  }
  
  /**
   * Indication de l'état initial de l'AFD
   * @param e numéro de l'état choisi comme état de départ (e doit etre >= 0 et < nombre d'états)
   * @throws AFDException en cas de numéro d'état incorrect
   */
  public void setInitial(int e) throws AFDException{
    if ((e>=nb_etats)  || (e<0)) 
      throw new AFDException("état initial incorrect dans setInitial()");
    etat_initial= e;
    if (TRACE) System.out.println("[debug] etat initial="+etat_initial);
  }
  
  /**
   * Indication d'un état final
   * @param e numéro de l'état appartenant aux état finaux (e doit etre >= 0 et < nombre d'états)
   * @throws AFDException en cas de numéro d'état incorrect
   */
  public void setFinal(int e) throws AFDException{
    if ((e>=nb_etats)  || (e<0)) 
      throw new AFDException("état final incorrect dans setFinal()");
    etat_finaux.add(e);
    if (TRACE) System.out.println("[debug] etat final="+e);
  }
  
  /**
   * Ajout d'une transition entre deux états sur un symbole d'entrée
   * @param e1 état de départ
   * @param c symbole d'entrée
   * @param e2 état d'arrivée
   * @param a numéro de l'action à effectuer
   * @throws AFDException en cas de numéro d'état incorrect ou de transition inexistante
   */
  public void addTransition(int e1,char c, int e2, int a) throws AFDException{
    if ((e1>=nb_etats)  || (e1<0)) 
      throw new AFDException("état de départ incorrect dans addTransition()");
    if ((e2>=nb_etats)  || (e2<0)) 
      throw new AFDException("état d'arrivée incorrect dans addTransition()");   
   if (transition[e1].get(c) !=null) 
      throw new AFDException("indéterminisme dans addTransition()"); 
    transition[e1].put(c, e2);
    action[e1].put(c, a);
    if (TRACE) System.out.println("[debug] delta("+e1+",'"+c+"')="+e2);
 }  
  
  /**
   * Place l'AFD dans son état initial
   * @throws AFDException en cas d'état initial ou finaux non spécifiés
   */
  public void reset() throws AFDException{
    if (etat_initial == -1) 
      throw new AFDException("état initial non spécifié dans l'AFD");
    if (etat_finaux.isEmpty())  
      throw new AFDException("pas d'états finaux spécifiés dans l'AFD");
    etat_courant= etat_initial;
    if (TRACE) System.out.println("[debug] etat courant="+etat_courant);
  }
  
  /**
   * Applique une transition à partir de l'état courant sur la lecture d'un symbole d'entrée
   * @param c symbole d'entrée
   * @return numéro de l'action à effectuer
   * @throws AFDException en cas de transition non trouvée
   */
  public int makeTransition(char c) throws AFDException{
    if (TRACE) System.out.println("[debug] symbole= "+c);
    assert(etat_courant !=-1) : "état courant invalide dans makeTransition()";
    Integer a= action[etat_courant].get(c);
    if (TRACE) System.out.println("[debug] action a realiser="+a);
    int etat_precedant= etat_courant;
    Integer e= transition[etat_courant].get(c);
    if (e == null) 
      throw new AFDException("transition("+etat_precedant+","+c+") non trouvée");
    else
      etat_courant= e;
    if (TRACE) System.out.println("[debug] nouvel etat courant="+etat_courant);
    return (a==null) ? -1 : a; 
  }
  
  /**
   * Prédicat d'arrivée sur un état final
   * @return vrai ssi l'état courant appartient à l'ensemble des état finaux
   */
  public boolean isFinal(){
    return etat_finaux.contains(etat_courant);
  }
  
  /**
   * Construit un fichier dot représentant l'automate et 
   * l'affiche à l'écran avec un viewer PDF
   * (gère l'environnement système, Linux ou Windows)
   */
  public void toDot() {
    String sep= FileSystems.getDefault().getSeparator();
    String dot_filename = "."+sep+"aef-npi.dot";
    String pdf_filename = "."+sep+"aef-npi.pdf";
    BufferedWriter dot_file = null;
    try {
      dot_file = new BufferedWriter(new FileWriter(dot_filename));
    } catch (IOException e1) {
      System.err.println("impossible d'ouvrir en écriture le fichier \"" + dot_filename + "\")");
      System.exit(1);
    }
    try {
      dot_file.append("digraph G{").append('\n');
      dot_file.append("rankdir=LR;\n");
      dot_file.append("shape=circle;\n");
      // dot_file.append("style=filled ;\n");
      dot_file.append(etat_initial + "").append(" [shape=box]\n");
      for (int i : etat_finaux) {
        dot_file.append(i + " ");
      }
      dot_file.append("[shape=triangle] ;\n");
      for (int i = 0; i < nb_etats; i++) {
        for (char c : transition[i].keySet()) {
          Integer a= action[i].get(c);
          dot_file.append(i + "").append(" -> ").append(transition[i].get(c)+ "");
          dot_file.append(" [label=\"'");
          dot_file.append((c=='\n' ? "\\n" : String.valueOf(c)) + "'");
          dot_file.append(":"+(a==null ? "" : a+""));
          dot_file.append("\"]");
          dot_file.append(";\n");
        }
      }
      dot_file.append("}\n");
      dot_file.close();
    } catch (IOException e2) {
      System.err.println("échec lors de l'écriture du fichier dot");
      System.exit(1);
    }
    String dot_cmd;
    switch(getSysteme()) {
      case "linux":
          dot_cmd= String.format(LINUX_DOT_CMD,pdf_filename,dot_filename);
          break;
      case "windows":
          dot_cmd= String.format(WIN_DOT_CMD,dot_filename,pdf_filename);
          break;
      default:
        return;
    }
    try {
      System.out.println("tentative d'execution de la commande DOT : "+dot_cmd);
      Process dot_process=  Runtime.getRuntime().exec(dot_cmd);
      dot_process.waitFor();
      if (dot_process.exitValue()!=0) {
        System.err.println("échec de l'exécution de DOT");
      }
    } catch (Exception e) {
      System.err.println("échec de l'exécution de DOT");
    }
    String viewpdf_cmd;
    switch(getSysteme()) {
      case "linux":
          viewpdf_cmd= String.format(LINUX_VIEWPDF_CMD,pdf_filename);
          break;
      case "windows":
          viewpdf_cmd= String.format(WIN_VIEWPDF_CMD,pdf_filename);
          break;
      default:
        return;
    }
    try {
      System.out.println("tentative d'exécution de la commande de visualisation d'un PDF : "+viewpdf_cmd);
      Runtime.getRuntime().exec(viewpdf_cmd).waitFor();
    } catch (Exception e) {
      System.err.println("échec de l'exécution du viewer PDF");
    }
  }
}
