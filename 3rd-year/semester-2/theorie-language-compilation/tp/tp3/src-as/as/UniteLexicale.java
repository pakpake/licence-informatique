/**
 * NE PAS MODIFIER
 */
package as;

import map.CodeOperation;

/**
 * Unités lexicales du langage d'assemblage de la MAP
 */
public class UniteLexicale{

    /**
     * type de l'unité lexicale
     */ 
    public TypeUniteLexicale type;

    /**
     *  code opération associé dans le cas d'une instruction Map
     */
    public CodeOperation codeop;
    
    /**
     * lexème de l'unité lexicale dans le cas d'une étiquette ou d'une valeur
     */
    public String value;
 
    /**
     * Constructeur d'une unité lexicale pour autre chose qu'une instruction Map
     * @param t type de l'unité lexicale
     * @param v lexème associé
     */
    public UniteLexicale(TypeUniteLexicale t, String v) {
        type = t;
        value = v;
    }

    /**
     * Constructeur d'une unité lexicale pour une instruction Map
     * @param i code opération Map 
     */
    public UniteLexicale(CodeOperation i) {
        type= TypeUniteLexicale.MAP;
        codeop = i;
        value = null;
    }

    /**
     * Chaine pour affichage 
     */
    public String toString() {
        StringBuffer s= new StringBuffer("#");
        switch(type){
            case MAP:
              s.append(codeop.toString());
              break;
        	case ERROR:
        	case LABEL:	
        	case VALUE:	
           	case EOF:	
           	   s.append(type.toString());
               if (value != null)
                 s.append("(").append(value).append(")");
         	   break;
        }
        return s.toString();
    }

}

