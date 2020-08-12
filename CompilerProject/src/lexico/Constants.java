package lexico;

public interface Constants extends ScannerConstants, ParserConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_identificador = 2;
    int t_constante_inteira = 3;
    int t_constante_real = 4;
    int t_constante_caractere = 5;
    int t_bool = 6;
    int t_consts = 7;
    int t_def = 8;
    int t_end = 9;
    int t_execute = 10;
    int t_false = 11;
    int t_float = 12;
    int t_int = 13;
    int t_get = 14;
    int t_ifFalse = 15;
    int t_ifTrue = 16;
    int t_input = 17;
    int t_print = 18;
    int t_println = 19;
    int t_set = 20;
    int t_str = 21;
    int t_true = 22;
    int t_types = 23;
    int t_var = 24;
    int t_whileFalse = 25;
    int t_whileTrue = 26;
    int t_TOKEN_27 = 27; //"("
    int t_TOKEN_28 = 28; //")"
    int t_TOKEN_29 = 29; //"="
    int t_TOKEN_30 = 30; //":"
    int t_TOKEN_31 = 31; //":="
    int t_TOKEN_32 = 32; //","
    int t_TOKEN_33 = 33; //"&&"
    int t_TOKEN_34 = 34; //"||"
    int t_TOKEN_35 = 35; //"!="
    int t_TOKEN_36 = 36; //"<"
    int t_TOKEN_37 = 37; //"<="
    int t_TOKEN_38 = 38; //">"
    int t_TOKEN_39 = 39; //">="
    int t_TOKEN_40 = 40; //"*"
    int t_TOKEN_41 = 41; //"+"
    int t_TOKEN_42 = 42; //"-"
    int t_TOKEN_43 = 43; //"!"
    int t_TOKEN_44 = 44; //"/"

}
