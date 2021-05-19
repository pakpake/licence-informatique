/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts;

import pts.ast.Noeud;
import pts.erreur.Erreur;
import pts.erreur.Messages;
import pts.symb.Symbole;
import pts.symb.TableSymboles;

/**
 * Analyse des déclarations et des références sur les identificateurs
 */
public class AnalyseurIdentificateurs {

    /**
     * Parcours récursif gauche droite de l'AST pour la vérification des 
     * déclarations et des références. 
     * Synthèse d'un attribut contenant le symbole défini ou référencé par un noeud de l'AST.
     * Héritage de la table des symboles courante.
     * Ajoute au noeud courant le symbole définit.
     * @param node noeud courant de l'AST
     * @param tsymb table des symboles courante
     * @return le symbole défini ou référencé
     */
    public Symbole parcours(Noeud node, TableSymboles tsymb) {
      Symbole symb= null;
        switch (node.etiquette) {
        case IDENTIFIER:
            node.symbole = tsymb.referencer(node.identificateur);
            if (Compilateur.debug_ident_flag) 
              Compilateur.displayMessage(" * reference à l'identificateur "+node.symbole+ " ligne "+node.ligne);
            if (node.symbole == null)
        	    Erreur.compilation(Messages.IDENTIFIER_UNKNOW,node.identificateur,node.ligne);
            symb= node.symbole;
            break;
        case NEW_IDENTIFIER:
        	if (tsymb.contient(node.identificateur)) {
        	    Erreur.compilation(Messages.IDENTIFIER_ALREADY_DEFINED,node.identificateur,node.ligne);
        	} else {
        	    node.symbole= tsymb.definir(node.identificateur);
        	}
            symb= node.symbole;
            if (Compilateur.debug_ident_flag) 
              Compilateur.displayMessage(" * création de l'identificateur "+node.symbole+ " ligne "+node.ligne);
            break;
        case PROGRAM: 
        case VARIABLE_DECLARATION_LIST:
        case FUNCTION_DECLARATION_LIST:
        case PARAMETER_DECLARATION: 
        case PARAMETER_DECLARATION_LIST: 
        case VARIABLE_DECLARATION: 
        case VARIABLE_ACCESS:
        case ARRAY_ACCESS:
        case PROCEDURE_STATEMENT:
        case FUNCTION_CALL:
        case INTEGER_TYPE:
        case BOOLEAN_TYPE:
        case VOID_TYPE:
        case ARRAY_TYPE:
        case INTEGER_VALUE:
        case BOOLEAN_VALUE:
        case STATEMENT_LIST:
        case WHILE_STATEMENT:
        case IF_STATEMENT:
        case ASSIGN_STATEMENT:
        case READ_STATEMENT:
        case WRITE_STATEMENT:
        case RETURN_STATEMENT:
        case ARGUMENT_LIST: 
        case EQUAL_EXPRESSION:
        case NOT_EQUAL_EXPRESSION:
        case NEGATIVE_EXPRESSION:
        case LOWER_EXPRESSION:
        case GREATER_EXPRESSION:
        case LOWER_EQUAL_EXPRESSION:
        case GREATER_EQUAL_EXPRESSION:
        case MINUS_EXPRESSION:
        case PLUS_EXPRESSION:
        case OR_EXPRESSION:
        case MUL_EXPRESSION:
        case DIV_EXPRESSION:
        case MOD_EXPRESSION:
        case AND_EXPRESSION:
        case NOT_EXPRESSION:
            if (node.fils != null){
                for (int i = 0; i < node.fils.length; i++) {
                    if (node.fils[i] != null)
                        parcours(node.fils[i], tsymb);
                }
            }
            break;
        case FUNCTION_DECLARATION:{
          parcours(node.fils[0], tsymb);
          TableSymboles ts = tsymb.entrerBloc();
          if (Compilateur.debug_ident_flag) 
            Compilateur.displayMessage(" * entrée dans le bloc "+ts.getBlocId()+ " ligne "+node.ligne);     
          if (node.fils[1] != null) {
            parcours(node.fils[1], ts);
          }
          parcours(node.fils[2], ts);
          parcours(node.fils[3], ts);
          if (Compilateur.debug_ident_flag) 
            Compilateur.displayMessage(" * sortie du bloc "+ts.getBlocId()+ " ligne "+node.ligne);     
          ts = ts.sortirBloc();
          break;
          }
        case COMPOUND_STATEMENT:{
            TableSymboles ts= tsymb.entrerBloc();
            if (Compilateur.debug_ident_flag) 
              Compilateur.displayMessage(" * entrée dans le bloc "+ts.getBlocId()+ " ligne "+node.ligne);     
            if (node.fils != null){
              if (node.fils[0] != null) parcours(node.fils[0], ts);
              if (node.fils[1] != null) parcours(node.fils[1], ts);
            }
            if (Compilateur.debug_ident_flag) 
              Compilateur.displayMessage(" * sortie du bloc "+ts.getBlocId()+ " ligne "+node.ligne);      
            ts = ts.sortirBloc();
            break;
        }
        case FOR_STATEMENT:  // TODO: for
        	TableSymboles ts = tsymb.entrerBloc();
        	node.symbole = parcours(node.fils[0],ts);
        	parcours(node.fils[1],ts);
        	parcours(node.fils[2],ts);
        	parcours(node.fils[3],ts);
        	ts = ts.sortirBloc();
        	break;
      }
     return symb;
    }
}