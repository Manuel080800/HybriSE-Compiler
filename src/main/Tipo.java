package main;

public enum Tipo {
    TD("(cadena-|flotante-|entero-)$"),
    OA("^[-|*|/|+|%]$"),
    OR("(<)|(<=)|(>)|(>=)|(==)|(!=)$"),
    IC("^(if)|(else)$"),
    DEL("[( ) { } , ;]$"),
    SEP("(&&)|(\\|\\|)$"),
    ID("^[A][0-9]{1,}[V][a-z][/]$"),
    CNE("^(-?)(\\d+)22$"),
    AS("^[=]$"),
    CNPF("^[0-9]{1,}22\\.[0-9]{1,}$");

    public final String patron;

    Tipo(String s) {
        this.patron = s;

    }
}
