/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts;

import java.util.LinkedList;
import java.util.Stack;

import map.CodeOperation;
import pts.ast.Noeud;
import pts.symb.Symbole;
import pts.type.TypeArray;
import as.Instruction;

/**
 * Générateur de code assembleur pour la MAP
 */
public class GenerateurCode {

  private static int cpt_global; // compteur du nombre de variables (et constantes) globales

  private static int cpt_local; // compteur du nombre de variables locales à la fonction courante

  private static int cpt_label; // compteur du nombre d'étiquettes générées

  private static int cpt_param; // compteur du nombre de paramètres de la fonction courante

  private static String return_label; // étiquette de fin de procédure (adresse de l'instruction RET)
  
  // une pile est obligatoire pour traiter les WHILES imbiqués,
  // sinon un BREAK dans le WHILE englobant se branche à l''étiquette de fin du WHILE imbriqué.
  // sur breakif2.pts le prgm semble fonctionner mais ne termine pas
  // on peut remplacer la pile par une variable déclarée dans le case BREAKIF_STATEMENT
  private Stack<String> break_stack = new Stack<String>(); // TODO: breakif
  
  static String newLabel() {
    return new String("L" + (cpt_label++) +":");
  }

  /**
   * Parcours de l'AST et synthèse du code assembleur
   * @param node noeud courant de l'AST
   * @param right_hand (attribut hérité) indicateur de génération de code pour une partie droite
   * @param next_label (attribut hérité) étiquette de l'instruction suivante
   * @param true_label (attribut hérité) étiquette de l'instruction si une expression booléenne est vraie
   * @param false_label (attribut hérité) étiquette de l'instruction si une expression booléenne est fausse
   * @return le code synthétisé pour MAP
   */
  public LinkedList<Instruction> parcours(Noeud node, 
                                          boolean right_hand, 
                                          String next_label, 
                                          String true_label, 
                                          String false_label){
    LinkedList<Instruction> code = new LinkedList<Instruction>();
    if (Compilateur.debug_gen_flag) Compilateur.displayMessage(" *** parcours du noeud "+node);
    switch ( node.etiquette ) {
    case PROGRAM: {
      // nom du programme
      // parcours(n.fils[0]); inutile
      cpt_label = 0;
      cpt_global = 0;
      // variables globales
      if (node.fils[1] != null) parcours(node.fils[1], false,null,null,null);
      if (cpt_global != 0){// on réserve la place des variables globales
        code.addFirst(new Instruction(null, CodeOperation.GBL, String.valueOf(cpt_global), null));
      }
      // saut au début du programme principal
      String debut = newLabel();
      code.add(new Instruction(null, CodeOperation.JMP, null,debut));
      // declarations des fonctions
      if (node.fils[2] != null) 
        code.addAll(parcours(node.fils[2], false,null,null,null));
      // instructions du programme principal
      code.add(new Instruction(debut, CodeOperation.NOP,null,null));
      String fin = newLabel(); // etiquette du HALT
      code.addAll(parcours(node.fils[3], false,fin,null,null));
      code.add(new Instruction(fin, CodeOperation.HLT, null, null));
      break;
    }
    case VARIABLE_DECLARATION_LIST:
    case FUNCTION_DECLARATION_LIST:
      for (int i = 0; i < node.fils.length; i++) {
        code.addAll(parcours(node.fils[i], false,null,null,null));
      }
      break;
    case VARIABLE_DECLARATION: {
      //parcours(node.fils[0], null); inutile
      Symbole symb = node.fils[0].symbole;
      //parcours(node.fils[1], null); inutile
      symb.setTaille(symb.getType().taille);
      if (symb.estGlobal()) {
        symb.setAdresse(cpt_global);
        cpt_global += symb.getTaille();
      } else {
        symb.setAdresse(cpt_local);
        cpt_local += symb.getTaille();
      }
      break;
    }
    case IDENTIFIER:
    case NEW_IDENTIFIER:
    case INTEGER_TYPE:
    case BOOLEAN_TYPE:
    case VOID_TYPE:
    case ARRAY_TYPE: 
      break;
    case FUNCTION_DECLARATION: {
      // parcours(node.fils[0],false); inutile
      Symbole symb = node.fils[0].symbole;
      cpt_param = 0;
      // on associe au symbole l'adresse de début du code de la fonction
      symb.setEtiquette(newLabel());
      code.add(new Instruction(symb.getEtiquette(),CodeOperation.NOP,null,null));
      if (node.fils[1] != null) parcours(node.fils[1],false,null,null,null);
      //parcours(node.fils[2], null); inutile
      cpt_local = 0;
      return_label = newLabel();// etiquette pour l'instruction RETURN de la fonction
      code.addAll(parcours(node.fils[3],false,return_label,null,null));
      code.add(new Instruction(return_label, CodeOperation.RET, String.valueOf(cpt_param), null));
      break;
    }
    case PARAMETER_DECLARATION_LIST: 
      for (int i = node.fils.length - 1; i >= 0; i--) {// parcours des fils
        parcours(node.fils[i],false,null,null,null);
      }
      break;
    case PARAMETER_DECLARATION: {
      //parcours(node.fils[0], null); inutile
      Symbole symb = node.fils[0].symbole;
      // calcul de l'adresse du parametre
      symb.setAdresse(-(cpt_param + 3));
      // maj du du compteur de parametres
      cpt_param += 1;
      // parcours(node.fils[1],null); inutile
      break;
    }
    case COMPOUND_STATEMENT: {
      // sauvegarde du compteur des variables locales
      int prev_cpt = cpt_local;
      String fin; // etiquette de la dernière instruction du bloc
      // déclarations ou instructions du bloc
      if (node.fils[0] != null) parcours(node.fils[0], false,null,null,null);
      if (cpt_local != prev_cpt) { // il y a eu des déclarations locales (cas de blocs imbriqués)
        fin= newLabel(); // étiquette utilisée pour le DEC
        code.add(new Instruction(null, CodeOperation.INC, String.valueOf(cpt_local-prev_cpt), null));
      }else{ // pas de déclarations locales
        fin= next_label; // inutile
      }
      // instructions du bloc
      if (node.fils[1] != null)
        code.addAll(parcours(node.fils[1], false,next_label,null,null));
      if (cpt_local != prev_cpt) { // il y a eu des déclarations locales 
        code.add(new Instruction(fin, CodeOperation.DEC, String.valueOf(cpt_local-prev_cpt), null));
        cpt_local= prev_cpt;
      }
      break;
    }
    case ASSIGN_STATEMENT: {
      if (node.fils[0].type.isBoolean()) {
        // générer le test de la partie droite et générer une affectation dans la partie gauche
        String vrai = newLabel();
        String faux = newLabel();
        code.addAll(parcours(node.fils[1], true, null, vrai, faux));
        code.add(new Instruction(vrai, CodeOperation.LDC, String.valueOf(1), null));
        code.add(new Instruction(null,CodeOperation.STI,null,null));
        code.add(new Instruction(null,CodeOperation.JMP,null,next_label));
        code.add(new Instruction(faux, CodeOperation.LDC, String.valueOf(0), null));
        code.add(new Instruction(null,CodeOperation.STI,null,null));
        code.add(new Instruction(null,CodeOperation.JMP,null,next_label));
      } else {// parcours des fils et génération d'une affectation
        code.addAll(parcours(node.fils[1], true,null,null,null));
        code.addAll(parcours(node.fils[0], false,null,null,null));
        code.add(new Instruction(null,CodeOperation.STI,null,null));
      }
      break;
    }
    case VARIABLE_ACCESS: {
      Symbole var_symb = node.fils[0].symbole;
      if (right_hand) { // variable apparaissant en partie droite d'une affectation, on charge sa valeur
        if (var_symb.estGlobal()) {// variable globale
          code.add(new Instruction(null, CodeOperation.LDG, String.valueOf(var_symb.getAdresse()),null));
        } else {// variable locale
          code.add(new Instruction(null, CodeOperation.LDL, String.valueOf(var_symb.getAdresse()),null));
        }
        if (var_symb.getType().isBoolean()) {// en partie droite d'une expression booleenne
          code.add(new Instruction(null, CodeOperation.JNZ, null, true_label));
          code.add(new Instruction(null,CodeOperation.JMP,null,false_label));
        }
      }else{// variable apparaissant en partie gauche d'une affectation, on charge son adresse
        if (var_symb.estGlobal()) {
          code.add(new Instruction(null, CodeOperation.LGA, String.valueOf(var_symb.getAdresse()),null));
        } else {
          code.add(new Instruction(null, CodeOperation.LLA, String.valueOf(var_symb.getAdresse()),null));
        }
      }
      break;
    }
    case ARRAY_ACCESS: {
      //parcours(node.fils[0], null); //inutile
      TypeArray array_type= (TypeArray) node.fils[0].type;
      Symbole array_symb= node.fils[0].symbole;
      // calcul de l'adresse de la variable indexée
      code.addAll(parcours(node.fils[1], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.LDC, String.valueOf(array_type.getEltType().taille),null));
      code.add(new Instruction(null, CodeOperation.MUL, null,null));
      if (array_symb.estGlobal()) {//tableau global
        code.add(new Instruction(null, CodeOperation.LGA, String.valueOf(array_symb.getAdresse()),null));
      }else{// tableau local
        code.add(new Instruction(null, CodeOperation.LLA, String.valueOf(array_symb.getAdresse()),null));
      }
      code.add(new Instruction(null, CodeOperation.ADD, null,null));
      if (right_hand) { // tableau apparaissant en partie droite
        code.add(new Instruction(null, CodeOperation.LDI, null,null));
        if (array_type.isBoolean()) {// en partie droite d'une expression booleenne
          code.add(new Instruction(null, CodeOperation.JNZ, null, true_label));
          code.add(new Instruction(null,CodeOperation.JMP,null,false_label));
        }
      }else{// tableau apparaissant en partie gauche d'une affectation, on charge son adresse
        // c'est déjà fait !
      }
      break;
    }
    case INTEGER_VALUE:
      code.add(new Instruction(null, CodeOperation.LDC, node.identificateur, null));
      break;
    case MINUS_EXPRESSION: {
      code.addAll(parcours(node.fils[0], right_hand,next_label,true_label,false_label));
      code.addAll(parcours(node.fils[1], right_hand,next_label,true_label,false_label));
      code.add(new Instruction(null, CodeOperation.SUB, null, null));
      break;
    }
    case NEGATIVE_EXPRESSION: {
      code.add(new Instruction(null, CodeOperation.LDC, String.valueOf(0), null));
      code.addAll(parcours(node.fils[0], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.SUB, null, null));
      // ou bien
      //code.addAll(parcours(node.fils[0],true,null,null,null));
      //code.add(new Instruction(null,CodeOperation.LDC,"-1",null));
      //code.add(new Instruction(null,CodeOperation.MUL,null,null));
      break;
    }
    case PLUS_EXPRESSION: {
      code.addAll(parcours(node.fils[0], true,null,null,null));
      code.addAll(parcours(node.fils[1], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.ADD, null, null));
      break;
    }
    case MUL_EXPRESSION: {
      code.addAll(parcours(node.fils[0], true,null,null,null));
      code.addAll(parcours(node.fils[1], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.MUL, null, null));
      break;
    }
    case DIV_EXPRESSION: {
      code.addAll(parcours(node.fils[0], true,null,null,null));
      code.addAll(parcours(node.fils[1], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.DIV, null, null));
      break;
    }
    case MOD_EXPRESSION: {
      code.addAll(parcours(node.fils[0], true,null,null,null));
      code.addAll(parcours(node.fils[1], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.MOD, null, null));
      break;
    }
    case STATEMENT_LIST:
      for (int i = 0; i < node.fils.length; i++) {
        if (i+1 < node.fils.length){
        String suiv = newLabel();
        code.addAll(parcours(node.fils[i], false, suiv, null, null));
        code.add(new Instruction(suiv,CodeOperation.NOP,null,null));
        }else{ // dernière instruction de la liste
          code.addAll(parcours(node.fils[i], false, next_label, null, null));
      }
      }
      break;
    case READ_STATEMENT: {
      code.add(new Instruction(null, CodeOperation.RCV, null, null));
      code.addAll(parcours(node.fils[0], false,null,null,null));
      code.add(new Instruction(null,CodeOperation.STI,null,null));
      break;
    }
    case WRITE_STATEMENT: {
      code.addAll(parcours(node.fils[0], true,null,null,null));
      code.add(new Instruction(null, CodeOperation.SND, null, null));
      break;
    }
    case WHILE_STATEMENT: {
      String debut = newLabel();
      code.add(new Instruction(debut,CodeOperation.NOP,null,null));
      String vrai = newLabel();
      code.addAll(parcours(node.fils[0], true, null, vrai, next_label));
      code.add(new Instruction(vrai,CodeOperation.NOP,null,null));
      //String break_label = next_label;       // TODO: breakif, il faut que ce soit une var glob
      // ne fonctionne pas dans le cas de while imbriqués
      break_stack.push(next_label); // TODO: breakif, à déclarer au début de la classe
      code.addAll(parcours(node.fils[1], false, debut, null, null));
      break_stack.pop(); // TODO: breakif
      code.add(new Instruction(null,CodeOperation.JMP,null,debut));
      break;
    }
    case BREAKIF_STATEMENT: { //TODO: breakif
    	//code.addAll(parcours(node.fils[0], true, null, break_label, next_label));
    	code.addAll(parcours(node.fils[0], true, null, break_stack.peek(), next_label));
    	break;
    }
    case IF_STATEMENT: {
      String vrai = newLabel();
      if (node.fils[2] == null) {// pas de sinon
        code.addAll(parcours(node.fils[0], true, null, vrai, next_label));
        code.add(new Instruction(vrai,CodeOperation.NOP,null,null));
        code.addAll(parcours(node.fils[1], false, next_label, null, null));
      } else {// avec un sinon
        String faux = newLabel();
        code.addAll(parcours(node.fils[0], true, null, vrai, faux));
        code.add(new Instruction(vrai,CodeOperation.NOP,null,null));
        code.addAll(parcours(node.fils[1], false, next_label, null, null));
        code.add(new Instruction(null,CodeOperation.JMP,null,next_label));
        code.add(new Instruction(faux,CodeOperation.NOP,null,null));
        code.addAll(parcours(node.fils[2], false, next_label, null, null));
      }
      break;
    }
    case BOOLEAN_VALUE:
      if (Boolean.parseBoolean(node.identificateur)){// valeur vrai
        code.add(new Instruction(null,CodeOperation.JMP,null,true_label));
      }else{//valeur faux
        code.add(new Instruction(null,CodeOperation.JMP,null,false_label));
      }
      break;
    case EQUAL_EXPRESSION:
      code.addAll(parcours(node.fils[0], true, null, null, null));
      code.addAll(parcours(node.fils[1], true, null, null, null));
      code.add(new Instruction(null,CodeOperation.SUB,null,null));
      code.add(new Instruction(null, CodeOperation.JNZ, null, false_label));
      code.add(new Instruction(null,CodeOperation.JMP,null,true_label));
      break;
    case NOT_EQUAL_EXPRESSION:
      code.addAll(parcours(node.fils[0], true, null, null, null));
      code.addAll(parcours(node.fils[1], true, null, null, null));
      code.add(new Instruction(null,CodeOperation.SUB,null,null));
      code.add(new Instruction(null, CodeOperation.JNZ, null, true_label));
      code.add(new Instruction(null, CodeOperation.JMP, null, false_label));
      break;
    case GREATER_EQUAL_EXPRESSION:
      code.addAll(parcours(node.fils[0], true, null, null, null));
      code.addAll(parcours(node.fils[1], true, null, null, null));
      code.add(new Instruction(null,CodeOperation.SUB,null,null));
      code.add(new Instruction(null, CodeOperation.JGZ, null, true_label));
      code.add(new Instruction(null, CodeOperation.JMP, null, false_label));
      break;
    case LOWER_EXPRESSION:
      code.addAll(parcours(node.fils[0], true, null, null, null));
      code.addAll(parcours(node.fils[1], true, null, null, null));
      code.add(new Instruction(null,CodeOperation.SUB,null,null));
      code.add(new Instruction(null, CodeOperation.JGZ, null, false_label));
      code.add(new Instruction(null, CodeOperation.JMP, null, true_label));
      break;
    case GREATER_EXPRESSION:
      code.addAll(parcours(node.fils[1], true, null, null, null));
      code.addAll(parcours(node.fils[0], true, null, null, null));
      code.add(new Instruction(null,CodeOperation.SUB,null,null));
      code.add(new Instruction(null, CodeOperation.JGZ, null, false_label));
      code.add(new Instruction(null, CodeOperation.JMP, null, true_label));
      break;
    case LOWER_EQUAL_EXPRESSION:
      code.addAll(parcours(node.fils[1], true, null, null, null));
      code.addAll(parcours(node.fils[0], true, null, null, null));
      code.add(new Instruction(null,CodeOperation.SUB,null,null));
      code.add(new Instruction(null, CodeOperation.JGZ, null, true_label));
      code.add(new Instruction(null, CodeOperation.JMP, null, false_label));
      break;
    case OR_EXPRESSION: {
      String faux = newLabel();
      code.addAll(parcours(node.fils[0], true, null, true_label, faux));
      code.add(new Instruction(faux,CodeOperation.NOP,null,null));
      code.addAll(parcours(node.fils[1], true, null, true_label, false_label));
      break;
    }
    case AND_EXPRESSION: {
      String vrai = newLabel();
      code.addAll(parcours(node.fils[0], true, null, vrai, false_label));
      code.add(new Instruction(vrai,CodeOperation.NOP,null,null));
      code.addAll(parcours(node.fils[1], true, null, true_label, false_label));
      break;
    }
    case NOT_EXPRESSION: {
      code = parcours(node.fils[0], true, null, false_label, true_label);
      break;
    }
    case PROCEDURE_STATEMENT: 
    case FUNCTION_CALL: {
     // parcours(n.fils[0],false,null,null,null); inutile
      Symbole symb = node.fils[0].symbole;
      if (symb.getType().isFunction()) {// reserver un emplacement pour le résultat sur la pile
        code.add(new Instruction(null, CodeOperation.INC, String.valueOf(1), null));
      }
      if (node.fils[1] != null) {// parametres
        code.addAll(parcours(node.fils[1], true, null, null, null));
      }
      // appel du sous-programme
      code.add(new Instruction(null,CodeOperation.JSR,null,symb.getEtiquette()));
      break;
    }
    case ARGUMENT_LIST: { 
      for (int i =0 ; i < node.fils.length; i++) { // parcours des arguments
        code.addAll(parcours(node.fils[i], true,null,null,null));
      }
      break;
    }
    case RETURN_STATEMENT: {
      // calcul de l'expression retournée
      code.addAll(parcours(node.fils[0], true, null, null, null));
      // mémorisation du résultat
      code.add(new Instruction(null, CodeOperation.STL, String.valueOf(-(cpt_param + 3)), null));
      // saut à l'instruction RETURN de la fonction
      code.add(new Instruction(null, CodeOperation.JMP, null, return_label));
      break;
    }
    }
    return code;
  }

}
