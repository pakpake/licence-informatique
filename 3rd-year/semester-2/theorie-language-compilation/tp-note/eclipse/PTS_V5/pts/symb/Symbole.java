/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */

package pts.symb;

import pts.type.Type;

/**
 * Symbole correspondant à un identificateur de la table des symboles 
 */
public class Symbole {

    /**
     * identificateur associé
     */
    private String ident;

    public String getIdent() {
      return ident;
    }
    
    public void setIdent(String ident) {
      this.ident = ident;
    }

    /**
     * Type du symbole. 
     * Attribut mis à jour dans AnalyseurTypes.parcours()
     */
    private Type type;

    public Type getType() {
      return type;
    }
    
    public Type setType(Type type) {
      this.type = type;
      return type;
    }

    /**
     * Numéro de bloc : 0 pour une variable globale, > 0 sinon 
     * MAJ à la déclaration
     */
    private int bloc;
    
    public int getBloc() {
      return bloc;
    }
    
    /**
     * Indicateur de portee globale
     * @return vrai ssi le symbole est déclaré au niveau 0
     */
    public boolean estGlobal(){
        return bloc==0;
    }
    
    /**
     * Adresse relative d'une variable dans la pile d'exécution
     * MAJ dans GenerateurCode.parcours()
     */
    private int adresse;
    
    public int getAdresse() {
      return adresse;
    }
    
    public void setAdresse(int adresse) {
      this.adresse = adresse;
    }

    /**
     * Etiquette associée à une fonction dans le code MAP
     * MAJ dans GenerateurCode.parcours()
     */
    private String etiquette;

    public String getEtiquette() {
      return etiquette;
    }
   
    public void setEtiquette(String etiquette) {
      this.etiquette = etiquette;
    }

    /**
     * Taille occupée en mémoire 
     * MAJ dans GenerateurCode.parcours()
     */
    private int taille;
   
    public int getTaille() {
      return taille;
    }
    
    public void setTaille(int taille) {
      this.taille = taille;
    }

    /**
     * Constructeur d'un symbole lors de sa déclaration. 
     * @param i identificateur du symbole
     * @param b numéro du bloc de la déclaration du symbole
     */
    Symbole(String i, int b) {
        setIdent(i);
        bloc= b;
    }

    /**
     * Chaine pour affichage textuel
     * @return la chaine 
     */
    public String toString() {
        StringBuffer s= new StringBuffer();
        s.append(getIdent());
        s.append('@').append(bloc);
        //if (type !=null) s.append(':').append(type);
        return s.toString();
    }

    /**
     * Chaine pour affichage avec dot
     * @return la chaine
     */
    public String toDot() {
        return toString();
    }

}
