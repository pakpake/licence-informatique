/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

/**
 * @author raimbaul
 *
 */
public class TypeVoid extends Type {

  TypeVoid(){
    super("void",0);
  }
  /**
   * 
   * @see pts.type.Type#isBasic()
   */
  public boolean isBasic(){
    return false;
  }
  /**
   * 
   * @see pts.type.Type#isVoid()
   */
  public boolean isVoid(){
    return true;
  }

  public boolean isArray(){
    return false;
  }
  /**
   * 
   * @see pts.type.Type#isInteger()
   */
  public boolean isInteger(){
    return false;
  }
  /**
   * 
   * @see pts.type.Type#isBoolean()
   */
  public boolean isBoolean(){
    return false;
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
    return false;
  }

}
