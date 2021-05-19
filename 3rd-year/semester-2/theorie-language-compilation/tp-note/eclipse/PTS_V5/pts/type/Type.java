/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */

package pts.type;

public abstract class Type{
  
  public final static Type INTEGER= new TypeInt();
  public final static Type BOOLEAN= new TypeBool();
  public final static Type VOID= new TypeVoid();
  public static final Type PROGRAM = new TypeProgram();

  /** taille occupé par en mémoire */
  public int taille;
 
  /**
   * nom du type (pour affichage)
   */
  protected String name;
  
  /**
   * Type du langage PTS
   * @param s nom du type tel qu'il s'affichera dans l'AST
   * @param t taille mémoire d'un objet de ce type
   */
  public Type(String s,int t){
    name= s;
    taille= t;
  }
  
  /**
   * Affichage textuel dans l'AST du type
   * @see java.lang.Object#toString()
   */
  public String toString(){
    return name;
  }
  /**
   * Prédicat de type Scalaire
   * @return vrai ssi c'est un type scalaire
   */
  public abstract boolean isBasic();
  /**
   * Prédicat de type Tableau
   * @return vrai ssi c'est un tableau
   */
  public abstract boolean isArray();
  /**
   * Prédicat de type void
   * @return vrai ssi c'est un type void
   */
  public abstract boolean isVoid();
  /**
   * Prédicat de type void
   * @return vrai ssi c'est un type void
   */
  public abstract boolean isInteger();
  /**
   * Prédicat de type entier
   * @return vrai ssi c'est un type entier
   */
  public abstract boolean isBoolean();
  /**
   * Prédicat de type booléen
   * @return vrai ssi c'est un type booléen
   */
  public abstract boolean isFunction();
  /**
   * Conformité du type avec un autre
   * @param param_type type auquel se conformer
   * @return vrai ssi le second type est conforme au premier
   */
  public abstract boolean match(Type param_type);

}
