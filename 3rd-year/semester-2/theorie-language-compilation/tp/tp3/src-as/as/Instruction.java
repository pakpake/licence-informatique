/**
 * NE PAS MODIFIER
 */
package as;

import map.CodeOperation;

/**
 * Instruction MAP générée par l'assembleur
 * L'adresse des instructions dans le source sont automatiquement générées.
 */
public class Instruction {

    /**
     * adresse de l'instruction dans le code généré
     */
    private int adresse;

    /**
     * adresse de la prochaine instruction à générer 
     */
    private static int prochaine_adresse = 0;

    /**
     * étiquette éventuelle
     */
    private String etiquette;
    
    /**
     * code opération
     */
    private CodeOperation codeop;

    /**
     * valeur éventuelle de l'argument  
     */
    private String argument;

    /**
     * référence (à une étiquette) éventuelle de l'argument 
     */
    private String reference;
    
    /**
     * Remise à zéro du compteur d'adresse
     */
    public static void reset(){
      prochaine_adresse= 0;
    }
    
    /**
     * Constructeur d'une instruction complète 
     * @param etiquette associée (ou null)
     * @param code mnémonique
     * @param valeur argument de type valeur (ou null)
     * @param reference argument de type étiquette (ou null)
     */
    public Instruction(String etiquette,CodeOperation code, String valeur, String reference) {

        adresse = prochaine_adresse;
        prochaine_adresse += ((valeur !=null)||(reference != null)) ? 2 : 1;
        this.etiquette= etiquette;
        this.codeop = code;
        this.argument = valeur;
        this.reference= reference;
    }

    /**
     * Instruction symbolique sous forme textuelle pour le compilateur PTS 
     * ou écriture d'un commentaire dans le programme MAP
     * @return l'instruction au format AS-MAP
     */
    public String toString() {
        StringBuffer s = new StringBuffer();
        if (etiquette != null)
            s.append(etiquette);
        s.append(" \t");
        s.append(codeop.name());
        s.append(" \t");
        if (reference != null)
            s.append(reference);
        if (argument != null)
            s.append(argument);
        return s.toString();
    }

    /**
     * Accesseur de l'étiquette associée
     * @return l'étiquette
     */
    public String getEtiquette() {
      return etiquette;
    }

    /**
     * Modificateur de l'étiquette
     * @param etiq nouvelle étiquette
     */
    public void setEtiquette(String etiq) {
      etiquette= etiq;
    }
    /**
     * Accesseur du code opération
     * @return le codeop
     */
    public CodeOperation getCodeOp() {
      return codeop;
    }

    /**
     * Accesseur de l'argument
     * @return l'argument
     */
    public String getArgument() {
      return argument;
    }
    /**
     * Accesseur de la référence
     * @return la référence
     */
    public String getReference() {
      return reference;
    }

    /**
     * Modificateur de la référence
     * @param ref l'étiquette référencée
     */
    public void setReference(String ref) {
      reference= ref;
    }

    /**
     * Modificateur de l'adresse de l'instruction
     * @param add
     */
    public void setAdresse(int add) {
      adresse= add;
    }

    /**
     * Accesseur de l'adresse de l'instruction
     * @return l'adresse de l'instruction
     */
    public int getAdresse() {
      return adresse;
    }

}