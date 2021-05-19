/**
 * Compilateur PTS Version 0
 * F. Raimbault
 */
package pts.ast;

import java.util.ArrayList;

import pts.symb.Symbole;
import pts.type.Type;

/**
 * Noeud de l'arbre syntaxique abstrait {@link ArbreSyntaxique}.
 * Un noeud possède une étiquette  {@link EtiquetteNoeud} qui identifie son contenu.
 * Un noeud possède un nombre quelconque de fils (qui sont aussi des noeuds de l'AST.
 * Un noeud possède des attributs obligatoires : son père, le numéro de ligne dans le source et son type.
 * Un noeud possède des attributs facultatifs : identificateur (ou valeur), symbole.
 */
public class Noeud {

    /** numérotation automatique des noeuds pour affichage par dot */
    private static int prochain_numero = 0;
    private int numero;

    /** structure syntaxique associée */
    public EtiquetteNoeud etiquette;
 
    /** identificateur (ou valeur) associé ou null */
    public String identificateur; // renseigné dans AnalyseurSyntaxique

    /** symbole associé ou null */
    public Symbole symbole; // renseigné dans AnalyseurIdentificateurs
    
    /** type du sous-arbre ou de l'identificateur */
    public Type type; // renseigné dans AnalyseurTypes
    
    /** père ou null pour la racine */
    public Noeud pere;

    /** liste des fils (peut etre vide mais non null) */
    public Noeud[] fils;

    /** ligne de source ayant engendré ce noeud */
    public int ligne;
  	 
    /**
     * Noeud sans fils 
     * @param n numéro de ligne dans le source
     * @param e etiquette du noeud
     */
    public Noeud(int n, EtiquetteNoeud e) {
        this.ligne= n;
        this.numero = prochain_numero++;
        etiquette = e;
        type= null;
        symbole= null;
        identificateur= null;
    }

    /**
     * Noeud sans fils avec un identificateur
     * @param n numéro de ligne dans le source
     * @param e étiquette du noeud
     * @param ident identificateur (ou valeur) associé
     */
    public Noeud(int n, EtiquetteNoeud e, String ident) {
        this(n,e);
        identificateur = ident;
        ligne= n;
    }

    /**
     * Noeud avec un tableau de fils 
     * @param n numéro de ligne dans le source
     * @param e étiquette du noeud
     * @param f tableau de fils
     */
    public Noeud(int n, EtiquetteNoeud e, Noeud[] f) {
        this(n,e);
        fils = f;
        for (int i = 0; i < fils.length; i++) {
            if (fils[i] != null) {
                fils[i].pere = this;
            }
        }
    }

    /**
     * Noeud avec un seul fils 
     * @param n numéro de ligne dans le source
     * @param e étiquette du noeud
     * @param f fils
     */
    public Noeud(int n, EtiquetteNoeud e, Noeud f) {
        this(n, e, new Noeud[] { f });
    }

    /**
     * Noeud avec une liste de fils
     * @param n numéro de ligne dans le source
     * @param e étiquette du noeud
     * @param l liste de fils
     */
    public Noeud(int n, EtiquetteNoeud e, ArrayList<Noeud> l) {
        this(n,e, l.toArray(new Noeud[l.size()]));
    }

    /**
     * Chaine pour affichage
     * @return la chaine
     */
    public String toString() {
        StringBuffer s= new StringBuffer(etiquette.getLabel());
        if (identificateur !=null)
            s.append(":").append(identificateur);
        if (type != null)
            s.append(" (").append(type).append(")");
        return s.toString();
    }

    /**
     * Chaine pour affichage sous forme textuelle 
     * @param prefix
     * @return la chaine
     */
    public String toString(String prefix) {
        return prefix + toString();
    }

    /**
     * Chaine pour affichage du sous-arbre sous forme textuelle 
     * @param prefix indentation 
     * @return la chaine
     */
    public String dump(String prefix) {
        StringBuffer s = new StringBuffer(toString(prefix));
        s.append("\n");
        if (fils != null) {
            for (int i = 0; i < fils.length; ++i) {
                Noeud n = fils[i];
                if (n != null) {
                    s.append(n.dump(prefix + "| "));
                }
            }
        }
        return s.toString();
    }

    /**
     * Chaine pour affichage par dot 
     * @return la chaine
     */
    public String toDot() {
        StringBuffer s = new StringBuffer("n").append(numero);
        s.append(" [label=\"").append(etiquette.getLabel());
        if (symbole !=null){
          s.append(":").append(symbole);
        }else if (identificateur !=null){
            s.append(":").append(identificateur);
        }
        if (type != null){
          s.append("\\n< ").append(type).append(" >");
        }
        s.append("\"]\n");
        if (fils != null) {
          for (int i = 0; i < fils.length; ++i) {
            Noeud c = fils[i];
            if (c != null) {
              s.append(c.toDot()).append("\n");
              s.append("n").append(numero);
              s.append(" -> ").append("n").append(c.numero).append(";\n");
            }
          }
        }
        return s.toString();
      }

}
