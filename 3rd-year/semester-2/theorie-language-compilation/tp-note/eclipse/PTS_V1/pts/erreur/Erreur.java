/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.erreur;

import pts.Compilateur;

/**  
 * Gestion de tous les messages d'erreur (ou pas) du compilateur 
 */
public class Erreur{
  
  /**
   * Affichage d'un message d'information
   * @param msg le message
   */
  public static void message(TypeErreurs type,String msg,Object ... params){
    System.out.format("["+type.toString().toLowerCase()+"] : "+msg, params);
  }

  /**
   * Affichage d'un message d'erreur système.
   * Stoppe la compilation 
   * @param m le message
   */
  public static void systeme(Messages m,Object ... params) {
    message(TypeErreurs.SYSTEME,m.toString(),params);
    Compilateur.finCompilation(m.ordinal());
  }

  /**
   * Affichage d'un message d'erreur interne
   * Stoppe la compilation
   * @param m message
   */
  public static void interne(Messages m,Object ... params) {
    message(TypeErreurs.INTERNE,m.toString(),params);
    Compilateur.finCompilation(m.ordinal());
  }

  /**
   * Affichage d'un message d'erreur sur la ligne de commande
   * Stoppe la compilation 
   * @param m le message
   */
  public static void parametre(Messages m,Object ... params) {
    message(TypeErreurs.PARAMETRE,m.toString(),params);
    Compilateur.finCompilation(m.ordinal());
  }

  /**
   * Affichage d'un message d'erreur de compilation
   * Stoppe la compilation 
   * @param m le message
   */
  public static void compilation(Messages m,Object ... params) {
    message(TypeErreurs.COMPILATION,m.toString(),params);
    Compilateur.finCompilation(m.ordinal());
  }
}


