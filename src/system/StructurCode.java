package system;

import java.util.ArrayList;

public class StructurCode {
    
    /*
        Información:
        Clase destinada para obtener y almacenar las palabras con ubicación en
        la cadena de texto principal [RUDIMENTARIO] ~ STRING.SPLIT
    */

    private String contEdCod = "";
    private int longCoEd = 0;
    private ArrayList<String> eConEdCo = new ArrayList<String>();
    private ArrayList<Integer> startEst = new ArrayList<Integer>();
    private ArrayList<Integer> endEst = new ArrayList<Integer>();
    private int sizeEstructure = 0;

    private boolean analisisEstructura = false;

    public StructurCode(String contEdCod) {
        this.contEdCod = contEdCod;
        this.longCoEd = contEdCod.length();
    }
    
    // Método principal:
    // Este método recorre complemante la cadea de texto y posteriormente va creando subcadenas hasta crear un espacio o
    // salto de línea, tabulación, entre otros para almacenarlo en un arreglo que posteriormemte tendra en su paralelo su
    // ubicacion inicial y final.
    public void analizerCode(int lineaConteo) {
        if (this.analisisEstructura == false) {
            String textConcat = "";
            Boolean startEstr = false;

            for (int i = 0; i < this.contEdCod.length(); i++) {
                if (((i == 0) && (this.contEdCod.charAt(i) == '"')) || (((i > 0)) && (this.contEdCod.charAt(i - 1) == ' ') && (this.contEdCod.charAt(i) == '"'))) {
                    startEstr = true;

                    for (int j = i; j < this.contEdCod.length(); j++) {
                        if ((this.contEdCod.charAt(j) == '"' && i != j) || (j == (this.contEdCod.length() - 1))) {
                            if ((j + 1) < this.contEdCod.length()) {
                                if ((((this.contEdCod.charAt(j) == '"' && i != j)) && (this.contEdCod.charAt(j + 1) == ' '))) {
                                    textConcat += this.contEdCod.charAt(j);

                                    this.eConEdCo.add(textConcat);
                                    endEst.add(j + 1);
                                    textConcat = "";
                                    this.sizeEstructure++;
                                    i = (j + 1);
                                    break;
                                }
                            }

                            if (j == (this.contEdCod.length() - 1)) {
                                if (this.startEst.size() == this.endEst.size()) {
                                    startEst.add(j);
                                }

                                textConcat += this.contEdCod.charAt(j);

                                this.eConEdCo.add(textConcat);
                                endEst.add(j + 1);
                                textConcat = "";
                                this.sizeEstructure++;
                                i = (j + 1);
                                break;
                            }

                            textConcat += this.contEdCod.charAt(j);
                        } else {
                            textConcat += this.contEdCod.charAt(j);

                            if (startEstr == true) {
                                startEst.add(j);
                                startEstr = false;
                            }
                        }
                    }
                } else {
                    if (this.contEdCod.charAt(i) != ' ' && this.contEdCod.charAt(i) != '\t') {
                        if (startEstr == false) {
                            startEst.add(i);
                            startEstr = true;
                        }

                        textConcat += this.contEdCod.charAt(i);

                        if (i == (this.contEdCod.length() - 1)) {
                            this.eConEdCo.add(textConcat);
                            endEst.add(i + 1);
                            textConcat = "";
                            this.sizeEstructure++;
                            startEstr = false;
                        }
                    } else {
                        if (startEstr == true) {
                            this.eConEdCo.add(textConcat);
                            endEst.add(i);
                            textConcat = "";
                            this.sizeEstructure++;
                            startEstr = false;
                        }
                        
                        for (int j = i; j < this.contEdCod.length(); j++) {
                            if (this.contEdCod.charAt(j) != ' ' || this.contEdCod.charAt(j) != '\t') {
                                i = j;
                                break;
                            }
                        }
                    }
                }
            }

            this.analisisEstructura = true;
        }
    }

    public ArrayList<String> getListEstructure() {
        return this.eConEdCo;
    }

    public ArrayList<Integer> getListEstructureStart() {
        return this.startEst;
    }

    public ArrayList<Integer> getListEstructureEnd() {
        return this.endEst;
    }

    public String gettEstructureID(int posEstr) {
        return this.eConEdCo.get(posEstr);
    }

    public int getEstructureStartID(int posEstr) {
        return this.startEst.get(posEstr);
    }

    public int getEstructureEndID(int posEstr) {
        return this.endEst.get(posEstr);
    }

    public int getLongitudText() {
        return this.longCoEd;
    }

    public int getSizeStructure() {
        return sizeEstructure;
    }

    public void setSizeStructure(int sizeEstructure) {
        this.sizeEstructure = sizeEstructure;
    }
}
