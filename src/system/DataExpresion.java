package system;

import java.util.ArrayList;

public class DataExpresion {

    /*
        Información:
        Clase exclusiva para el almacenamiento de las expresiones regulares
        del compilador, el cual aloja en su constructor la expresion, prefijo y 
        contador de indice de cada expresion.
    */
    
    private ArrayList<String> expresion = new ArrayList<String>();
    private ArrayList<String> pre = new ArrayList<String>();
    private ArrayList<Integer> contador = new ArrayList<Integer>();
    private int totalExpresiones = 10;

    public DataExpresion() {
        this.expresion.add("(&&)|(\\|\\|)$");
        this.expresion.add("[( ) { } , ;]$");
        this.expresion.add("^[-|*|/|+|%]$");
        this.expresion.add("(<)|(<=)|(>)|(>=)|(==)|(!=)$");
        this.expresion.add("^[=]$");
        this.expresion.add("^[A][0-9]{1,}[V][a-z][/]$");
        this.expresion.add("^(-?)(\\d+)22$");
        this.expresion.add("^[0-9]{1,}22\\.[0-9]{1,}$");
        this.expresion.add("(cadena-|flotante-|entero-)$");
        this.expresion.add("^(if)|(else)$");

        this.pre.add("SEP");
        this.pre.add("DEL");
        this.pre.add("OA");
        this.pre.add("OR");
        this.pre.add("AS");
        this.pre.add("ID");
        this.pre.add("CNE");
        this.pre.add("CNPF");
        this.pre.add("TD");
        this.pre.add("IC");

        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
        this.contador.add(-1);
    }

    public ArrayList<String> getListaExpresion() {
        return this.expresion;
    }

    public ArrayList<String> getListaListaPrefijo() {
        return this.pre;
    }

    public ArrayList<Integer> getListaContador() {
        return this.contador;
    }

    public String getExpresion(int posicionRegular) {
        return this.expresion.get(posicionRegular);
    }

    public String getPrefijo(int posicionRegular) {
        return this.pre.get(posicionRegular);
    }

    public int getContador(int posicionRegular) {
        return this.contador.get(posicionRegular);
    }

    public int getTotalExpresiones() {
        return this.totalExpresiones;
    }

    public void addContReg(int posicionRegular) {
        this.contador.set(posicionRegular, this.contador.get(posicionRegular) + 1);
    }
}
