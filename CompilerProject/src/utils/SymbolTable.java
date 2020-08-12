/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;

/**
 *
 * @author Computador
 */
public class SymbolTable {
    HashMap<String, Symbol> symbolTable;
    
    public SymbolTable() {
        this.symbolTable = new HashMap<>();
    }
    
    public void add(Symbol sym) {
        this.symbolTable.put(sym.getIdentification(), sym);
    }
    
    public void remove(Symbol sym) {
       this.symbolTable.remove(sym.getIdentification());
    }
    
    public Symbol get(String id) {
       return this.symbolTable.get(id);
    }
    
    public boolean has(String id) {
       return this.symbolTable.get(id) != null;
    }
  
}
