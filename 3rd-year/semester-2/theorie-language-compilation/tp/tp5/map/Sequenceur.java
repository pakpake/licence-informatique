/**
 * Machine à pile MAP : séquenceur
 * @author F. Raimbault
 */
package map;

import static map.Registres.PC;

/**
 * Séquenceur de la machine à pile MAP
 */
public final class Sequenceur { // NE PAS MODIFIER

    /**
     * status d'exécution pouvant prendre trois valeurs  
     */
    private static Status PS;

    /**
     * valeurs possibles du status
     */
    private enum Status{ 
      CONTINUE,// boucle
      TERMINE,//arret normal
      STOP//arret sur erreur
    }

    /**
     * Boucle d'exécution du séquenceur. 
     * Termine quand le registre status passe à STOP ou à TERMINE
     */
    public static void executer() {
        if (Map.trace_mode) EntreesSorties.afficheInfo("debut de l'execution");
        PC.ecrire(0); // adresse de la premiere instruction
        PS = Status.CONTINUE;
        Instruction ins;
        do {
            ins = Memoire.lireInstruction(PC.lire());
            if (PS == Status.CONTINUE) {
                if (Map.trace_mode) EntreesSorties.afficheInfo("execution de l'instruction " + PC + ":" + ins);
                PC.ecrire(ins.executer());
                if (Map.trace_mode) EntreesSorties.afficheInfo("etat de la memoire :\n" + Memoire.dumpFrame());
           }
        } while (PS == Status.CONTINUE);
        if (Map.trace_mode) EntreesSorties.afficheInfo("fin de l'execution avec le code " + PS);
    }

    /**
     * Stoppe l'exécution du séquenceur en mettant le registre status à STOP
     */
    public static void stop() {
        PS = Status.STOP;
    }

    /**
     * Stoppe l'exécution du séquenceur en mettant le registre status à TERMINE
     */
    public static void termine() {
        PS = Status.TERMINE;
    }
}