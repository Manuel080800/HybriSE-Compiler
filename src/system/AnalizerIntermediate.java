package system;

import java.util.ArrayList;

public class AnalizerIntermediate {
    
    /*
        Información:
        Clase intermediaria para el análisis del código intermedio, este requiere
        de tres clases componente para la recuperación de tokens, creación de 
        precódigo y escritura del tiplo.
    */

    DataExpresion DE;
    DataSimbolo DS;
    DataError DSE;
    DataTiplo DT;
    AnalizerCode AC;

    ArrayList<ArrayList<Object>> iE = new ArrayList<ArrayList<Object>>();
    ArrayList<ArrayList<Object>> tE = new ArrayList<ArrayList<Object>>();
    ArrayList<ArrayList<Object>> lE = new ArrayList<ArrayList<Object>>();
    int tP = 0;

    ArrayList<ArrayList<Object>> cI = new ArrayList<ArrayList<Object>>();
    ArrayList<Integer> ifStart = new ArrayList<Integer>();
    ArrayList<Integer> ifID = new ArrayList<Integer>();
    ArrayList<Integer> ifP = new ArrayList<Integer>();
    String jB1 = "";
    String jB2 = "";
    boolean startB = false;

    public AnalizerIntermediate(DataExpresion DE, DataSimbolo DS, DataError DSE, AnalizerCode AC, DataTiplo DT) {
        this.DE = DE;
        this.DS = DS;
        this.DSE = DSE;
        this.AC = AC;
        this.DT = DT;
    }

    public void generateCode() {
        
        // Obtiene los tokens y lo almacena sus arreglos adicionalmente con el numero de línea.
        IntermediatePreCode preCode = new IntermediatePreCode(DE, DS, DSE, AC);
        preCode.runCodeIntermediate();
        iE = preCode.getiE();
        tE = preCode.gettE();
        lE = preCode.getlE();
        tP = preCode.gettP();

        // Crea la estructura de precódigo de acuerdo al proceso anterior.
        IntermediateCode code = new IntermediateCode(DE, DS, DSE, AC, iE, tE, lE, tP);
        code.processingPreCode();
        cI = code.getcI();
        ifStart = code.getIfStart();
        ifID = code.getIfID();
        ifP = code.getIfP();
        jB1 = code.getjB1();
        jB2 = code.getjB2();
        startB = code.isStartB();
        
        // Clase final para la escritura del triplo.
        IntermediatePosCode posCode = new IntermediatePosCode(DE, DS, DSE, AC, iE, tE, lE, tP, cI, ifStart, ifID, ifP, jB1, jB2, startB, DT);
        posCode.addTiploCode();
        
        // NOTA. Las variables recuperadas conforman parte de los elementos de la etapa siguiente.
    }
}
