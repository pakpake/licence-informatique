/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.type;

import java.util.List;

/**
 * Type d'une fonction ou procédure
 */
public class TypeFunction extends TypeConstructor {

  Type return_type; // TypeVoid ou TypeBasic seulement
  List<TypeBasic> param_type_list; 
  
  public TypeFunction(List<TypeBasic> p,Type r){ 
    super("()",0);
    return_type= r;
    name= "(";
    param_type_list= p; 
    for (Type t:p){ 
      name= name+t+",";
    }
    name= name+"):"+return_type;
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
    return true;
  }

  /**
   * Liste des types des paramètres
   * @return le type des paramètres
   */
  public List<TypeBasic> getParamTypeList() { 
    return param_type_list;
  }

  /**
   * 
   * @see pts.type.Type#match(pts.type.Type)
   */
  public boolean match(Type param_type){
    return false;
  }

  /**
   * Type de retour de la fonction
   * @return le type de retour
   */
  public Type getReturnType() {
    return return_type;
  }

}
