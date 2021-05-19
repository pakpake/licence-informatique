/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

/**
 * @author raimbaul
 *
 */
public class TypeBool extends TypeBasic {

  TypeBool(){
    super("bool");
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
    return true;
  }
  
  /**
   * 
   * @see pts.type.Type#match(pts.type.Type)
   */
  public boolean match(Type param_type){
    return param_type.isBoolean();
  }

}
