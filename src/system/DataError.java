package system;

import java.util.ArrayList;

public class DataError {
    
    /*
        Información:
        Clase de almacenamiento de errores, este presenta el error, token error, linea de error y descripción.
    */

    ArrayList<Object> tERROR = new ArrayList<Object>();
    ArrayList<Object> lERROR = new ArrayList<Object>();
    ArrayList<Object> liERROR = new ArrayList<Object>();
    ArrayList<Object> dERROR = new ArrayList<Object>();
    int totalErrores = -1;

    // Añade el error a la estructura, sin embargo; antes de realizarlo 
    // este comprueba su existencia previamente.
    public boolean addError(String lERROR, String tERROR, String liERROR, String dERROR) {
        if (this.searchLeError(lERROR) != -1) {
            int posicion_Lexema = this.searchLeError(lERROR);
            String liERRORCompletada = existingData(liERROR, this.liERROR.get(posicion_Lexema).toString());
            this.liERROR.set(posicion_Lexema, liERRORCompletada);
            return false;
        } else {
            this.lERROR.add(lERROR);
            this.tERROR.add(tERROR);
            this.liERROR.add(liERROR);
            this.dERROR.add(dERROR);
            this.totalErrores++;
            return true;
        }
    }

    public ArrayList<Object> getListaLexemaError() {
        return this.lERROR;
    }

    public ArrayList<Object> getListaTokenError() {
        return this.tERROR;
    }

    public ArrayList<Object> getListaLineaError() {
        return this.liERROR;
    }

    public ArrayList<Object> getListaDescripcionError() {
        return this.dERROR;
    }

    // Busca errores a partir de su lexema
    public int searchLeError(String lERROR) {
        for (int i = 0; i < this.lERROR.size(); i++) {
            if (lERROR.compareTo(this.lERROR.get(i).toString()) == 0) {
                return i;
            }
        }

        return -1;
    }

    // Comprueba la existencia del error
    String existingData(String liERROR, String lineAfter) {
        if (lineAfter.indexOf(liERROR) == -1) {
            return lineAfter + "," + liERROR;
        } else {
            return lineAfter;
        }
    }

    public String getLexemaError(int posicionError) {
        return this.lERROR.get(posicionError).toString();
    }

    public String getTokenError(int posicionError) {
        return this.tERROR.get(posicionError).toString();
    }

    public String getLineaError(int posicionError) {
        return this.liERROR.get(posicionError).toString();
    }

    public String getDescripcionError(int posicionError) {
        return this.dERROR.get(posicionError).toString();
    }

    public int getTotalErrores() {
        return this.totalErrores;
    }
}
