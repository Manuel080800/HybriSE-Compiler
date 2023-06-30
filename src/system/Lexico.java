package system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexico {
    
    /*
        Información:
        Clase especial encargada de ejecutar los elementos léxicos del compilador.
        Este tiene el objetivo de designar los tokens y almacenar los simbolos y errores
        en sus debidas estructuras.
    */

    DataExpresion DE = null;
    DataSimbolo DS = null;
    DataError DSE = null;
    DataToken DST = null;
    AnalizerCode AC = null;

    public Lexico(DataExpresion DE, DataSimbolo DS, DataError DSE, DataToken DST, AnalizerCode AC) {
        this.DE = DE;
        this.DS = DS;
        this.DSE = DSE;
        this.DST = DST;
        this.AC = AC;
    }
    
    // Método principal:
    public void runAnalizerLexica() {
        for (int i = 0; i < this.AC.getSizeEstructura(); i++) {
            StructurCode EE = this.AC.getContenidoEstructura(i);

            for (int j = 0; j < EE.getSizeStructure(); j++) {
                Boolean estructureFound = false;

                for (int a = 0; a < this.DE.getTotalExpresiones(); a++) {
                    Pattern expresion = Pattern.compile(this.DE.getExpresion(a));
                    Matcher palabra = expresion.matcher(EE.gettEstructureID(j));

                    // La palabra cumplio correctamente la expresión regular.
                    if (palabra.matches()) {
                        String tokenGenerado = this.DE.getPrefijo(a) + (this.DE.getContador(a) + 1);
                        
                        // Agrega un nuevo simbolo
                        if (this.DS.addSimbolos(EE.gettEstructureID(j), tokenGenerado) == true) {
                            this.DST.addToken(tokenGenerado);
                            this.DE.addContReg(a);
                        
                        // Recupera el token de la palabra para evitar su duplicidad
                        } else {
                            int posicionToken = this.DS.encontrarLexena(EE.gettEstructureID(j));
                            tokenGenerado = this.DS.getToken(posicionToken);
                            this.DST.addToken(tokenGenerado);
                        }

                        estructureFound = true;
                        System.err.println(i + "" + j);
                        break;
                    }
                }
                
                // Agrega la palabra a la estructura de errores:
                if (estructureFound == false) {
                    String tokenError = "ERL" + (this.DSE.getTotalErrores() + 1);

                    if (this.DSE.addError(EE.gettEstructureID(j), tokenError, String.valueOf(i + 1), "Error léxico")) {
                        this.DST.addToken(tokenError);
                    } else {
                        int posicionToken = this.DSE.searchLeError(EE.gettEstructureID(j));
                        tokenError = this.DSE.getTokenError(posicionToken);
                        this.DST.addToken(tokenError);
                    }
                    System.err.println(i + "" + j);
                }
            }

            DST.registerToken();
        }
    }
}
