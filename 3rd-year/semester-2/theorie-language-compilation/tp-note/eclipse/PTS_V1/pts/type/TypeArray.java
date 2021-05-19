/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

public class TypeArray extends TypeConstructor {

  private TypeBasic elt_type;
  private int size;
  
  public TypeArray(TypeBasic t, int n){
    super("[]",n*t.taille);
    elt_type= t;
    size= n;
    name= elt_type+"["+size+"]";
  }
  
  /**
   * 
   * @see pts.type.Type#isArray()
   */
  public boolean isArray(){
    return true;
  }
/**
 * Type des éléments du tableau
 * @return le yype des éléments du tableau
 */
  public TypeBasic getEltType() {
    return elt_type;
  }

  /**
   * 
   * @see pts.type.Type#isFunction()
   */
  public boolean isFunction(){
    return false;
  }

  /**
   * 
   * @see pts.type.Type#match(pts.type.Type)
   */
  public boolean match(Type param_type){
    if (! param_type.isArray()) return false;
    else return this.getSize() == ((TypeArray) param_type).getSize();
  }

  /**
   * La taille du tableau
   * @return le nombre maximal d'éléments
   */
  public int getSize() {
    return size;
  }

}
