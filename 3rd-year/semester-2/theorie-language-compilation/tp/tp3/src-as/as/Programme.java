/**
 * NE PAS MODIFIER
 */
package as;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * programme MAP généré par l'assembleur
 */

public class Programme {

    /**
     * Liste des instructions (de type Instruction) 
     */
    public List<Instruction> liste_instructions;

    /**
     * Table des étiquettes du programme
     */
    public TableEtiquettes table_etiquettes;

    /**
     * Constructeur d'un programme vide
     */	
    public Programme() {
      Instruction.reset();
      liste_instructions = new LinkedList<Instruction>();
      table_etiquettes = new TableEtiquettes();
    }
    
    /**
     * Ecriture du programme dans un fichier
     * @param nomfich nom du fichier
     */
    public void ecrireFichier(String nomfich) {
      PrintWriter fichier = null;
          try {
              fichier = new PrintWriter(new FileOutputStream(nomfich),true);
         } catch (IOException e) {
            Erreur.parametre("impossible d'ouvrir le fichier " + nomfich + "en écriture");
        }
        for(Instruction ins : liste_instructions){
          //fichier.printf("%s\n",ins.toString());
            try {
                fichier.printf("%3s ",ins.getCodeOp().encode());
                if (ins.getArgument() != null)
                    fichier.printf("%4d",Short.parseShort(ins.getArgument()));
                else if (ins.getReference() != null){
                    Instruction ref_ins= table_etiquettes.symboleDe(ins.getReference());
                    fichier.printf("%4d",ref_ins.getAdresse());
                }else
                    fichier.printf("%4s"," ");
            } catch (NumberFormatException e1) {
                Erreur.interne(e1.getMessage());
            }
            fichier.printf("; %s\n",ins.toString());
        }
    }
}