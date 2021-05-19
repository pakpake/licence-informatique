/**
 * Machine à pile MAP : instruction de la MAP
 * @author F. Raimbault
 * A COMPLETER
 */
package map;

import static map.Registres.*;

/**
 * Instructions de la machine MAP
 */
public class Instruction { // DONE : A COMPLETER (méthode executer() seulement)

    /**
     * adresse de l'instruction 
     */
    private short adresse;

    /**
     * taille de l'instruction 
     */
    private int size;
    
    /**
     * code de l'opération 
     */
    private CodeOperation code_op;

    /**
     * argument de l'opération si présent
     */
    private short argument;

    /**
     * Nouvelle instruction sans argument. 
     * @param ad adresse de l'instruction
     * @param code_op code opération de l'instruction
     */
    public Instruction(short ad, CodeOperation code_op) {
        this.code_op = code_op;
        this.adresse= ad;
        this.size= 1; // pour le code op
    }

    /**
     * Nouvelle instruction avec argument. 
     * @param ad adresse de l'instruction
     * @param code_op code opération de l'instruction
     * @param arg argument de l'opération
     */
    public Instruction(short ad, CodeOperation code_op, short arg) {
        this(ad,code_op);
        this.size += 1; // pour l'argument
        this.argument= arg;
    }

    /**
     * Effet de l'exécution de l'instruction sur la MAP 
     * @return l'adresse de l'instruction suivante à exécuter
     */
    public short executer() { // DONE : A COMPLETER
        int adresse_instruction_suivante= adresse+size; // sauf pour les branchements
        switch (code_op) {
        case INC: // NE PAS MODIFIER
          Memoire.reserverVariableLocale(argument);
          break;
        case LDC: // on empile une constante
          Memoire.empiler(argument);
          break;
        case DEC: // on libère des emplacements
          Memoire.libererVariableLocale(argument);
          break;
        case GBL: // on réserve des emplacements, initialisé à zéro sur la pile
          Memoire.reserverVariableGlobale(argument);
          break;
        case LDL: // on empile la variable locale
          Memoire.empiler(Memoire.lireVariableLocale(argument));
          break;
        case STL: // On dépile dans la variable locale
            Memoire.ecrireVariableLocale(argument,Memoire.depiler());
          break;
        case LDG: // On empile la variable globale
          Memoire.empiler(Memoire.lireVariableGlobale(argument));
          break;
        case STG: // On dépile dans la variable globale
          Memoire.ecrireVariableGlobale(argument,Memoire.depiler());
          break;
        case LDI:{ // On empile le contenu d'une adresse
            Memoire.empiler(Memoire.lireEmplacement(Memoire.depiler()));
            break;
        }
        case STI:{ // On dépile à une adresse
            int addr = Memoire.depiler();
            int val = Memoire.depiler();
            Memoire.ecrireEmplacement(addr, val);
            break;
        }
        case LLA:{ // On empile l'adresse de la variable locale 
            // Memoire.empiler(Registres.FP.lire()+argument);
            Memoire.empiler(FP.lire()+argument);
            break;	
        }
        case LGA:{ // On empile l'adresse de la variable globale
            // Memoire.empiler(Registres.GP.lire()+argument);
            Memoire.empiler(GP.lire()+argument);
            break;
        }
        case ADD: { // on dépile 2 éléments, on les additionne et on empile le résultat
            int m1 = Memoire.depiler();
            int m2 = Memoire.depiler();
            // System.out.println("m1="+m1+"\nm2="+m2);
            Memoire.empiler(m1+m2);
            // Memoire.empiler(Memoire.depiler()+Memoire.depiler());
            break;
        }
        case SUB: { // on dépile 2 éléments, on les soustrait et on empile le résultat
            int m1 = Memoire.depiler();
            int m2 = Memoire.depiler();
            Memoire.empiler(m2-m1);
            // Memoire.empiler(Memoire.depiler()-Memoire.depiler());
            break;
        }
        case MUL: { // on dépile 2 éléments, on les multiplie et on empile le résultat
            int m1 = Memoire.depiler();
            int m2 = Memoire.depiler();
            Memoire.empiler(m1*m2);
            // Memoire.empiler(Memoire.depiler()*Memoire.depiler());
            break;
        }
        case DIV: { // on dépile un 1er élément, on vérifie qu'il est non nul, on divise 
                    // le second élément dépilé, et on empile le résultat
            short diviseur = Memoire.depiler();
            if (diviseur == 0)
                Erreur.execution("Erreur DIV : division par 0 impossible !");
            Memoire.empiler(Memoire.depiler()/diviseur);
            break;
        }
        case MOD: { // on calcule le reste de la division entière
            short modulo = Memoire.depiler();
            if (modulo == 0) {
                Erreur.execution("Erreur MOD : calcul modulo 0 impossible !");
            }
            Memoire.empiler(Memoire.depiler() % modulo);
            break;
        }
         case RET: { // Retour de procédure et restauration de FP
            Memoire.restaureFP();
            adresse_instruction_suivante = Memoire.depiler();
            Memoire.libererVariableLocale(argument);
            break;
        }
        case JMP: // saut inconditionnel
          adresse_instruction_suivante = argument;
          break;
        case JNZ: { // saut si non nul
            if (Memoire.depiler() != 0) {
                adresse_instruction_suivante = argument;
            }
            break;
        }
        case JGZ: { // saut si positif ou nul
            if (Memoire.depiler() >= 0) {
                adresse_instruction_suivante = argument;
            }
            break;
        }
        case JSR: { // appel de porcédure et sauvegarde de FP
            Memoire.empiler(adresse_instruction_suivante);
            Memoire.sauveFP();
            adresse_instruction_suivante = argument;
            break;
        }
        case NOP: // NE PAS MODIFIER
            break;
        case SND: { // affiche l'entier pile SP-1, après l'avoir dépilé
            EntreesSorties.ecrireEntier(Memoire.depiler());
            break;
        }
        case RCV: { // entier lu au clavier et empilé
            Memoire.empiler(EntreesSorties.lireEntier());
            break;
        }
        case HLT: // NE PAS MODIFIER
            Sequenceur.termine();
            break;
        }
        return (short) adresse_instruction_suivante;
    }

    /**
     * Représentation d'une instruction avec son argument (si présent)
     * @return une chaine qui représente le contenu de l'instruction
     */
    public String toString() {
        StringBuffer s = new StringBuffer(code_op.toString());
        if (code_op.argumentSuit())
           s.append(' ').append(argument);
        return s.toString();
    }
}
