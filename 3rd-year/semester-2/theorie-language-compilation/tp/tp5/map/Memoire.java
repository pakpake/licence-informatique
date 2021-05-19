/**
 * Machine à pile MAP : mémoire et registres internes
 * @author F. Raimbault
 */
package map;

import static map.Registres.*;

/**
 * Mémoire de la machine MAP 
 */
public final class Memoire { // NE PAS MODIFIER

    /**
     * Contenu de la mémoire
     */
    private static short[] contenu = new short[Map.VALEUR_MAX];

    /**
     * Lecture de la mémoire
     * @param ad adresse 
     * @return contenu à l'adresse ad
     */
    public static short lireEmplacement(int ad){
      try{
        return contenu[ad];
      }catch (ArrayIndexOutOfBoundsException e){
        Erreur.execution("accès mémoire illégal (lecture à l'adresse "+ad+")");
        return 0;
      }
    }

    /**
     * Ecriture de la mémoire d'une valeur à une adresse donnée
     * @param ad adresse 
     * @param val valeur
     */
    public static void ecrireEmplacement(int ad, int val){
      try{
        contenu[ad] = (short) val;
      }catch (ArrayIndexOutOfBoundsException e){
        Erreur.execution("accès mémoire illégal (écriture à l'adresse "+ad+")");
      }
    }

    /**
     * Lecture d'une variable globale
     * @param n numéro de la variable globale
     * @return le contenu de la variable globale n
     */
    public static short lireVariableGlobale(short n) {
        return lireEmplacement(GP.lire() + n);
    }

    /**
     * Ecriture d'une variable globale
     * @param n numéro de la variable globale
     * @param v nouvelle valeur de la variable globale n
     */
    public static void ecrireVariableGlobale(short n, short v) {
        ecrireEmplacement(GP.lire() + n, v);
    }

    /**
     * Lecture d'une variable locale
     * @param n numéro de la variable locale
     * @return le contenu de la variable locale n
     */
    public static short lireVariableLocale(short n) {
        return lireEmplacement(FP.lire() + n);
    }

    /**
     * Ecriture d'une variable locale
     * @param n numéro de la variable locale
     * @param v nouvelle valeur de la variable locale n
     */
    public static void ecrireVariableLocale(short n, short v) {
        ecrireEmplacement(FP.lire() + n, v);
    }

    /**
     * Lecture d'un code d'instruction. 
     * @param n adresse de l'instruction
     * @return le codeop de l'instruction à l'adresse n
     */
    public static Instruction lireInstruction(short n) {
        Instruction ins;
        if ((n >= GP.lire()) || (n < 0)){
          Erreur.execution("débordement de la zone de code");
          return null;
        }
        CodeOperation codeop= CodeOperation.decode(lireEmplacement(n));
        if (codeop==null){
          Erreur.execution("instruction illégale (codeop="+lireEmplacement(n)+")");
          return null;
        }
        if (codeop.argumentSuit()){ // lecture de l'argument
          if (n+1 >= GP.lire()) {
            Erreur.execution("debordement de la zone de code");
            return null;
          }
          short arg= lireEmplacement(n+1);
          ins= new Instruction(n,codeop,arg);
        }else{
          ins= new Instruction(n,codeop);
        }
        return ins;
    }

    /**
     * Ajout d'une instruction (ou de l'argument de l'instruction précédente) 
     * dans la zone de code, au dessus des instructions précédentes.
     * Met à jour les registres GP, FP0, FP et SP.
     * @param i instruction (ou argument) à  ajouter
     */
    public static void ajoutInstruction(short i) {
        ecrireEmplacement(GP.lire(), i);
        GP.increment(1);
        FP0.ecrire(FP.ecrire(SP.ecrire(GP.lire())));
    }

    /**
     * Réserve de la mémoire pour des variables globales 
     * et les initialise.
     * Met à jour les registres FP0, FP et SP.
     * @param n nombre de variables globales à  réserver
     */
    public static void reserverVariableGlobale(short n) {
        for (int i = 0; i < n; i++) {
            ecrireEmplacement(GP.lire() + i, 0);
        }
        FP0.ecrire(GP.lire() + n);
        SP.ecrire(FP.ecrire(FP0.lire()));
    }

    /**
     * Empile une valeur au sommet de la pile d'exécution.
     * Met à jour le registre SP.
     * @param x valeur à  empiler
     */
    public static void empiler(int x) {
        if (x >= Map.VALEUR_MAX)
            Erreur.execution("dépassement de la capacité d'un mot");
        ecrireEmplacement(SP.lire(), x);
        SP.increment(1);
    }

    /**
     * Réserve de la mémoire pour des variables locales
     * et les initialise.
     * Met à jour le registre SP.
     * @param n nombre de variables locales à  réserver
     */
    public static void reserverVariableLocale(short n) {
        for (int i = 0; i < n; i++) {
            ecrireEmplacement(SP.lire(), 0);
        }
        SP.increment(n);
    }

    /**
     * Libère la mémoire occupée par des variables locales.
     * Met à jour le registre SP.
     * @param n nombre de variables locales à  libérer
     */
    public static void libererVariableLocale(short n) {
        SP.increment(- n);
    }

    /**
     * Dépile la valeur en sommet de la pile d'exécution.
     * Met à jour le registre SP.
     * @return la valeur en sommet de pile
     */
    public static short depiler() {
        SP.increment(-1);
        return lireEmplacement(SP.lire());
    }

    /**
     * Initialise un nouveau cadre d'exécution.
     * Met à jour les registres SP et FP.
     */
    public static void sauveFP() {
        empiler(FP.lire());
        FP.ecrire(SP.lire());
    }

    /**
     * Restaure le cadre d'exécution précédent
     * Met à jour les registres SP et FP.
     */
    public static void restaureFP() {
        SP.ecrire(FP.lire());
        FP.ecrire(depiler());
    }

    /**
     * Représentation du contenu complet de la mémoire 
     * jusqu'au sommet de pile
     * et la valeurs des registres internes.
     * Pour déboggage.
     * @return une chaine représentant le contenu de la mémoire 
     * et des registres
     */
    public static String dump() {
        StringBuffer s = new StringBuffer();
        for (int i = SP.lire() - 1; i >= 0; i--) {
            s.append("mem[").append(i).append("]=");
            s.append(lireEmplacement(i)).append('\n');
        }
        s.append("SP=").append(SP.lire()).append('\n');
        s.append("FP=").append(FP.lire()).append('\n');
        s.append("FP0=").append(FP0.lire()).append('\n');
        s.append("GP=").append(GP.lire()).append('\n');
        return s.toString();
    }

    /**
     * Représentation du cadre d'activation courant 
     * et des registres principaux.
     * Pour déboggage.
     * @return une chaine représentant le contenu du cadre d'activation et 
     * des registres
     */
    public static String dumpFrame() {
        StringBuffer s = new StringBuffer();
        int prev_FP = (FP.lire() != FP0.lire()) ? contenu[FP.lire() - 1] : FP0.lire();
        for (int i = SP.lire() - 1; i >= prev_FP; i--) {
            s.append("mem[").append(i).append("]=");
            s.append(contenu[i]).append('\n');
        }
        for (int i = FP0.lire() - 1; i >= GP.lire(); i--) {
            s.append("mem[").append(i).append("]=");
            s.append(contenu[i]).append('\n');
        }
        s.append("SP= ").append(SP.lire()).append(", ");
        s.append("FP= ").append(FP.lire()).append(", ");
        s.append("FP0= ").append(FP0.lire()).append(", ");
        s.append("GP= ").append(GP.lire()).append('\n');
        return s.toString();
    }

}