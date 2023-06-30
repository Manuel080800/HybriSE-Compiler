package system;

import java.util.ArrayList;

public class IntermediateCode {
    
    /*
        Información:
        Clase importante del código intermedio ya que genera un precódigo intermedio
        para la agregación de esta en la estructura de triplos. Es importante generar el precodigo
        ya que permite obtener instrucciónes fiables para el codigo intermedio o de triplo
        antes de agregarlo a dicha estructura.
    */

    DataExpresion DE;
    DataSimbolo DS;
    DataError DSE;
    AnalizerCode AC;
    int identificador = 1;

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

    public IntermediateCode(DataExpresion DE, DataSimbolo DS, DataError DSE, AnalizerCode AC, ArrayList<ArrayList<Object>> iE, ArrayList<ArrayList<Object>> tE, ArrayList<ArrayList<Object>> lE, int tP) {
        this.DE = DE;
        this.DS = DS;
        this.DSE = DSE;
        this.AC = AC;
        this.iE = iE;
        this.tE = tE;
        this.lE = lE;
        this.tP = tP;
    }

    public String getjB1() {
        return jB1;
    }

    public String getjB2() {
        return jB2;
    }

    public boolean isStartB() {
        return startB;
    }

    public ArrayList<ArrayList<Object>> getcI() {
        return cI;
    }

    public ArrayList<Integer> getIfStart() {
        return ifStart;
    }

    public ArrayList<Integer> getIfID() {
        return ifID;
    }

    public ArrayList<Integer> getIfP() {
        return ifP;
    }

    // Método principal:
    // Ejecuta todos los precodificadores sobre la misma linea de contenido de codigo
    // del usuario
    public void processingPreCode() {
        for (int i = 0; i < tP; i++) {
            preCodeCondicion(iE.get(i), tE.get(i));
            preCodeBloques(iE.get(i), tE.get(i));
            preCodeContrario(iE.get(i), tE.get(i));
            preCodeAsignaciones(iE.get(i), tE.get(i));
        }
    }

    // Encargado de generar el código intermedio de las asignaciónes y operaciones aritmeticas.
    // Este coloca una cabecera con el nombre de identificación en [0] y posteriormente
    // agrega el dato de origen de ultimo y en su cuerpo los restantes elementos.
    boolean preCodeAsignaciones(ArrayList<Object> contED, ArrayList<Object> contTO) {
        String inicializacion = "";
        ArrayList<Object> opr = new ArrayList<Object>();

        boolean contOP = false;
        boolean initA = false;
        boolean contAS = false;

        for (int i = 0; i < contED.size(); i++) {
            if (initA == false) {
                if (contTO.get(i).toString().indexOf("ID") != -1 || contTO.get(i).toString().indexOf("ERL") != -1) {
                    opr.add("Asignacion");
                    contAS = true;
                    initA = true;
                    inicializacion = contED.get(i).toString();

                    if ((i + 1) < contED.size()) {
                        i++;
                    }
                }
            }

            if (contAS == true) {
                if (contED.get(i).toString().compareTo("=") == 0) {
                    contAS = false;
                } else {
                    inicializacion = "";
                    contOP = false;
                    initA = false;
                    contAS = false;
                    opr.clear();
                    return false;
                }
            } else {
                if (initA == true) {
                    if (contTO.get(i).toString().indexOf("ID") != -1 || contTO.get(i).toString().indexOf("CNE") != -1 || contTO.get(i).toString().indexOf("CNPF") != -1
                            || contTO.get(i).toString().indexOf("ERL") != -1) {
                        if (contOP == false) {
                            contOP = true;
                            opr.add(contED.get(i));
                        } else {
                            inicializacion = "";
                            contOP = false;
                            initA = false;
                            contAS = false;
                            opr.clear();
                            return false;
                        }
                    } else {
                        if (contOP == true) {
                            if (contTO.get(i).toString().indexOf("OA") != -1) {
                                contOP = false;
                                opr.add(contED.get(i));
                            } else {
                                if (contED.get(i).toString().compareTo(";") == 0) {
                                    opr.add(inicializacion);
                                    this.cI.add(opr);
                                    return true;
                                } else {
                                    inicializacion = "";
                                    contOP = false;
                                    initA = false;
                                    contAS = false;
                                    opr.clear();
                                    return false;
                                }
                            }
                        } else {
                            inicializacion = "";
                            contOP = false;
                            initA = false;
                            contAS = false;
                            opr.clear();
                            return false;
                        }
                    }
                }
            }
        }

        return false;
    }

