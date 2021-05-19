/**
 * Machine à pile MAP : chargeur
 * @author F. Raimbault
 */
package map;

/**
 * Registres internes
 */
public enum Registres {
  
  SP, FP, FP0, GP, PC;
  
  private short value= 0;
  
  short lire() {
    return value;
  }
  
  short ecrire(int val) {
    value= (short) val;
    return value;
  }
  
  short increment(int i) {
    value += i;
    return value;
  }
}
