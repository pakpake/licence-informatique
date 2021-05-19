/**
 * Machine à pile MAP : chargeur
 * @author F. Raimbault
 */
package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * Chargeur de code de la machine MAP
 */
public final class Chargeur { // NE PAS MODIFIER
  
     /**
     * Lit un fichier de code et charge la mémoire d'instructions
     * @param f le nom du fichier de code
     */
    public static void lire(String f) {

        if (Map.trace_mode) EntreesSorties.afficheInfo("debut du chargement du fichier " + f);
        BufferedReader fich = null;
        try {
            fich = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            Erreur.chargement("impossible d'ouvrir le fichier " + f);
        }
        StreamTokenizer lecteur = new StreamTokenizer(fich);
        lecteur.wordChars(33, 255);
        lecteur.commentChar(';'); // ce qui suit un ";" est un commentaire
        lecteur.eolIsSignificant(true);
        try {  
            int tok = lecteur.nextToken();// lecture de la première instruction
            while (tok != StreamTokenizer.TT_EOF) {
                if (tok == StreamTokenizer.TT_NUMBER) {
                    // lecture d'un codeop ou d'un argument
                    short codeop_ou_arg = (short) lecteur.nval;
                    Memoire.ajoutInstruction(codeop_ou_arg);
                 } else if (tok == StreamTokenizer.TT_EOL) {
                    // fin d'une instruction
                }
                // lecture des instructions suivantes
                tok = lecteur.nextToken();
            }
            if (Map.trace_mode) EntreesSorties.afficheInfo("memoire apres chargement:\n"+Memoire.dump()); // pour debug
        } catch (IOException e1) {
            Erreur.chargement("erreur de syntaxe ligne " + lecteur.lineno());
        }
        if (Map.trace_mode) EntreesSorties.afficheInfo("fin du chargement du fichier " + f);
    }
}