    // Encargado de generar el código intermedio de las las condiciones con operaciones simples o compuestas.
    // Este coloca una cabecera con el nombre de identificación en [0] y posteriormente
    // agrega el dato de origen de ultimo del elemento bloque y en su cuerpo los restantes elementos de condicion
    // como la aritmetica o comparaciones de tipo compuesto o simple, ademas de detectar las operaciones lógicas.
    boolean preCodeCondicion(ArrayList<Object> contED, ArrayList<Object> contTO) {
        ArrayList<Object> opr = new ArrayList<Object>();
        ArrayList<Object> oprRelacionales = new ArrayList<Object>();
        boolean contR = false;
        boolean initA = false;
        boolean contP = false;
        boolean permLo = false;
        int contRR = 0;
        int contRL = 0;

        for (int i = 0; i < contED.size(); i++) {
            if (initA == false) {
                if (contTO.get(i).toString().indexOf("IC") != -1) {
                    opr.add("Condicion");
                    contP = true;
                    initA = true;

                    if ((i + 1) < contED.size()) {
                        i++;
                    }
                }
            }

            if (contP == true) {
                if (contED.get(i).toString().compareTo("(") == 0) {
                    contP = false;
                    opr.add("Temporal 1");
                } else {
                    contR = false;
                    initA = false;
                    contP = false;
                    permLo = false;
                    contRR = 0;
                    contRL = 0;
                    opr.clear();
                    return false;
                }
            } else {
                if (initA == true) {
                    if (contTO.get(i).toString().indexOf("ID") != -1 || contTO.get(i).toString().indexOf("CNE") != -1 || contTO.get(i).toString().indexOf("CNPF") != -1
                            || contTO.get(i).toString().indexOf("ERL") != -1) {
                        if (contR == false) {
                            contR = true;
                            opr.add(contED.get(i));
                        } else {
                            contR = false;
                            initA = false;
                            contP = false;
                            permLo = false;
                            contRR = 0;
                            contRL = 0;
                            opr.clear();
                            return false;
                        }
                    } else {
                        if (contR == true) {
                            if (contTO.get(i).toString().indexOf("OR") != -1) {
                                contR = false;
                                oprRelacionales.add(contED.get(i));
                                contRR++;
                                opr.add("Temporal 2");
                            } else {
                                if (contED.get(i).toString().compareTo(")") == 0 && ((contRR == 1 && contRL == 0) || (contRR > 0 && contRL == (contRR - 1)))) {
                                    try {
                                        opr.add(oprRelacionales.get(contRR - 1));
                                        this.cI.add(opr);
                                        return true;
                                    } catch (Exception ex) {

                                    }
                                } else {
                                    if (contTO.get(i).toString().indexOf("SEP") != -1 && permLo == false) {
                                        try {
                                            opr.add(oprRelacionales.get(contRR - 1));
                                        } catch (Exception ex) {

                                        }

                                        if (contED.get(i).toString().compareTo("||") == 0) {
                                            opr.add("Or");
                                            opr.add("Temporal 1");
                                        } else {
                                            opr.add("And");
                                            opr.add("Temporal 1");
                                        }

                                        contR = false;
                                        contRL++;
                                    } else {
                                        if (contTO.get(i).toString().indexOf("OA") != -1) {
                                            opr.add(contED.get(i));
                                            contR = false;
                                        } else {
                                            contR = false;
                                            initA = false;
                                            contP = false;
                                            permLo = false;
                                            contRR = 0;
                                            contRL = 0;
                                            opr.clear();
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else {
                            contR = false;
                            initA = false;
                            contP = false;
                            permLo = false;
                            contRR = 0;
                            contRL = 0;
                            opr.clear();
                            return false;
                        }
                    }
                }
            }
        }

        return false;
    }

    // Encargado de generar el código intermedio del else.
    // Este coloca una cabecera con el nombre de identificación en [0]
    boolean preCodeContrario(ArrayList<Object> contED, ArrayList<Object> contTO) {
        ArrayList<Object> opr = new ArrayList<Object>();

        for (int i = 0; i < contED.size(); i++) {
            if (contED.get(i).toString().compareTo("else") == 0) {
                opr.add("Contrario");
                cI.add(opr);
                return true;
            }
        }

        return false;
    }
    
    // Encargado de generar el código intermedio de las llaves delimitadoras.
    // Este coloca una cabecera con el nombre de identificación en [0].
    boolean preCodeBloques(ArrayList<Object> contED, ArrayList<Object> contTO) {
        ArrayList<Object> opr = new ArrayList<Object>();

        for (int i = 0; i < contED.size(); i++) {
            if (contED.get(i).toString().compareTo("{") == 0) {
                opr.add("Bloque 1");
                cI.add(opr);
                return true;
            }

            if (contED.get(i).toString().compareTo("}") == 0) {
                opr.add("Bloque 2");
                cI.add(opr);
                return true;
            }
        }

        return false;
    }
}
