/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Stack;
import lexico.Constants;
import lexico.SemanticError;
import lexico.Token;

/**
 *
 * @author apozzi
 */
public class Compiler {
    
    String operador;
    static StringBuilder codigo = new StringBuilder();
    Stack<Types> pilhaDeTipos = new Stack<>();
    Stack<String> pilhaDeRotulos = new Stack<>();
    ArrayList<String> listaIdentificadores = new ArrayList<>();
    SymbolTable tabelaDeSimbolos = new SymbolTable();
    Types tipoVariavel;
    
    Token actualToken;
    public void execute(int action, Token token)	throws SemanticError
    {
       actualToken = token;
       switch (action) {
            case 15:
                executar15();
                break;
            case 16:
                executar16();
                break;
            case 22:
                executar22(token);
                break;
            case 21:
                executar21(token);
                break;
            case 23:
                executar23();
                break;
            case 5:
                executar5(token);
                break;
            case 6:
                executar6(token);
                break;
            case 14:
                executar14();
                break;
            case 20:
                executar20(token);
                break;
            case 26:
                executar26();
                break;
            case 1:
                executar1Or2("+");
                break;
            case 2:
                executar1Or2("-");
                break;
            case 25: 
                executar25(token);
                break;
            case 3:
                executar3Or4("*");
                break;
            case 4:
                executar3Or4("/");
                break;
            case 24:
                executar24();
                break;
            case 7:
                executar7();
                break;
            case 8: 
                executar8();
                break;
            case 11: 
                executar11Or12("true");
                break;
            case 12:
                executar11Or12("false");
                break;
            case 13:
                executar13();
                break;
            case 9:
                executar9(token);
                break;
            case 10:
                executar10();
                break;
            case 17:
                executar17();
                break;
            case 18:
                executar18Or19("and");
                break;
            case 19:
                executar18Or19("or");
                break;
            case 27:
                executar27();
                break;
            case 28:
                executar28(token);
                break;
            case 29:
                executar29();
                break;
            case 30:
                executar30();
                break;
            case 31:
                executar31(token);
                break;
            case 32:
                executar32(token);
                break;
                
        }
    }	
    
    private void executar15() {
        codigo.append(".assembly extern mscorlib {}\n");
        codigo.append(".assembly _codigo_objeto{}\n");
        codigo.append(".module   _codigo_objeto.exe\n");
        codigo.append(".class public _UNICA{\n");
        codigo.append(".method static public void _principal() {\n");
        codigo.append(".entrypoint\n");
    }
    
    private void executar16() {
        codigo.append("ret\n");
        codigo.append("}\n");
        codigo.append("}\n");
    }
    
    private void executar22(Token token) {
        listaIdentificadores.add(token.getLexeme());
    }
    
    private void executar21(Token token) {
        if ("int".equals(token.getLexeme())) {
            tipoVariavel = Types.int64;
        }
        if ("float".equals(token.getLexeme())) {
            tipoVariavel = Types.float64;
        }
    }
    
    private void executar23() throws SemanticError {
        for (String ident : listaIdentificadores) {
            if (tabelaDeSimbolos.has(ident)) {
               throw new SemanticError(ident + " já declarado", actualToken.getPosition());
            } else {
              Symbol sym = new Symbol();
              sym.setIdentification(ident);
              sym.setClazz(ClassType.VAR);
              sym.setType(tipoVariavel);
              tabelaDeSimbolos.add(sym);
              codigo
              .append(".locals(" + tipoVariavel + " " + ident + ")\n");
            }
        }
        listaIdentificadores = new ArrayList<>();
    }
    
    private void executar14() {
        Types tipo = pilhaDeTipos.pop();
        if (Types.int64.equals(tipo)) {
           codigo.append("conv.i8\n"); 
        }
        codigo.append("call void [mscorlib]System.Console::Write(" + tipo.getTypeAsString() + ")\n");
    }
    
    private void executar20(Token token) {
        pilhaDeTipos.push(Types.string);
        codigo.append("ldstr ")
        .append(token.getLexeme())
        .append("\n");
    }
   
    
    private void executar5(Token token) {
        pilhaDeTipos.push(Types.int64);
        codigo.append("ldc.i8 ")
        .append(token.getLexeme())
        .append("\n");
        codigo.append("conv.r8\n");
    }
    
