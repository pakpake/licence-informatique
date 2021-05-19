/**
 * Compilateur PTS Version 1
 * F. Raimbault
 */
package pts.ast;

/**
 * Valeur possible d'étiquette d'un noeud de l'AST
 * @author raimbaul
 */
public enum EtiquetteNoeud {

  IDENTIFIER("ident"),
  NEW_IDENTIFIER("new_ident"),
  PROGRAM("program"),
  PARAMETER_DECLARATION_LIST("param_decl_list"),
  PARAMETER_DECLARATION("param_decl"),
  VARIABLE_DECLARATION_LIST("var_decl_list"),
  VARIABLE_DECLARATION("var_decl"),
  FUNCTION_DECLARATION_LIST("fct_decl_list"),
  FUNCTION_DECLARATION("fct_decl"),
  ARGUMENT_LIST("arg_list"),
  INTEGER_TYPE("int_type"),
  BOOLEAN_TYPE("bool_type"),
  VOID_TYPE("void_type"),
  ARRAY_TYPE("array_type"),
  STATEMENT_LIST("stm_list"),
  COMPOUND_STATEMENT("compound"),
  WHILE_STATEMENT("while"),
  IF_STATEMENT("if"),
  ASSIGN_STATEMENT("assign"),
  VARIABLE_ACCESS("var"),
  ARRAY_ACCESS("array"),
  PROCEDURE_STATEMENT("proc_call"),
  EQUAL_EXPRESSION("equal"),
  NOT_EQUAL_EXPRESSION("not_equal"),
  LOWER_EXPRESSION("lower"),
  GREATER_EXPRESSION("greater"),
  LOWER_EQUAL_EXPRESSION("lower_equal"),
  GREATER_EQUAL_EXPRESSION("greater_equal"),
  MINUS_EXPRESSION("minus"),
  PLUS_EXPRESSION("plus"),
  NEGATIVE_EXPRESSION("negative"),
  OR_EXPRESSION("or"),
  MUL_EXPRESSION("mul"),
  DIV_EXPRESSION("div"),
  MOD_EXPRESSION("mod"),
  AND_EXPRESSION("and"),
  INTEGER_VALUE("integer"),
  BOOLEAN_VALUE("boolean"),
  NOT_EXPRESSION("not"),
  FUNCTION_CALL("fct_call"),
  READ_STATEMENT("read"),
  WRITE_STATEMENT("write"),
  RETURN_STATEMENT("return"),
  BREAKIF_STATEMENT("breakif");//TODO: breakif
  
  /**
   * texte de l'étiquette
   */
  private String texte;

  EtiquetteNoeud(String e){
    this.texte = e;
  }

  /**
   * extracteur du label associé à un noeud
   * @return the texte
   */
  public String getLabel() {
    return texte;
  }
}
