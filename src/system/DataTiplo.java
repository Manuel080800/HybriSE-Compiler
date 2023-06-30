package system;

import java.util.ArrayList;

public class DataTiplo {

    /*
        Información:
        Clase destinada exclusivamente para el almacenamieno de codigo intermedio
        llamado triplo. Este almacena el renglon, el dato objeto, dato fuente, operador y
        una etiqueta auxiliar para el control de llaves.
    */
    
    ArrayList<Integer> ID = new ArrayList<Integer>();
    ArrayList<Object> DO = new ArrayList<Object>();
    ArrayList<Object> DF = new ArrayList<Object>();
    ArrayList<Object> OP = new ArrayList<Object>();
    ArrayList<Object> ETQ = new ArrayList<Object>();
    int totalTiplo = 1;

    public DataTiplo() {
        this.ID.add(0);
        this.DO.add("Dato objeto:");
        this.DF.add("Dato fuente:");
        this.OP.add("Operador:");
        this.ETQ.add("Etiqueta");
    }

    // Agrega un elemnto de triplo a al estructura de la misma.
    public void addTiplo(String datoObjeto, String datoFuente, String operador, String etiqueta) {
        this.ID.add(this.totalTiplo);
        this.DO.add(datoObjeto);
        this.DF.add(datoFuente);
        this.OP.add(operador);
        this.ETQ.add(etiqueta);
        this.totalTiplo++;
    }

    public ArrayList<Integer> recuperarListaTiploID() {
        return this.ID;
    }

    public ArrayList<Object> recuperarListaTiploDO() {
        return this.DO;
    }

    public ArrayList<Object> recuperarListaTiploDF() {
        return this.DF;
    }

    public ArrayList<Object> recuperarListaTiploOP() {
        return this.OP;
    }

    public ArrayList<Object> recuperarListaTiploETQ() {
        return this.ETQ;
    }

    public int recuperarTiploID(int posicion) {
        return this.ID.get(posicion);
    }

    public String recuperarTiploDO(int posicion) {
        return this.DO.get(posicion).toString();
    }

    public String recuperarTiploDF(int posicion) {
        return this.DF.get(posicion).toString();
    }

    public String recuperarTiploOP(int posicion) {
        return this.OP.get(posicion).toString();
    }

    public String recuperarTiploETQ(int posicion) {
        return this.ETQ.get(posicion).toString();
    }

    public int recuperarsizeTiplo() {
        return this.totalTiplo;
    }

    // Encargada de generar una estructura cuadrada de los elementos del triplo
    public String formartPrintF() {
        String formart = "";
        int MDO = 0;
        int MDF = 0;
        int MO = 0;
        int sizeTiplo = 0;

        for (int i = 0; i < totalTiplo; i++) {
            if (MDO <= this.DO.get(i).toString().length()) {
                MDO = this.DO.get(i).toString().length();
            }
            if (MDF <= this.DF.get(i).toString().length()) {
                MDF = this.DF.get(i).toString().length();
            }
            if (MO <= this.OP.get(i).toString().length()) {
                MO = this.OP.get(i).toString().length();
            }
        }

        sizeTiplo = String.valueOf(this.totalTiplo - 1).length();
        formart += printF("", ' ', sizeTiplo) + "   " + printF(this.DO.get(0).toString(), ' ', MDO) + "   " + printF(this.DF.get(0).toString(), ' ', MDF) + "   " + printF(this.OP.get(0).toString(), ' ', MO);

        for (int i = 1; i < totalTiplo; i++) {
            formart += "\n" + printF(String.valueOf(i), ' ', sizeTiplo) + "   " + printF(this.DO.get(i).toString(), ' ', MDO) + "   " + printF(this.DF.get(i).toString(), ' ', MDF) + "   " + printF(this.OP.get(i).toString(), ' ', MO);
        }

        return formart;
    }
    
    public String formartPrintFF() {
        String formart = "";
        int MDO = 0;
        int MDF = 0;
        int MO = 0;
        int E = 0;
        int sizeTiplo = 0;

        for (int i = 0; i < totalTiplo; i++) {
            if (MDO <= this.DO.get(i).toString().length()) {
                MDO = this.DO.get(i).toString().length();
            }
            if (MDF <= this.DF.get(i).toString().length()) {
                MDF = this.DF.get(i).toString().length();
            }
            if (MO <= this.OP.get(i).toString().length()) {
                MO = this.OP.get(i).toString().length();
            }
            if (E <= this.OP.get(i).toString().length()) {
                E = this.OP.get(i).toString().length();
            }
        }

        sizeTiplo = String.valueOf(this.totalTiplo - 1).length();
        formart += printF("", ' ', sizeTiplo) + "   " + printF(this.DO.get(0).toString(), ' ', MDO) + "   " + printF(this.DF.get(0).toString(), ' ', MDF) + "   " + printF(this.OP.get(0).toString(), ' ', MO) + printF(this.ETQ.get(0).toString(), ' ', E);

        for (int i = 1; i < totalTiplo; i++) {
            formart += "\n" + printF(String.valueOf(i), ' ', sizeTiplo) + "   " + printF(this.DO.get(i).toString(), ' ', MDO) + "   " + printF(this.DF.get(i).toString(), ' ', MDF) + "   " + printF(this.OP.get(i).toString(), ' ', MO) + printF(this.ETQ.get(i).toString(), ' ', E);
        }

        return formart;
    }

    // Encargado de designar los tamaños especificos para cada elemento del triplo:
    public String printF(String contenidoPalabra, char contenidoComplemento, int maximoComplemento) {
        if (contenidoPalabra.length() >= maximoComplemento) {
            return contenidoPalabra;
        }

        String palabra = "";

        for (int i = 0; i < (maximoComplemento - contenidoPalabra.length()); i++) {
            palabra += contenidoComplemento;
        }

        return contenidoPalabra + palabra;
    }
}
