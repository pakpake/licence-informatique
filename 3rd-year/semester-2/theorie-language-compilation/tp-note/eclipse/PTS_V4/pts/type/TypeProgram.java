/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

/**
 * @author raimbaul
 *
 */
public class TypeProgram extends TypeConstructor {

  TypeProgram(){
    super("prog",0);
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
  /**
   * 
   * @see pts.type.Type#match(pts.type.Type)
   */
  public boolean match(Type param_type){
    return false;
  }

}
