package afd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.HashMap;

/**
 * Interpréteur d'expressions arithmétiques 
 * en Notation Polonaise Inversée
 * @author pakpak
 */
public class NPI {

    /**
     * Construction de l'automate pour la NPI
     * @return l'automate créé et complété
     */
    static AFD build_AFD(){
        int n = 11;  // nombre d'etats
        AFD afd=null;
        afd = new AFD(n); // creation de l'afd vide avec 9 etats
        try {
            afd.setInitial(0); // etat initial
            afd.setFinal(8);  // etat final
            // transitions entre 0 et 0
            afd.addTransition(0,' ',0,0);
            afd.addTransition(0,'\n',0,8);
            // transitions entre 0 et 1
            for(int i=0;i<=9;i++) afd.addTransition(0,(char)(i+'0'),1,1);
            /* On autorise un nombre à commencer par . ; par exemple : .4 = 0.4
             * Par conséquent . tout seul = 0.0 */
            // transitions entre 0 et 2
            afd.addTransition(0,'.',2,0);
            // transitions entre 1 et 0
            afd.addTransition(1,' ',0,6);
            afd.addTransition(1,'\n',0,7);
            // transitions entre 1 et 1
            for(int i=0;i<=9;i++) afd.addTransition(1,(char)(i+'0'),1,2);
            // transitions entre 1 et 2
            afd.addTransition(1,'.',2,0);
            /* ON autorise un espace où \n après le . ; par exemple 1. = 1.0
             * Par conséquent . tout seul = 0.0 */
            // transitions entre 2 et 0
            afd.addTransition(2,' ',0,6);
            afd.addTransition(2,'\n',0,7);
            // transitions entre 2 et 3
            for(int i=0;i<=9;i++) afd.addTransition(2,(char)(i+'0'),3,3);
            // transitions entre 3 et 3
            for(int i=0;i<=9;i++) afd.addTransition(3,(char)(i+'0'),3,4);
            // transitions entre 3 et 0
            afd.addTransition(3,' ',0,6);
            afd.addTransition(3,'\n',0,7);
            // transitions entre 0 et 4
            afd.addTransition(0,'-',4,0);
            // transitions entre 4 et 1
            for(int i=0;i<=9;i++) afd.addTransition(4,(char)(i+'0'),1,5);
            // transitions entre 4 et 0
            afd.addTransition(4,' ',0,9);
            afd.addTransition(4,'\n',0,10);
            // transitions entre 0 et 5
            afd.addTransition(0,'+',5,0);
            // transitions entre 5 et 0
            afd.addTransition(5,' ',0,11);
            afd.addTransition(5,'\n',0,12);
            // transitions entre 0 et 6
            afd.addTransition(0,'*',6,0);
            // transitions entre 6 et 0
            afd.addTransition(6,' ',0,13);
            afd.addTransition(6,'\n',0,14);
            // transitions entre 0 et 7
            afd.addTransition(0,'/',7,0);
            // transitions entre 7 et 0
            afd.addTransition(7,' ',0,15);
            afd.addTransition(7,'\n',0,16);
            // transitions entre 0 et 8
            afd.addTransition(0,'#',8,0);
            /* Ajout de nouvelles transitions pour la gestion des variables */
            // transition entre 0 et 9
            // = lecture du '>'
            afd.addTransition(0,'>',9,17);
            // transition entre 9 et 0
            afd.addTransition(9,' ',0,18);
            afd.addTransition(9,'\n',0,19);
            // transition entre 0 et 10
            // = lecture de la 1ère lettre de la variable
            for(int i=0;i<26;i++) afd.addTransition(0,(char)(i+'A'),10,20);
            // transition entre 10 et 10
            // = lecture des autres lettres de la variable
            for(int i=0;i<26;i++) afd.addTransition(10,(char)(i+'A'),10,21);
            // transition entre 10 et 0
            afd.addTransition(10,' ',0,22);
            afd.addTransition(10,'\n',0,23);
        } catch (AFDException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        afd.toDot(); // pour debug
        return afd;
    }

    static Stack<Double> pile;      // la pile des valeurs
    static AFD automate;            // l'automate
    static BufferedReader console;  // le lecteur sur la console

    // les variables utilisées dans les actions de l'automate
    private static double valeur;   // valeur courante du nombre
    private static double facteur;  // facteur courant de la partie décimale
    private static boolean negatif; // vrai si valeur négative 
    // variable pour le stockage mémoire des variables de la calculatrice
    static HashMap<String, Double> variables;
    static String nomVar;   // nom de la variable courante
    static boolean recordVar;   // vrai si on enregistre une variable
    /**
     * Réinitialisation des variables courantes utilisées dans les actions de l'automate
     */
    private static void reset_values() {
        // attention on change les valeurs d'initialisation
        valeur = 0.;
        facteur= 0.1;
        boolean negatif= false;
    }

    /**
     * Affichage du sommet de pile, puis du prompt
     */
    private static void display_top() {
        if (! pile.isEmpty())      
            System.out.println("result: "+pile.pop());
        display_prompt();
    }

    /**
     * Affichage du prompt
     */
    private static void display_prompt(){
        System.out.print("Input expression: ");
    }

    /**
     * Actions à réaliser en cas d'erreur dans les actions de l'automate ou de transistion inexistante
     * @param msg message à afficher
     */
    private static void error(String msg){
        System.out.println(msg);
        try {
            automate.reset();
            pile.clear();
        } catch (AFDException ignore) {
        }
        try {
            if (console.ready()) console.readLine();
        } catch (IOException ignore) {
        }
        display_prompt();
        reset_values();
    }

    /**
     * Fonction de conversion d'un caractère dans la valeur du chiffre décimal correspondant
     * @param c un caractère représentant un chiffre décimal
     * @return la valeur du chiffre ou -1 si caractère non valable pour un chiffre
     */
    private static int atoi(char c){
        switch(c){
            case '0':return 0;
            case '1':return 1;
            case '2':return 2;
            case '3':return 3;
            case '4':return 4;
            case '5':return 5;
            case '6':return 6;
            case '7':return 7;
            case '8':return 8;
            case '9':return 9;
            default: return -1;
        }
    }

    /**
     * Programme principal 
     * gestion des différents cas de calculs 
     * @param args pas de paramètre sur la ligne de commande
     */
    public static void main(String[] args) {
        // initialisations
        automate = build_AFD();
        pile = new Stack <Double>();
        variables = new HashMap<String, Double>();
        double tmp1=0,tmp2=0;

        InputStreamReader r = new InputStreamReader(System.in);
        console = new BufferedReader(r);
        // reset des valeurs et de l'automate
        error("Début du programme");

        do{// boucle sur la lecture des symboles
            char symb= ' '; // symbole courant
            try {
                symb = (char) console.read();
            } catch (IOException e) {
                System.exit(1);
            }
            int a=0; // numéro de l'action à executer
            try {
                // execution de la transition
                // on vérifie si le caractère saisi est autorisé
                if (isCharValid(symb)) a = automate.makeTransition(symb);
            } catch (AFDException e) {
                System.out.println("error : "+e);
            }
            if (!isCharValid(symb)) {
                System.out.println("error : non valid symbol");
            } else {
                switch(a){ // execution de l'action renvoyée par la transition 
                    case 0: // rien à faire
                        break;
                    case 1: // premier chiffre de la partie entière 
                        valeur = atoi(symb);
                        break;
                    case 2: // chiffre suivant de la partie entière
                        valeur = valeur*10. + atoi(symb);
                        break;
                    case 3: // premier chiffre de la partie décimale
                        valeur = valeur + facteur * atoi(symb);
                        break;
                    case 4: // chiffre suivant de la partie décimale
                        facteur *= 0.1;
                        valeur = valeur + facteur * atoi(symb);
                        break;
                    case 5: // premier chiffre de la partie entière suivant un '-'
                        negatif = true; 
                        valeur = -atoi(symb);
                        break;
                    case 6: // valeur décimale empilée
                        pile.push(valeur);
                        reset_values();
                        break;
                    case 7: // valeur décimale empilée et affichage du sommet de pile
                        pile.push(valeur);
                        display_top();
                        reset_values();
                        break;
                    case 8: // affichage du sommet de pile et du prompt
                        display_top();
                        reset_values();
                        break;
                    case 9: // soustraction
                        if (pile.size() < 2){
                            error("substract : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1-tmp2);
                            reset_values();
                        }
                        break;
                    case 10: // soustraction et affichage
                        if (pile.size() < 2){
                            error("substract : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1-tmp2);
                            display_top();
                            reset_values();
                        }
                        break;
                    case 11: // addition
                        if (pile.size() < 2) {
                            error("add : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1+tmp2);
                            reset_values();
                        }
                        break;
                    case 12: // addition et affichage
                        if (pile.size() < 2) {
                            error("add : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1+tmp2);
                            display_top();
                            reset_values();
                        }
                        break;
                    case 13: // multiplication
                        if (pile.size() < 2) {
                            error("multiply : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1*tmp2);
                            reset_values();
                        }
                        break;
                    case 14: // multiplication et affichage
                        if (pile.size() < 2) {
                            error("multiply : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1*tmp2);
                            display_top();
                            reset_values();
                        }
                        break;
                    case 15: // division
                        if (pile.size() < 2) {
                            error("divide : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1/tmp2);
                            reset_values();
                        }
                        break;
                    case 16: // division et affichage
                        if (pile.size() < 2) {
                            error("divide : not enough operands");
                        } else {
                            tmp2 = pile.pop();
                            tmp1 = pile.pop();
                            pile.push(tmp1/tmp2);
                            display_top();
                            reset_values();
                        }
                        break;
                    case 17: // on vient de lire '>',ne rien faire
                        break;
                    case 18: // préparation du stockage dans nomVar
                        nomVar = "";
                        recordVar = true;
                        break;
                    case 19: // '\n'
                        error("> : missing variable name");
                        break;
                    case 20: // lecture de la 1ère lettre de la variable
                        nomVar += symb;
                        break;
                    case 21: // lecture des lettres suivantes
                        nomVar += symb;
                        break;
                    case 22: // ' ' après nom de la variable
                        if (recordVar) { // si on est en mode enregistrement
                            recordVar=false;
                            // on stocke dans la variable si il y a au moins un élément dans la pile
                            if (pile.size()>0) {
                                variables.put(nomVar,pile.pop());
                                System.out.println("Nouvelle variable : "+nomVar+"="+variables.get(nomVar)); 
                            }
                            else error("empty stack for creating variable "+nomVar);
                        } else {
                            // on fait appel à la variable si elle existe dans notre table de hashage
                            if (variables.containsKey(nomVar)) pile.push(variables.get(nomVar));
                            else error("variable "+nomVar+" doesn't exist");
                        }
                        nomVar="";
                        break;
                    case 23: // '\n', stockage dans la variable
                        if (recordVar) {
                            recordVar=false;
                            // on stocke dans la variable si il y a au moins un élément dans la pile
                            if (pile.size()>0) {
                                variables.put(nomVar,pile.pop());
                                System.out.println("Nouvelle variable : "+nomVar+"="+variables.get(nomVar)); 
                            display_prompt();
                            }
                            else error("empty stack for creating variable "+nomVar);
                        } else {
                            // on fait appel à la variable si elle existe dans notre table de hashage
                            if (variables.containsKey(nomVar)) {
                                pile.push(variables.get(nomVar));
                                display_top();
                            } else error("variable "+nomVar+" doesn't exist");
                        }
                        nomVar="";
                        reset_values();
                        break;
                }
            }   
        } while(! automate.isFinal()); // tant que l'on atteint pas un état final
        System.out.println("\nCiao !");
        try {
            console.close();
        } catch (IOException e) {
            System.out.println("error : "+e);
        }
    }

    private static boolean isCharValid(char c) {
        // fonction qui liste les caractères autorisés
        return (c>='0' && c<='9') || (c>='A' && c<='Z') ||
            c=='*' || c=='+' || c=='-' || c=='*' || c=='/' ||
            c==' ' || c=='\n' || c=='#' || c=='.' || c=='>';
    }
}
