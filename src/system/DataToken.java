package system;

import java.util.ArrayList;

public class DataToken {
    
    /*
        Información:
        Clase encargada de almacenar el archivo de tokens presente en el compilador,
        este solo almacena la traducción de cada palabra pero en tokens.
    */

    private ArrayList<Object> line = new ArrayList<Object>();
    private String lineToken = "";
    private boolean start = true;
    private int sizeLine = 0;

    public DataToken() {

    }

    // Agrega los tokens a una sublinea de tokens en curso
    public void addToken(String contenido) {
        if (this.start == true) {
            this.lineToken += contenido;
            this.start = false;
        } else {
            this.lineToken += (" " + contenido);
        }
    }
    
    // Encargado de agregar los tokens escritos en la sublinea a la estructura de tokens
    public void registerToken() {
        this.line.add(this.lineToken);
        this.lineToken = "";
        this.start = true;
        this.sizeLine++;
    }

    public ArrayList<Object> getListaLineasContenidoToken() {
        return this.line;
    }

    public String getLineaContenidoTokenEscritas() {
        String lineTokenWrite = "";

        for (int i = 0; i < this.line.size(); i++) {
            if (i == 0) {
                lineTokenWrite += this.line.get(i).toString();
            } else {
                lineTokenWrite += "\n" + this.line.get(i).toString();
            }
        }

        return lineTokenWrite;
    }

    public String getLineContent() {
        return this.lineToken;
    }

    public int getSizeLineToken() {
        return this.sizeLine;
    }
}
