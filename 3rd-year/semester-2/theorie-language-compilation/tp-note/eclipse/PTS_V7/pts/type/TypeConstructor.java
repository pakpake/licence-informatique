/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

public abstract class TypeConstructor extends Type {
 
  TypeConstructor(String s,int t){
    super(s,t);
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
  
}
