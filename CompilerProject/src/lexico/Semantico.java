package lexico;

import java.util.ArrayList;
import java.util.Stack;
import utils.ClassType;
import utils.Symbol;
import utils.SymbolTable;
import utils.Types;
import utils.Compiler;

public class Semantico implements Constants
{
    Compiler compiler = new Compiler();
    
    public void executeAction(int action, Token token)	throws SemanticError
    {
        this.compiler.execute(action, token);
    }	
    

}
