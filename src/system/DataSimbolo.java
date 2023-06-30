package system;

import java.util.ArrayList;

public class DataSimbolo {
    
    /*
        Información:
        Clase de almacenamiento de simbolos dependiendo de la expresion regular en cuestion.
        Este almacena el lexema, token y tipo de elemento.
    */

    private ArrayList<Object> lexema = new ArrayList<Object>();
    private ArrayList<Object> token = new ArrayList<Object>();
    private ArrayList<Object> tipo = new ArrayList<Object>();
    private int totalSimbolos = 0;

    // Método para la agregación de los simbolos; sin embargo, este valida la existencia del
    // simbolo para repetir duplicidad de los datos.
    public boolean addSimbolos(String lexema, String token) {
        if (this.encontrarLexena(lexema) != -1) {
            return false;
        } else {
            this.lexema.add(lexema);
            this.token.add(token);
            this.tipo.add("");
            this.totalSimbolos++;
            return true;
        }
    }

    // Encuentra un simbolo a partir del lexema
    public int encontrarLexena(String lexema) {
        for (int i = 0; i < this.lexema.size(); i++) {
            if (lexema.compareTo(this.lexema.get(i).toString()) == 0) {
                return i;
            }
        }

        return -1;
    }
    
    // Encuentra un simbolo a partir del token correspondiente
    public int encontrarToken(String token) {
        for (int i = 0; i < this.token.size(); i++) {
            if (token.compareTo(this.token.get(i).toString()) == 0) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<Object> getListaLexema() {
        return this.lexema;
    }

    public ArrayList<Object> getListaToken() {
        return this.token;
    }

    public ArrayList<Object> getListaTipo() {
        return this.tipo;
    }

    public String getLexema(int posSim) {
        return this.lexema.get(posSim).toString();
    }

    public String getToken(int posSim) {
        return this.token.get(posSim).toString();
    }

    public String getTipo(int posSim) {
        return this.tipo.get(posSim).toString();
    }

    public int getTotalSimbolos() {
        return this.totalSimbolos;
    }
}
