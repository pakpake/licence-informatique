/* Generated By:JavaCC: Do not edit this line. AnalyseurSyntaxique.java */
package pts.javacc;

import java.util.ArrayList;
import pts.ast.*;

public class AnalyseurSyntaxique implements AnalyseurSyntaxiqueConstants {

/********************************************
 * LA GRAMMAIRE DU LANGAGE TPS COMMENCE ICI *
 ********************************************/
  final public Noeud Program() throws ParseException {
    Noeud n1,n2=null,n3=null,n4;
    jj_consume_token(PROGRAM);
    n1 = NewIdentifier();
    jj_consume_token(SEMICOLON);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      n2 = VariableDeclarationList();
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FUNCTION:
      n3 = FunctionDeclarationList();
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    n4 = CompoundStatement();
    jj_consume_token(DOT);
                {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.PROGRAM,new Noeud[]{n1,n2,n3,n4});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud VariableDeclarationList() throws ParseException {
    Noeud n;
    ArrayList ln= new ArrayList();
    n = VariableDeclaration();
                              ln.add(n);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMICOLON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        n = VariableDeclaration();
                                                                                    ln.add(n);
        break;
      default:
        jj_la1[3] = jj_gen;
        ;
      }
    }
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.VARIABLE_DECLARATION_LIST,ln);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud VariableDeclaration() throws ParseException {
    Noeud n1,n2;
    jj_consume_token(VAR);
    n1 = NewIdentifier();
    jj_consume_token(COLON);
    n2 = Type();
       {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.VARIABLE_DECLARATION,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud FunctionDeclarationList() throws ParseException {
    ArrayList ln= new ArrayList();
    Noeud n;
    n = FunctionDeclaration();
                              ln.add(n);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      jj_consume_token(SEMICOLON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNCTION:
        n = FunctionDeclaration();
                                                                                       ln.add(n);
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
    }
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.FUNCTION_DECLARATION_LIST,ln);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud FunctionDeclaration() throws ParseException {
    Noeud n1,n2=null,n3,n4;
    jj_consume_token(FUNCTION);
    n1 = NewIdentifier();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      n2 = ParameterDeclarationList();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    jj_consume_token(COLON);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case INTEGER:
      n3 = BasicType();
      break;
    case VOID:
      n3 = VoidType();
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(SEMICOLON);
    n4 = CompoundStatement();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.FUNCTION_DECLARATION,new Noeud[]{n1,n2,n3,n4});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud ParameterDeclarationList() throws ParseException {
    Noeud n;
    ArrayList ln= new ArrayList();
    n = ParameterDeclaration();
                               ln.add(n);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      n = ParameterDeclaration();
                                                                                ln.add(n);
    }
        {if (true) return new Noeud(getToken(0).beginLine,
                EtiquetteNoeud.PARAMETER_DECLARATION_LIST,ln);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud ParameterDeclaration() throws ParseException {
    Noeud n1,n2;
    n1 = NewIdentifier();
    jj_consume_token(COLON);
    n2 = BasicType();
       {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.PARAMETER_DECLARATION,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Type() throws ParseException {
    Noeud n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case INTEGER:
      n = BasicType();
        {if (true) return n;}
      break;
    case ARRAY:
      n = ArrayType();
        {if (true) return n;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud BasicType() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      jj_consume_token(INTEGER);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.INTEGER_TYPE);}
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.BOOLEAN_TYPE);}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud VoidType() throws ParseException {
    jj_consume_token(VOID);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.VOID_TYPE);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud ArrayType() throws ParseException {
    Noeud n1,n2;
    jj_consume_token(ARRAY);
    jj_consume_token(LBRAC);
    n1 = Integer();
    jj_consume_token(RBRAC);
    jj_consume_token(OF);
    n2 = BasicType();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.ARRAY_TYPE,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud CompoundStatement() throws ParseException {
    Noeud n1=null,n2=null;
    jj_consume_token(BEGIN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      n1 = VariableDeclarationList();
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
    case RETURN:
    case WHILE:
    case BEGIN:
    case READ:
    case WRITE:
    case FOR:
    case IDENTIFIER:
      n2 = StatementList();
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    jj_consume_token(END);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.COMPOUND_STATEMENT,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud StatementList() throws ParseException {
    ArrayList ln= new ArrayList();
    Noeud n;
    n = Statement();
                   ln.add(n);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_4;
      }
      jj_consume_token(SEMICOLON);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IF:
      case RETURN:
      case WHILE:
      case BEGIN:
      case READ:
      case WRITE:
      case FOR:
      case IDENTIFIER:
        n = Statement();
                                                              ln.add(n);
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
    }
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.STATEMENT_LIST,ln);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Statement() throws ParseException {
    Noeud n;
    if (jj_2_1(2)) {
      n = AssignmentStatement();
                {if (true) return n;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        n = ProcedureStatement();
                {if (true) return n;}
        break;
      case BEGIN:
        n = CompoundStatement();
                {if (true) return n;}
        break;
      case IF:
        n = IfStatement();
                {if (true) return n;}
        break;
      case FOR:
        n = ForStatement();
          {if (true) return n;}
        break;
      case WHILE:
        n = WhileStatement();
                {if (true) return n;}
        break;
      case READ:
      case WRITE:
        n = IOStatement();
                {if (true) return n;}
        break;
      case RETURN:
        n = ReturnStatement();
                {if (true) return n;}
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud ForStatement() throws ParseException {
        Noeud n1,n2,n3,n4;
    jj_consume_token(FOR);
    n1 = NewIdentifier();
    jj_consume_token(FROM);
    n2 = Expression();
    jj_consume_token(TO);
    n3 = Expression();
    jj_consume_token(DO);
    n4 = Statement();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.FOR_STATEMENT,new Noeud[] {n1,n2,n3,n4});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud WhileStatement() throws ParseException {
    Noeud n1,n2;
    jj_consume_token(WHILE);
    n1 = Expression();
    jj_consume_token(DO);
    n2 = Statement();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.WHILE_STATEMENT,new Noeud[] {n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud IfStatement() throws ParseException {
    Noeud n1,n2,n3=null;
    jj_consume_token(IF);
    n1 = Expression();
    jj_consume_token(THEN);
    n2 = Statement();
    if (jj_2_2(2147483647)) {
      jj_consume_token(ELSE);
      n3 = Statement();
    } else {
      ;
    }
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.IF_STATEMENT, new Noeud[] {n1,n2,n3});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud AssignmentStatement() throws ParseException {
    Noeud n1,n2;
    n1 = VariableAccess();
    jj_consume_token(ASSIGN);
    n2 = Expression();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.ASSIGN_STATEMENT, new Noeud[] {n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud VariableAccess() throws ParseException {
    Noeud n;
    if (jj_2_3(2)) {
      n = ArrayAccess();
        {if (true) return n;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        n = Identifier();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.VARIABLE_ACCESS,n);}
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud ArrayAccess() throws ParseException {
    Noeud n1,n2;
    n1 = Identifier();
    jj_consume_token(LBRAC);
    n2 = Expression();
    jj_consume_token(RBRAC);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.ARRAY_ACCESS,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud ProcedureStatement() throws ParseException {
    Noeud n1,n2=null;
    n1 = Identifier();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
    case TRUE:
    case NOT:
    case INTEGER_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case MINUS:
      n2 = ArgumentList();
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.PROCEDURE_STATEMENT,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud IOStatement() throws ParseException {
    Noeud n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case READ:
      jj_consume_token(READ);
      n = VariableAccess();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.READ_STATEMENT,n);}
      break;
    case WRITE:
      jj_consume_token(WRITE);
      n = Expression();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.WRITE_STATEMENT,n);}
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud ReturnStatement() throws ParseException {
    Noeud n;
    jj_consume_token(RETURN);
    n = Expression();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.RETURN_STATEMENT,n);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud ArgumentList() throws ParseException {
    Noeud n;
    ArrayList ln= new ArrayList();
    n = Argument();
                  ln.add(n);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_5;
      }
      jj_consume_token(COMMA);
      n = Argument();
                                                      ln.add(n);
    }
        {if (true) return new Noeud(getToken(0).beginLine,
            EtiquetteNoeud.ARGUMENT_LIST,ln);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Argument() throws ParseException {
    Noeud n;
    n = Expression();
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Expression() throws ParseException {
    Noeud n1,n2=null;
    EtiquetteNoeud op=null;
    n1 = SimpleExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case GT:
    case LT:
    case EQ:
    case LE:
    case GE:
    case NE:
      op = RelOp();
      n2 = SimpleExpression();
      break;
    default:
      jj_la1[20] = jj_gen;
      ;
    }
        {if (true) return (n2==null) ? n1 : new Noeud(getToken(0).beginLine,op,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public EtiquetteNoeud RelOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQ:
      jj_consume_token(EQ);
        {if (true) return EtiquetteNoeud.EQUAL_EXPRESSION;}
      break;
    case NE:
      jj_consume_token(NE);
        {if (true) return EtiquetteNoeud.NOT_EQUAL_EXPRESSION;}
      break;
    case LT:
      jj_consume_token(LT);
        {if (true) return EtiquetteNoeud.LOWER_EXPRESSION;}
      break;
    case GT:
      jj_consume_token(GT);
        {if (true) return EtiquetteNoeud.GREATER_EXPRESSION;}
      break;
    case LE:
      jj_consume_token(LE);
        {if (true) return EtiquetteNoeud.LOWER_EQUAL_EXPRESSION;}
      break;
    case GE:
      jj_consume_token(GE);
        {if (true) return EtiquetteNoeud.GREATER_EQUAL_EXPRESSION;}
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud SimpleExpression() throws ParseException {
    Noeud n1,n2=null;
    EtiquetteNoeud op=null;
    n1 = Term();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OR:
    case PLUS:
    case MINUS:
      op = AddOp();
      n2 = SimpleExpression();
      break;
    default:
      jj_la1[22] = jj_gen;
      ;
    }
        {if (true) return (n2==null) ? n1 : new Noeud(getToken(0).beginLine,op,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public EtiquetteNoeud AddOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
        {if (true) return EtiquetteNoeud.PLUS_EXPRESSION;}
      break;
    case MINUS:
      jj_consume_token(MINUS);
        {if (true) return EtiquetteNoeud.MINUS_EXPRESSION;}
      break;
    case OR:
      jj_consume_token(OR);
        {if (true) return EtiquetteNoeud.OR_EXPRESSION;}
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud Term() throws ParseException {
    Noeud n1,n2=null;
    EtiquetteNoeud op=null;
    n1 = Primary();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AND:
    case MUL:
    case DIV:
    case REM:
      op = MulOp();
      n2 = Term();
      break;
    default:
      jj_la1[24] = jj_gen;
      ;
    }
        {if (true) return (n2==null) ? n1 : new Noeud(getToken(0).beginLine,op,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public EtiquetteNoeud MulOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MUL:
      jj_consume_token(MUL);
        {if (true) return EtiquetteNoeud.MUL_EXPRESSION;}
      break;
    case DIV:
      jj_consume_token(DIV);
        {if (true) return EtiquetteNoeud.DIV_EXPRESSION;}
      break;
    case REM:
      jj_consume_token(REM);
        {if (true) return EtiquetteNoeud.MOD_EXPRESSION;}
      break;
    case AND:
      jj_consume_token(AND);
        {if (true) return EtiquetteNoeud.AND_EXPRESSION;}
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud Primary() throws ParseException {
    Noeud n;
    if (jj_2_4(2)) {
      n = FunctionCall();
        {if (true) return n;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        n = VariableAccess();
        {if (true) return n;}
        break;
      case INTEGER_LITERAL:
        n = Integer();
        {if (true) return n;}
        break;
      case FALSE:
      case TRUE:
        n = Boolean();
        {if (true) return n;}
        break;
      case MINUS:
        jj_consume_token(MINUS);
        n = Primary();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.NEGATIVE_EXPRESSION,n);}
        break;
      case LPAREN:
        jj_consume_token(LPAREN);
        n = Expression();
        jj_consume_token(RPAREN);
        {if (true) return n;}
        break;
      case NOT:
        jj_consume_token(NOT);
        n = Primary();
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.NOT_EXPRESSION,n);}
        break;
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public Noeud FunctionCall() throws ParseException {
    Noeud n1,n2= null;
    n1 = Identifier();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
    case TRUE:
    case NOT:
    case INTEGER_LITERAL:
    case IDENTIFIER:
    case LPAREN:
    case MINUS:
      n2 = ArgumentList();
      break;
    default:
      jj_la1[27] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
        {if (true) return new Noeud(getToken(0).beginLine,EtiquetteNoeud.FUNCTION_CALL,new Noeud[]{n1,n2});}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Identifier() throws ParseException {
    Token tok;
    tok = jj_consume_token(IDENTIFIER);
        {if (true) return new Noeud(tok.beginLine,EtiquetteNoeud.IDENTIFIER,tok.image);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud NewIdentifier() throws ParseException {
    Token tok;
    tok = jj_consume_token(IDENTIFIER);
        {if (true) return new Noeud(tok.beginLine,EtiquetteNoeud.NEW_IDENTIFIER,tok.image);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Integer() throws ParseException {
    Token tok;
    tok = jj_consume_token(INTEGER_LITERAL);
        {if (true) return new Noeud(tok.beginLine,EtiquetteNoeud.INTEGER_VALUE,tok.image);}
    throw new Error("Missing return statement in function");
  }

  final public Noeud Boolean() throws ParseException {
    Token tok;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      tok = jj_consume_token(TRUE);
        {if (true) return new Noeud(tok.beginLine,EtiquetteNoeud.BOOLEAN_VALUE,tok.image);}
      break;
    case FALSE:
      tok = jj_consume_token(FALSE);
        {if (true) return new Noeud(tok.beginLine,EtiquetteNoeud.BOOLEAN_VALUE,tok.image);}
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_3R_7() {
    if (jj_3R_10()) return true;
    if (jj_scan_token(LBRAC)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3R_10() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(ELSE)) return true;
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_3R_10()) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3R_9() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3R_11()) return true;
    }
    return false;
  }

  /** Generated Token Manager. */
  public AnalyseurSyntaxiqueTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200000,0x400000,0x0,0x200000,0x0,0x400000,0x0,0x48200,0x0,0x808200,0x8200,0x200000,0xc2094000,0x0,0xc2094000,0xc2094000,0x0,0x20022000,0xc0000000,0x0,0x0,0x0,0x8000000,0x8000000,0x10000000,0x10000000,0x20022000,0x20022000,0x22000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x1000,0x0,0x1000,0x0,0x10,0x0,0x2000,0x0,0x0,0x0,0x11,0x1000,0x11,0x11,0x10,0x1000118,0x0,0x2000,0x7e0000,0x7e0000,0x1800000,0x1800000,0xe000000,0xe000000,0x1000118,0x1000118,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[4];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public AnalyseurSyntaxique(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AnalyseurSyntaxique(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AnalyseurSyntaxiqueTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public AnalyseurSyntaxique(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new AnalyseurSyntaxiqueTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public AnalyseurSyntaxique(AnalyseurSyntaxiqueTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(AnalyseurSyntaxiqueTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[60];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 29; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 60; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 4; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
