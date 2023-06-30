package system;

import java.util.ArrayList;

public class IntermediatePreCode {
    
    /*
        Información:
        Clase de preprocesamiento de las palabras para generar el código intermedio, 
        este tiene el objetivo de recuperar la palabra, su token y línea especifica a la
        que pertenece.
    */

    DataExpresion DE;
    DataSimbolo DS;
    DataError DSE;
    AnalizerCode AC;

    ArrayList<ArrayList<Object>> iE = new ArrayList<ArrayList<Object>>();
    ArrayList<ArrayList<Object>> tE = new ArrayList<ArrayList<Object>>();
    ArrayList<ArrayList<Object>> lE = new ArrayList<ArrayList<Object>>();
    int tP = 0;

    public IntermediatePreCode(DataExpresion DE, DataSimbolo DS, DataError DSE, AnalizerCode AC) {
        this.DE = DE;
        this.DS = DS;
        this.DSE = DSE;
        this.AC = AC;
    }

    public ArrayList<ArrayList<Object>> getiE() {
        return iE;
    }

    public void setiE(ArrayList<ArrayList<Object>> iE) {
        this.iE = iE;
    }

    public ArrayList<ArrayList<Object>> gettE() {
        return tE;
    }

    public void settE(ArrayList<ArrayList<Object>> tE) {
        this.tE = tE;
    }

    public ArrayList<ArrayList<Object>> getlE() {
        return lE;
    }

    public void setlE(ArrayList<ArrayList<Object>> lE) {
        this.lE = lE;
    }

    public int gettP() {
        return tP;
    }

    public void settP(int tP) {
        this.tP = tP;
    }

    public void runCodeIntermediate() {
        codeIntermediate();
    }

    // Método principal:
    private void codeIntermediate() {
        for (int i = 0; i < this.AC.getSizeEstructura(); i++) {
            StructurCode estED = this.AC.getContenidoEstructura(i);
            ArrayList<Object> iE = new ArrayList<Object>();
            ArrayList<Object> tE = new ArrayList<Object>();
            ArrayList<Object> lE = new ArrayList<Object>();

            for (int j = 0; j < estED.getSizeStructure(); j++) {
                // Añade la palabra a la estructa interna
                iE.add(estED.gettEstructureID(j));

                // Busca el token de simbolo o error y lo agrega a la estructura interna
                if (this.DS.encontrarLexena(estED.gettEstructureID(j)) != -1) {
                    tE.add(this.DS.getToken(this.DS.encontrarLexena(estED.gettEstructureID(j))));
                } else {
                    tE.add(this.DSE.getTokenError(this.DSE.searchLeError(estED.gettEstructureID(j))));
                }
                
                // Añade el número de línea presente en el for por medio de i
                lE.add(i);
            }

            // Se agregan a las versiones globales de estas variables
            this.iE.add(iE);
            this.tE.add(tE);
            this.lE.add(lE);
            this.tP++;
        }
    }
}
