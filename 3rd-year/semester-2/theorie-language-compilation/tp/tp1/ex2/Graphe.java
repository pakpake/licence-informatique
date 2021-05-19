import java.io.*;
import java.util.*;

/* Programme qui lit une suite de ligne sous la forme "a -> b"
 * et qui affiche le graphe correspondant à l'écran 
 * en utilisant dot puis evince via des appels systèmes */

/* Contraintes à respecter : 
 * les fichiers dot et pdf seront créé dans /tmp/exp.dot et /tmp/exp.pdf
 * lecture du clavier par BufferedReader.readLine() 
 * analyse de la ligne avec String.split()
 * l'écriture du fichier d'entrée de dot, avec BufferedWriter.append()
 * le lancement de dot et de evince, par la méthode Runtime.getRuntime().exec()
 * avant de lancer evince, le processus dot doit être terminé, par Process.waitFor() */

public class Graphe {

    public static void main(String[] args) throws Exception {
        try { 
            // LECTURE
            // on lit les lignes une par une, on les fusionne dans "lignes" 
            // avec un séparateur # ensuite on split

            // Déclaration des variables
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            String lignes="", line="";
            String[] splitLines = null;

            // On demande à l'utilisateur si son graphe est orienté ou non
            String graph="digraph G{\n";     // par défaut le graphe sera orienté
            System.out.println("Est-ce un graphe orienté (oui/non) ?");
            if (br.readLine().equals("non")) {
                graph = "graph G{\n";   // le graphe n'est pas orienté
            }

            // boucle de lecture
            System.out.println("Format : a -> b<ENTER> ou a -- b<ENTER>\nAppuyez 2 fois sur la touche entrée pour finir");
            // lecture des lignes sur l'entrée standard
            while (!(line = br.readLine()).trim().equals("")){
                // lecture dans line et on ajoute ce qui a été lu dans lignes
                // avec un séparateur # à chaque fin de lignes
                lignes += line.trim() + "#";
            }
            // découpage des lignes
            splitLines = lignes.split("#");
            br.close();

            // ECRITURE
            File file = new File("/tmp/exp.dot");
            // fasle to overwrite ; true to append
            FileWriter fw = new FileWriter(file,false);

            // on teste si le fichier existe sinon on le créé
            if(!file.exists()){
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(graph);
            // parcours des lignes lues
            for (int i=0 ; i<splitLines.length ; i++) {
                // enregistrement des lignes dans le fichier correspondant
                bw.append("  ");
                bw.append(splitLines[i]);
                bw.append(";\n");
            }
            bw.append("}\n");
            bw.close();

            // EXECUTION des processus
            // execution de dot
            try { 
                Process process = Runtime.getRuntime().exec("dot -Tpdf /tmp/exp.dot -o /tmp/exp.pdf");
                // on attend que le thread se finisse correctement
                process.waitFor();
                // on lit la sortie standard du processus
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line=reader.readLine())!=null) {
                    System.out.println(line);   
                }
            } catch(Exception e) {
                System.out.println(e);
            }

            // execution de evince
            try { 
                Process process = Runtime.getRuntime().exec("evince /tmp/exp.pdf");
                // on attend que le thread se finisse correctement
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line=reader.readLine())!=null) {
                    System.out.println(line);   
                }
            } catch(Exception e) {
                System.out.println(e);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
