/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author casa
 */
public enum TokenClasses {
    identificador(2, "identificador"),
    constante_inteira(3, "constante_inteira"),
    constante_real(4, "constante_real"),
    constante_caractere(5, "constante_caractere"),
    comentario_linha(6, "comentario_linha"),
    comentario_bloco(7, "comentario_bloco"),
    simbolos_especial(8, "simbolos_especial"),
    palavras_reservada(27 , "palavras_reservada");
    
    private int cod;
    private String name;

    private TokenClasses(int cod, String name) {
        this.cod = cod;
        this.name = name;
    }
    
    public int getCode() {
        return this.cod;
    }
    
    public String getMessage() {
        return this.name;
    }
    
    public static TokenClasses getTokenByNumber(int numberCode) {
        TokenClasses tokenResult = null;
        for (TokenClasses token : TokenClasses.values()) {
            if (token.getCode() <= numberCode) {
                tokenResult = token;
            }
        }
        return tokenResult;
    }
    
    

}
