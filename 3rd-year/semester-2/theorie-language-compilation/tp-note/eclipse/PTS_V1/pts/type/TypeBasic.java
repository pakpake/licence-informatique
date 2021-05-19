/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

public abstract class TypeBasic extends Type {

  TypeBasic(String s){
    super(s,1);
  }
  
  /**
   * 
   * @see pts.type.Type#isBasic()
   */
  public boolean isBasic(){
    return true;
  }

  /**
   * 
   * @see pts.type.Type#isVoid()
   */
  public boolean isVoid(){
    return false;
  }

  /**
   * 
   * @see pts.type.Type#isArray()
   */
  public boolean isArray(){
    return false;
  }

  /**
   * 
   * @see pts.type.Type#isFunction()
   */
  public boolean isFunction(){
    return false;
  }
  
}
