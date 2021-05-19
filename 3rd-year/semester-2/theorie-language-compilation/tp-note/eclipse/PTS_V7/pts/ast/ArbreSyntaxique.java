/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.ast;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;

import pts.erreur.Erreur;
import pts.erreur.Messages;

/**
 * Arbre syntaxique abstrait produit par l'analyseur syntaxique. 
 * Tous les noeuds ont un nombre de fils CONSTANT (donc éventuellement à null) 
 * sauf s'il s'agit du dernier fils qui peut etre omis dans ce cas.
 */
public class ArbreSyntaxique {

  // Commandes pour l'utilisation de dot et d'un viewer PDF sous linux (suppose l'installation du package graphviz et okular)
  public static String LINUX_DOT_CMD="dot -Tpdf -o %s %s";
  public static String LINUX_VIEWPDF_CMD="nohup okular %s";
  //Commandes pour l'utilisation de dot et d'un viewer PDF sous windows (suppose l'installation du package graphviz et acrobat)
  public static String WIN_DOT_CMD="C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe -Tpdf %s > %s";
  public static String WIN_VIEWPDF_CMD="C:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe %s";
       
    /** racine de l'AST */
    public Noeud racine;
 
    /**
     * Construction à partir de la racine 
     * @param n
     */
    public ArbreSyntaxique(Noeud n) {
        racine = n;
     }

    /**
     * chaine pour affichage sous forme textuelle
     */
    public String toString() {
        return racine.dump("");
    }
    
    /**
     * Affichage d'un PDF apres création et exécution d'un fichier DOT 
     * @param rep répertoire ou créer les fichiers .dot et .ps 
     * @param fich nom du fichier .dot 
     */
    public void toDot(String rep, String fich) {
        FileWriter fichier = null;
        String sep= FileSystems.getDefault().getSeparator();
        String nomfich_dot= rep + sep + fich + ".dot";
        String nomfich_pdf= rep + sep + fich + ".pdf";
        String os_name = System.getProperty("os.name"); 
        
        try {
            fichier = new FileWriter(nomfich_dot);
        } catch (IOException e) {
            Erreur.systeme(Messages.DOT_OPEN,nomfich_dot);
        }
        try {
            fichier.write("digraph "+fich+" {\n");
            fichier.write("size=\"20,20\"\n");
            fichier.write("node [shape=plaintext]\n");
            fichier.write("edge [dir=none]\n");
            fichier.write(racine.toDot());
            fichier.write("}\n");
            fichier.close();
        } catch (IOException e) {
            Erreur.systeme(Messages.DOT_WRITE,nomfich_dot);
        }
        String dot_cmd;
        switch(os_name) {
          case "Linux":
              dot_cmd= String.format(LINUX_DOT_CMD,nomfich_pdf,nomfich_dot);
              break;
          case "Windows":
              dot_cmd= String.format(WIN_DOT_CMD,nomfich_dot,nomfich_pdf);
              break;
          default:
            return;
        }
        try {
          Process dot_process=  Runtime.getRuntime().exec(dot_cmd);
          dot_process.waitFor();
          if (dot_process.exitValue()!=0) {
            Erreur.systeme(Messages.DOT_EXEC,nomfich_dot);
          }
        } catch (Exception e) {
		    Erreur.systeme(Messages.DOT_EXEC,nomfich_dot);
		}
        String viewpdf_cmd;
        switch(os_name) {
          case "Linux":
              viewpdf_cmd= String.format(LINUX_VIEWPDF_CMD,nomfich_pdf);
              break;
          case "Windows":
              viewpdf_cmd= String.format(WIN_VIEWPDF_CMD,nomfich_pdf);
              break;
          default:
            return;
        }
        try {
            Runtime.getRuntime().exec(viewpdf_cmd).waitFor();
		} catch (Exception e) {
		    Erreur.systeme(Messages.OKULAR_ERROR,nomfich_pdf);
		}
    }
}