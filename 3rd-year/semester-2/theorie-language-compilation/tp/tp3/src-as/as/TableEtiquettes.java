/**
 * NE PAS MODIFIER
 */
package as;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Gestion d'une table des symboles pour les étiquettes de l'assembleur.
 * Maintient une table des références non définies (référence en avant) 
 * et des références résolues.
 * 
 */
public class TableEtiquettes{

    /** références non résolues */
    private Hashtable<String,Instruction> nondefinis;
    /** références résolues */
    private Hashtable<String,Instruction> definis;
    
    /**
     * Constructeur de la table des symboles
     */
    public TableEtiquettes() {
        nondefinis= new Hashtable<String,Instruction>();
        definis = new Hashtable<String,Instruction>();
    }
    
    /**
     * Ajoute une définition dans la table des symboles
     * @param lbl nom de l'étiquette
     * @param ins instruction associée à l'étiquette
     */
    public void definir(String lbl,Instruction ins){
        definis.put(lbl,ins);
        nondefinis.remove(lbl);
    }
    
    /**
     * Ajoute une référence en avant dans la table des symboles
     * @param lbl nom de l'étiquette
     * @param ins instruction qui contient une référence à ident
     */
    public void referencer(String lbl, Instruction ins){
        if (nondefinis.containsKey(lbl)){
            // déja référencée
        }else{// première référence
            nondefinis.put(lbl,ins);
        }
    }

    /**
     * Instruction associée à une étiquette (supposée résolue)
     * @param ident nom de l'étiquette
     * @return l'instruction associée
     */
    public Instruction symboleDe(String ident){
      return definis.get(ident);
    }
      
    /**
     * Prédicat de présence d'une étiquette dans la table des symboles
     * @param ident nom de l'étiquette
     * @return vrai ssi l'étiquette est présente dans la table des symboles
     */
    public boolean contient(String ident){
      return definis.containsKey(ident);
    }
    
    /**
     * Prédicat de résolution de toutes les références 
     * @return vrai ssi toutes les références sont résolues
     */
    public boolean tousDefinis(){
      return nondefinis.isEmpty();
    }
    
    /**
     * Parcours de toutes les références non résolues
     * @return enumérateur de toutes les références non résolues
     */
    public Enumeration<String> enumerationIndefinis(){
        return nondefinis.keys();
    }
}
