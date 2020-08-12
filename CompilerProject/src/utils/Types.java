/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Computador
 */
public enum Types {
    bool("bool"),
    int64("int64"),
    float64("float64"),
    string("string");
    
    private String type;

    Types(String type) {
        this.type = type;
    }
    
    public String getTypeAsString() {
        return this.type;
    }
}
