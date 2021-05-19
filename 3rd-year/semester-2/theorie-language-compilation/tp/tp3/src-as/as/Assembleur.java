/* A COMPLETER */

package as;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Assembleur de la machine MAP à une seule passe 
 */
public class Assembleur {

  /**
   * Affichage d'une erreur de syntaxe détectée dans le fichier source
   * @param scan l'analyseur lexical
   * @param msg le message à afficher
   */
    private void erreurDeSyntaxe(Scanner scan, String msg) {

        Erreur.syntaxe("ligne " + (scan.getLineNo() + 1) + " : " + msg);
    }

    /**
     * Lecture de l'unité lexicale suivante
     * @param scan l'analyseur lexical
     * @return l'unité lexicale suivante (et son lexème si présent)
     */
    private UniteLexicale lireUniteLexicaleSuivante(Scanner scan) {

        try {
            return scan.yylex();
        } catch (IOException e) {
            erreurDeSyntaxe(scan, "problème à la lecture (!?)");
        }
        return null;
    }

    /**
     * lecture d'un argument étiquette
     * @param scan l'analyseur lexical
     * @return le label de l'étiquette
     */
    private String lireEtiquette(Scanner scan) {
      
        UniteLexicale tok = lireUniteLexicaleSuivante(scan);
        String id = null;
        if (tok.type == TypeUniteLexicale.LABEL){
            id = tok.value;
        }
        else{
            erreurDeSyntaxe(scan, "identificateur d'étiquette manquant");
        }
        return id;
    }

    /**
     * lecture d'un argument valeur entière
     * @param scan l'analyseur lexical
     * @return la valeur de l'entier
     */
    private String lireValeur(Scanner scan) {

        UniteLexicale tok = lireUniteLexicaleSuivante(scan);
        String valeur = null;
        if (tok.type == TypeUniteLexicale.VALUE){
            valeur = tok.value;
        }else{
            erreurDeSyntaxe(scan, "valeur numérique attendue");
        }
        return valeur;
    }

    /**
     * lecture d'un argument valeur entière positive
     * @param scan l'analyseur lexical
     * @return la valeur de l'entier positif
     */
    private String lireValeurPositive(Scanner scan) {

        String valeur = lireValeur(scan);
        if (Short.parseShort(valeur) < 0)
            erreurDeSyntaxe(scan, "valeur numérique positive attendue");
        return valeur;
    }

    /**
     * Assemblage du programme lu par l'analyseur lexical
     * @param scan analyseur lexical
     * @return le programme MAP
     */
    Programme assemble(Scanner scan) {
        Programme prog = new Programme(); // programme à remplir
        Instruction ins = null; // instruction courante 
        String etiquette = null; // label de la définition d'une étiquette pour l'instruction courante
        String reference = null; // label de la référence à une étiquette pour l'instruction courante
        String valeur = null; // valeur de l'argument pour l'instruction courante
        UniteLexicale token = lireUniteLexicaleSuivante(scan); // unité lexicale courante
        do{
            switch (token.type){ // on distingue les différents types d'unités lexicale
            case LABEL: // ligne commençant par la définition d'une étiquette
                etiquette = token.value;
                // on vérifie que l'étiquette n'est pas déja présente 
                if (prog.table_etiquettes.contient(etiquette)) {
                    erreurDeSyntaxe(scan, "étiquette " + etiquette + "deja définie");
                } else {
                    // on attend de connaitre l'instruction associee a
                    // cette etiquette pour l'inserer dans la table
                }
                break;
            case MAP : // ligne commençant par le codeop d'une instruction
              switch(token.codeop){
              case LDC:
              case LDL:
              case STL:
              case LGA:
              case LLA:
              case LDG:
              case STG:{ // instruction avec un argument entier
                // A COMPLETER
                valeur = lireValeur(scan);
                ins = new Instruction(etiquette, token.codeop, valeur, null);
                break;
              }
              case GBL:{ // il faut vérifier que c'est bien la première instruction du programme
                   // A COMPLETER
                   if(! prog.liste_instructions.isEmpty()) {
                       erreurDeSyntaxe(scan, "GBL: n'est pas la première instruction !");
                   } else {
                       valeur = lireValeur(scan);
                       ins = new Instruction(etiquette, token.codeop, valeur, null);
                   }
                   break;
              }
              case INC:
              case DEC:
              case RET:{ // instruction avec un argument entier positif
                // A COMPLETER
                valeur = lireValeurPositive(scan);
                ins = new Instruction(etiquette, token.codeop, valeur, null);
                break;
              }
              case ADD:
              case SUB:
              case MUL:
              case DIV:
              case MOD:
              case SND:
              case RCV:
              case LDI:
              case STI:
              case HLT:
              case NOP:{ // instruction sans argument
                // A COMPLETER
                ins = new Instruction(null, token.codeop, null, null);
                break;
              }
              case JMP:
              case JNZ:
              case JGZ:
              case JSR:{// instruction avec un argument référence
                // A COMPLETER
                reference = lireEtiquette(scan);
                ins = new Instruction(etiquette, token.codeop, null, reference);
                break;
               }
              }// fin du switch sur les codeop
              // on ajoute l'instruction courante au programme
              // A COMPLETER
              prog.liste_instructions.add(ins);
              // si une nouvelle étiquette a été définie on l'ajoute à la table des étiquettes
              // A COMPLETER
              if (etiquette != null) {
                  prog.table_etiquettes.definir(etiquette, ins);
              }
              ins = null;
              etiquette=null;
              break;
            case EOF: // fin du programme
              break;
            case ERROR: // ligne contenant une erreur lexicale
              erreurDeSyntaxe(scan, "erreur de syntaxe ligne "+scan.getLineNo());
              break;
            case VALUE: // normalement impossible (ajouté pour complétude Java du switch)
              break;
            }
            // lecture de l'unité lexicale suivante (début de ligne suivant)
            token = lireUniteLexicaleSuivante(scan);
        }while (token.type != TypeUniteLexicale.EOF);
        // on verifie que toutes les references en avant ont bien été résolues
        // sinon on affiche toutes celles qui ne le sont pas
        // A COMPLETER
        if (!prog.table_etiquettes.tousDefinis()) {
            prog.table_etiquettes.enumerationIndefinis();
        }
        return prog;
    }

    /**
     * Lanceur de l'assembleur à partir des paramètres lus sur la ligne de commande
     * arguments attendus :
     * arg[0] : nom du fichier source
     * arg[1] : nom du fichier cible 
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
      
      Erreur.message("Démarrage de AS-MAP");
        switch (args.length) {
        case 0:
            Erreur.parametre("manque le nom d'un fichier de programme assembleur");
            break;
        case 1:
            Erreur.parametre("manque le nom du fichier de code binaire résultat");
            break;
        case 2:
            Scanner scan = null;
            try {
                //scan = new Scanner(new FileInputStream(args[0])); /* constructeur non généré par JFlex */
              scan= new Scanner(new FileReader(args[0]));
            } catch (FileNotFoundException e) {
                Erreur.parametre("impossible de lire le fichier de code assembleur " + args[0]);
            }
            Assembleur as= new Assembleur();
            Programme prog = as.assemble(scan);
            prog.ecrireFichier(args[1]);
            break;
        default:
            Erreur.parametre("trop de parametres !");
        }
    }
}