    private void executar6(Token token) {
        pilhaDeTipos.push(Types.float64);
        codigo.append("ldc.r8 ")
        .append(token.getLexeme())
        .append("\n");
    }
    
    private void executar26() throws SemanticError {
        String lastElement = listaIdentificadores.remove(listaIdentificadores.size() - 1);
        if (!tabelaDeSimbolos.has(lastElement)) {
            System.err.println(lastElement);
            throw new SemanticError(lastElement + " não declarado", actualToken.getPosition());
        }
        Types tipoid = tabelaDeSimbolos.get(lastElement).getType();
        Types tipoexp = pilhaDeTipos.pop();
        if (!tipoid.equals(tipoexp)) {
            System.err.println(tipoid);
            System.err.println(tipoexp);
            throw new SemanticError("tipo incompatível em comando de atribuição", actualToken.getPosition());
        }
        if (Types.int64.equals(tipoid)) {
            codigo.append("conv.i8\n");
        }
        codigo.append("stloc " + lastElement + "\n");
    }
    
    private void executar1Or2(String operation) throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        Types tipo2 = pilhaDeTipos.pop();
        verifyBinaryError(tipo1, tipo2);
        if (Types.float64.equals(tipo1) || Types.float64.equals(tipo2)) {
            pilhaDeTipos.push(Types.float64);
        } else {
            pilhaDeTipos.push(Types.int64);
        }
        if ("+".equals(operation)) {
          codigo.append("add\n");  
        }
        if ("-".equals(operation)) {
          codigo.append("sub\n");
        } 
    }
    
    private void executar3Or4(String operation) throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        Types tipo2 = pilhaDeTipos.pop();
        verifyBinaryError(tipo1, tipo2);
        if ("*".equals(operation)) {
            if (Types.float64.equals(tipo1) || Types.float64.equals(tipo2)) {
                pilhaDeTipos.push(Types.float64);
            } else {
                pilhaDeTipos.push(Types.int64);
            }
            codigo.append("mul\n");  
        }
        if ("/".equals(operation)) {
          pilhaDeTipos.push(Types.float64);
          codigo.append("div\n");
        } 
    }
    
    private void verifyBinaryError(Types tipo1, Types tipo2) throws SemanticError {
        boolean validType1 = Types.float64.equals(tipo1) || Types.int64.equals(tipo1);
        boolean validType2 = Types.float64.equals(tipo2) || Types.int64.equals(tipo2);
        if (!validType1 || !validType2) {
            throw new SemanticError("tipos incompatíveis em operação aritmética binária", actualToken.getPosition());
        }
    }
    
    private void executar2(Token token) {
        pilhaDeTipos.push(Types.float64);
        codigo.append("ldc.r8 ")
        .append(token.getLexeme())
        .append("\n");
    }
    
    private void executar25(Token token) throws SemanticError {
        String lexeme = token.getLexeme();
        if (!tabelaDeSimbolos.has(lexeme)) {
            throw new SemanticError(lexeme + " não declarado", actualToken.getPosition());
        }
        Symbol symbol = tabelaDeSimbolos.get(lexeme);
        if (symbol.getClazz().equals(ClassType.CONST)) {
            throw new SemanticError("não é possivel fazer atribuição de constantes", actualToken.getPosition());
        }
        Types tipoid = symbol.getType();
        pilhaDeTipos.push(tipoid);
        codigo.append("ldloc " + lexeme + "\n");
        if (Types.int64.equals(tipoid)) {
            codigo.append("conv.r8\n");
        }
    }
    
    private void executar24() throws SemanticError {
        for (String listaIdentificador : listaIdentificadores) {
            if (!tabelaDeSimbolos.has(listaIdentificador)) {
                System.err.println(listaIdentificador);
                throw new SemanticError(listaIdentificador + " não declarado", actualToken.getPosition());
            } 
            Types tipoid = tabelaDeSimbolos.get(listaIdentificador).getType();
            String classe = "";
            if (Types.float64.equals(tipoid)) {
                classe = "Double";
            }
            if (Types.int64.equals(tipoid)) {
                classe = "Int64";
            }
            codigo.append("call string [mscorlib]System.Console::ReadLine()\n");
            codigo.append("call " + tipoid.getTypeAsString() + " [mscorlib]System." + classe + "::Parse(string)\n");
            codigo.append("stloc " + listaIdentificador + "\n");
        }
    }
    
    private void executar7() throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        if (Types.int64.equals(tipo1) || Types.float64.equals(tipo1)) {
            pilhaDeTipos.push(tipo1);
        } else {
          throw new SemanticError("tipo incompatível em operação aritmética unária", actualToken.getPosition());
        }
    }
    
    private void executar8() throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        if (Types.int64.equals(tipo1) || Types.float64.equals(tipo1)) {
            pilhaDeTipos.push(tipo1);
        } else {
           throw new SemanticError("tipo incompatível em operação aritmética unária", actualToken.getPosition());
        }
        codigo.append("ldc.i8 -1\n");
        if (Types.int64.equals(tipo1)) {
            codigo.append("conv.r8\n");
        }
        codigo.append("mul\n");
    }
    
    private void executar11Or12(String operator) {
        pilhaDeTipos.push(Types.bool);
        if ("true".equals(operator)) {
            codigo.append("ldc.i4 1");
        }
        if ("false".equals(operator)) {
            codigo.append("ldc.i4 0");
        }
        codigo.append("\n");
        codigo.append("conv.r8\n");
    }
    
    private void executar13() throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        pilhaDeTipos.push(Types.bool);
        if (Types.bool.equals(tipo1)) {
            codigo.append("ldc.i4 1\n");
            codigo.append("xor\n");
        } else {
            throw new SemanticError("tipo incompatível em operação lógica unária", actualToken.getPosition());
        }
    }
    
    private void executar9(Token token) {
        operador = token.getLexeme();
    }
    
    private void executar10() throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        Types tipo2 = pilhaDeTipos.pop();
        relationalOperatorError(tipo1, tipo2);
        pilhaDeTipos.push(Types.bool);
        switch (operador) {
            case ">":
                codigo.append("cgt\n");
                break;
            case ">=":
                codigo.append("clt\n");
                codigo.append("ldc.i4.0\n");
                codigo.append("ceq\n");
                break;
            case "<":
                codigo.append("clt\n");
                break;
            case "<=":
                codigo.append("cgt\n");
                codigo.append("ldc.i4.0\n");
                codigo.append("ceq\n");
                break;
            case "=":
                codigo.append("ceq\n");
                break;
            case "!=":
                codigo.append("ceq\n");
                codigo.append("ldc.i4.0\n");
                codigo.append("ceq\n");
                break;
        }
    }
    
    private void relationalOperatorError(Types tipo1, Types tipo2) throws SemanticError {
        boolean integerType1 = Types.float64.equals(tipo1) || Types.int64.equals(tipo1);
        boolean integerType2 = Types.float64.equals(tipo2) || Types.int64.equals(tipo2);
        boolean stringType1 = Types.string.equals(tipo1);
        boolean stringType2 = Types.string.equals(tipo2);
        boolean boolType1 = Types.bool.equals(tipo1);
        boolean boolType2 = Types.bool.equals(tipo2);
        if ((integerType1 != integerType2) || (stringType1 != stringType2) || (boolType1 != boolType2)) {
            throw new SemanticError("tipos incompatíveis em operação relacional", actualToken.getPosition());
        }
    }
    
    private void executar17() {
        codigo.append("ldstr \"\\n\"\n");
        codigo.append("call void [mscorlib]System.Console::Write(string)\n");
    }
    
    private void executar18Or19(String operador) throws SemanticError {
        Types tipo1 = pilhaDeTipos.pop();
        Types tipo2 = pilhaDeTipos.pop();
        if (Types.bool.equals(tipo1) && Types.bool.equals(tipo2)) {
            pilhaDeTipos.push(Types.bool);
            if ("and".equals(operador)) {
                codigo.append("and\n");
            }
            if ("or".equals(operador)) {
                codigo.append("or\n");
            }
        } else {
            throw new SemanticError("tipos incompatíveis em operação lógica binária", actualToken.getPosition());
        }
    }
    
    private void executar27() {
        String loop = LoopRotulo.newLoop();
        pilhaDeRotulos.push(LoopRotulo.newLoop() + "End");
        pilhaDeRotulos.push(loop);
        codigo.append(loop + ":\n");
    }
    
    private void executar28(Token token) {
        String loop = pilhaDeRotulos.pop();
        String loopend = pilhaDeRotulos.pop();
        String lexema = token.getLexeme();
        pilhaDeRotulos.push(loopend);
        pilhaDeRotulos.push(loop);
        if ("ifTrue".equals(lexema)) {
            codigo.append("brfalse " + loopend + "\n");
        }
        if ("ifFalse".equals(lexema)) {
            codigo.append("brtrue " + loopend + "\n");
        }
        if ("whileTrue".equals(lexema)) {
            codigo.append("brfalse " + loopend + "\n");
        }
        if ("whileFalse".equals(lexema)) {
            codigo.append("brtrue " + loopend + "\n");
        }
    }
    
    private void executar29() {
        String loop = pilhaDeRotulos.pop();
        String loopEnd = pilhaDeRotulos.pop();
        codigo.append(loopEnd + ":\n");
    }
    
    private void executar30() {
        String loop = pilhaDeRotulos.pop();
        String loopEnd = pilhaDeRotulos.pop();
        String newloopEnd = LoopRotulo.newLoop();
        codigo.append("br " + newloopEnd + "\n");
        codigo.append(loopEnd + ":\n");
        pilhaDeRotulos.push(newloopEnd);
        pilhaDeRotulos.push(loopEnd);
    }
    
    private void executar31(Token token) {
        String loop = pilhaDeRotulos.pop();
        String loopEnd = pilhaDeRotulos.pop();
        codigo.append("br " + loop + "\n");
        codigo.append(loopEnd + ":\n");
    }
    
    private void executar32(Token token) throws SemanticError {
        String lexema = token.getLexeme();
        System.out.println(token.getId());
        System.out.println(lexema);
        if (token.getId() == Constants.t_constante_caractere) {
          tipoVariavel = Types.string;
        } else
        if (token.getId() == Constants.t_constante_inteira) {
          tipoVariavel = Types.int64;
        } else 
        if (token.getId() == Constants.t_constante_real) {
          tipoVariavel = Types.float64;
        } else
        if (token.getId() == Constants.t_false || token.getId() == Constants.t_true) {
          tipoVariavel = Types.bool;
        } else {
            throw new SemanticError("Não foi possivel fazer atribuiçäo da constante", actualToken.getPosition());
        }
        for (String ident : listaIdentificadores) {         
            if (tabelaDeSimbolos.has(ident)) {
               throw new SemanticError(ident + " já declarado");
            } else {
              Symbol sym = new Symbol();
              sym.setIdentification(ident);
              sym.setClazz(ClassType.CONST);
              sym.setType(tipoVariavel);
              tabelaDeSimbolos.add(sym);
              codigo
              .append(".locals(" + tipoVariavel + " " + ident + ")\n");
              if (Types.string.equals(tipoVariavel)) {
                codigo.append("ldstr ")
                .append(lexema)
                .append("\n");
              }
              if (Types.int64.equals(tipoVariavel)) {
                codigo.append("ldc.i8 ")
                .append(lexema)
                .append("\n");
              }
              if (Types.float64.equals(tipoVariavel)) {
                codigo.append("ldc.r8 ")
                .append(lexema)
                .append("\n");
              }
              if (Types.bool.equals(tipoVariavel)) {
                  codigo.append("ldc.i4 ");
                  if (token.getId() == Constants.t_true) {
                      codigo.append("1");
                  } else {
                      codigo.append("0");
                  }
                  codigo.append("\n");
                  codigo.append("conv.r8\n");
              }
              codigo.append("stloc " + ident + "\n");
            }
        }
        listaIdentificadores = new ArrayList<>();
    }
   
    
    public static String getCode() {
        return codigo.toString();
    }
    
    public static void clean() {
        codigo = new StringBuilder();
    }
    
}
