// A COMPLETER
package as; 

import map.CodeOperation;

%%

// la classe generee s'appelle Scanner
%class Scanner

// les unites lexicales renvoyees ne sont pas des entiers 
// mais des instances de la classe as.UniteLexicale
%type UniteLexicale

%{
  // methode pour recuperer le numero de ligne d'une instruction
   public int getLineNo(){
	return yyline;
    }

%}


%eofval{ // on termine par l'unité lexicale fin de fichier
    return new UniteLexicale(TypeUniteLexicale.EOF,null); 
%eofval} 

WHITE_SPACE_CHAR=[\n\ \t\b]
LETTER=[A-Za-z_]
DIGIT=[0-9]
IDENT={LETTER}({LETTER}|{DIGIT})*
VALUE=(-)?{DIGIT}({DIGIT})*
LABEL={LETTER}({LETTER}|{DIGIT})*:

%char

%line

%full

%notunix

%ignorecase

%%

{WHITE_SPACE_CHAR}+ 
{ /* passe les blancs */}

{LABEL} 
{return new UniteLexicale(TypeUniteLexicale.LABEL,yytext());}

{VALUE} 
{return new UniteLexicale(TypeUniteLexicale.VALUE,yytext());}

";".*
{ /* passe les commentaires */ }

"NOP"
{return new UniteLexicale(CodeOperation.NOP);}

// INSERER VOS REGLES ET ACTIONS ICI
"SUB"
{return new UniteLexicale(CodeOperation.SUB);}

"RET"
{return new UniteLexicale(CodeOperation.RET);}

"ADD"
{return new UniteLexicale(CodeOperation.ADD);}

"MUL"
{return new UniteLexicale(CodeOperation.MUL);}

"DIV"
{return new UniteLexicale(CodeOperation.DIV);}

"MOD"
{return new UniteLexicale(CodeOperation.MOD);}

"SND"
{return new UniteLexicale(CodeOperation.SND);}

"RCV"
{return new UniteLexicale(CodeOperation.RCV);}

"LDI"
{return new UniteLexicale(CodeOperation.LDI);}

"STI"
{return new UniteLexicale(CodeOperation.STI);}

"HLT"
{return new UniteLexicale(CodeOperation.HLT);}

"LDC"
{return new UniteLexicale(CodeOperation.LDC);}

"INC"
{return new UniteLexicale(CodeOperation.INC);}

"DEC"
{return new UniteLexicale(CodeOperation.DEC);}

"GBL"
{return new UniteLexicale(CodeOperation.GBL);}

"LDL"
{return new UniteLexicale(CodeOperation.LDL);}

"STL"
{return new UniteLexicale(CodeOperation.STL);}

"LDG"
{return new UniteLexicale(CodeOperation.LDG);}

"STG"
{return new UniteLexicale(CodeOperation.STG);}

"LLA"
{return new UniteLexicale(CodeOperation.LLA);}

"LGA"
{return new UniteLexicale(CodeOperation.LGA);}

"JMP"
{return new UniteLexicale(CodeOperation.JMP);}

"JGZ"
{return new UniteLexicale(CodeOperation.JGZ);}

"JNZ"
{return new UniteLexicale(CodeOperation.JNZ);}

"JSR"
{return new UniteLexicale(CodeOperation.JSR);}

. 
{return new UniteLexicale(TypeUniteLexicale.ERROR,yytext());}

