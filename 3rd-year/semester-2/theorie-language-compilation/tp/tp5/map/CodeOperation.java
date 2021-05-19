/**
 * Machine à pile MAP : codes opération des instructions
 * @author F. Raimbault
 */
package map;

import java.util.HashMap;


/**
 * Codes opération des instructions de la machine MAP.
 * Utilisés par l'assembleur ASS et le compilateur PTS
 */
public enum CodeOperation { // NE MODIFIER QUE POUR AJOUTER UNE INSTRUCTION
     
    // code op des instructions sans argument
    NOP(false,0),
    ADD(false,1),
    SUB(false,2),
    MUL(false,3),
    DIV(false,4),
    MOD(false,5),
    SND(false,6),
    RCV(false,7),
    LDI(false,8),
    STI(false,9),
    HLT(false,10),
    
    // code op des instructions avec argument
    LDC(true,11),
    INC(true,12),
    DEC(true,13),
    GBL(true,14),
    LDL(true,15),
    STL(true,16),
    LDG(true,17),
    STG(true,18),
    LLA(true,19),
    LGA(true,20),
    JMP(true,21),
    JGZ(true,22),
    JNZ(true,23),
    JSR(true,24),
    RET(true,25); 
    
    /**
     * indicateur de présence d'un argument dans l'instruction
     */
    private boolean avec_argument;
    
    /**
     * valeur numérique du codeop
     */
    private short codeop;
    
    /**
     * Table permettant de retrouver le CodeOperation correspondant à une valeur 
     */
    private static HashMap<Short,CodeOperation> code_map= new HashMap<Short, CodeOperation>(30);
    static{
      for (CodeOperation code:CodeOperation.values()){
        code_map.put(code.codeop, code);
      }
    }
    /**
     * Définition d'un code opération
     * @param b indicateur d'une instruction avec argument
     * @param v code numérique du code
     */
    CodeOperation(boolean b,int v){
      avec_argument= b;
      codeop= (short) v;
    }
   
    /**
     *  CodeOperation associé à une valeur numérique
     * @param n valeur numérique
     * @return le codeop n
     */
    public static CodeOperation decode(short n) {
      return code_map.get(n);
    }
    
    /**
     * Valeur numérique associé à un CodeOperation
     * @return la valeur associée
     */
    public int encode(){
      return this.codeop;
    }
    
    /**
     * Indicateur de présence d'un argument suivant ce codeOp
     * @return vrai ssi un argument suit ce codeop en mémoire
     */
    public boolean argumentSuit(){
      return this.avec_argument;
    }
    
    
}
