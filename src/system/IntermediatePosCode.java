package system;

import java.util.ArrayList;

public class IntermediatePosCode {
    
    /*
        Información:
        Clase sumamente importante del código intermedio ya que emplea el precódigo intermedio
        anteriomente generado para la agregación de contenido en la estructura de triplos. 
        Este traduce el precodigo a instrucciones para la estructura de triplos.
    */

    DataExpresion DE;
    DataSimbolo DS;
    DataError DSE;
    AnalizerCode AC;
    DataTiplo DT;
    int identificador = 1;
    int nivel = 1;

    ArrayList<ArrayList<Object>> iE = new ArrayList<ArrayList<Object>>();
    ArrayList<ArrayList<Object>> tE = new ArrayList<ArrayList<Object>>();
    ArrayList<ArrayList<Object>> lE = new ArrayList<ArrayList<Object>>();
    int tP = 0;

    ArrayList<ArrayList<Object>> cI = new ArrayList<ArrayList<Object>>();
    ArrayList<Integer> ifStart = new ArrayList<Integer>();
    ArrayList<Integer> ifID = new ArrayList<Integer>();
    ArrayList<Integer> ifP = new ArrayList<Integer>();
    ArrayList<componentElse> elseC = new ArrayList<componentElse>();
    String jB1 = "";
    String jB2 = "";
    boolean startB = false;

    public IntermediatePosCode(DataExpresion DE, DataSimbolo DS, DataError DSE, AnalizerCode AC, ArrayList<ArrayList<Object>> iE, ArrayList<ArrayList<Object>> tE, ArrayList<ArrayList<Object>> lE, int tP, ArrayList<ArrayList<Object>> cI, ArrayList<Integer> ifStart, ArrayList<Integer> ifID, ArrayList<Integer> ifP, String jB1, String jB2, boolean startB, DataTiplo DT) {
        this.DE = DE;
        this.DS = DS;
        this.DSE = DSE;
        this.AC = AC;
        this.iE = iE;
        this.tE = tE;
        this.lE = lE;
        this.tP = tP;
        this.cI = cI;
        this.ifStart = ifStart;
        this.ifID = ifID;
        this.ifP = ifP;
        this.jB1 = jB1;
        this.jB2 = jB2;
        this.startB = startB;
        this.DT = DT;
    }

    // Método principal:
    // Ejecuta todos los triplicadores sobre la misma linea de contenido de codigo
    // del usuario
    void addTiploCode() {
        for (int i = 0; i < cI.size(); i++) {
            addTiploAsignacion(cI.get(i));
            addTiploCondicion(cI.get(i));
            addTiploContrario(cI.get(i));
            addTiploLlave(cI.get(i));
        }

        addTiploIdentacion();
        
        for (int i = 0; i < this.elseC.size(); i++) {
            System.out.println(elseC.get(i).getCondicion());
            System.out.println(elseC.get(i).getNivel());
            System.out.println(elseC.get(i).getPosiciones().get(0) + " - " + elseC.get(i).getPosiciones().get(1));
        }
    }

    // Encargado de agregar las lineas no existentes definidas en la columna de operador de
    // la estructa de triplos
    void addTiploIdentacion() {
        ArrayList<Integer> sinRepeticion = new ArrayList<Integer>();
        int contadorAdicciones = 0;

        for (int i = 1; i < DT.recuperarsizeTiplo(); i++) {
            try {
                int posicion = Integer.parseInt(DT.recuperarTiploOP(i));

                if (1 > posicion || posicion > (DT.recuperarsizeTiplo() - 1)) {
                    boolean encontrado = false;

                    for (int j = 0; j < sinRepeticion.size(); j++) {
                        if (sinRepeticion.get(j) == posicion) {
                            encontrado = true;
                        }
                    }

                    if (encontrado == false) {
                        sinRepeticion.add(posicion);
                        contadorAdicciones++;
                    }
                }
            } catch (Exception ex) {

            }
        }

        for (int i = 0; i < contadorAdicciones; i++) {
            DT.addTiplo("", "", "", "Adicional");
        }
    }

    // Encargado de modificar todas los True o False presente en la estructura de codigo intermedio
    // Este al encontrar una llave marcada en Bloque 1 o 2 avanza hacia las posiciones de marcado de las condiciones
    // y en donde estan exclusivamente estos elementos para posteriormente agregar los renglones de la columna de
    // operadores por el desconocimiento de este.
    void addTiploLlave(ArrayList<Object> instruccionesTiplo) {
        boolean inicial = false;
        boolean modalidad = false;
        boolean paridad = false;

        for (int a = 0; a < instruccionesTiplo.size(); a++) {
            if (a == 0) {
                if (instruccionesTiplo.get(a).toString().compareTo("Bloque 1") == 0) {
                    modalidad = false;
                    inicial = true;
                } else {
                    if (instruccionesTiplo.get(a).toString().compareTo("Bloque 2") == 0) {
                        modalidad = true;
                        inicial = true;
                    } else {
                        return;
                    }
                }
            }

            if (a == 0 && inicial == true) {
                if (modalidad == false) {
                    for (int j = 0; j < ifP.size(); j++) {
                        if (ifID.get(j) == (identificador - 1)) {
                            if (paridad == false) {
                                paridad = true;

                                int posicionTiplo = DT.recuperarsizeTiplo();

                                for (int i = 0; i < DT.recuperarsizeTiplo(); i++) {
                                    if (i == ifP.get(j) - 1) {
                                        if (DT.recuperarTiploDF(i).compareTo("True") == 0 && DT.recuperarTiploOP(i).compareTo("") == 0) {
                                            DT.recuperarListaTiploOP().set(i, posicionTiplo);
                                        }
                                    }
                                }
                            } else {
                                paridad = false;
                            }
                        }
                    }
                } else {
                    ArrayList<Integer> delete = new ArrayList<Integer>();

                    for (int j = 0; j < ifP.size(); j++) {
                        if (ifID.get(j) == (identificador - 1)) {
                            if (paridad == true) {
                                paridad = false;

                                int posicionTiplo = DT.recuperarsizeTiplo();

                                for (int i = 0; i < DT.recuperarsizeTiplo(); i++) {
                                    if (i == ifP.get(j) - 1) {
                                        delete.add(j - 1);
                                        delete.add(j);

                                        if (DT.recuperarTiploDF(i).compareTo("False") == 0 && DT.recuperarTiploOP(i).compareTo("") == 0) {
                                            DT.recuperarListaTiploOP().set(i, posicionTiplo);
                                        }
                                    }
                                }
                            } else {
                                paridad = true;
                            }
                        }
                    }

                    for (int o = delete.size() - 1; o >= 0; o--) {
                        ifID.remove((int) delete.get(o));
                        ifP.remove((int) delete.get(o));
                    }

                    if (delete.size() > 0) {
                        identificador--;
                    }

                    for (int i = DT.recuperarsizeTiplo() - 1; i > 0; i--) {
                        if (DT.recuperarTiploDF(i).compareTo("JMP") == 0 && DT.recuperarTiploOP(i).compareTo("") == 0) {
                            int posicionTiplo = DT.recuperarsizeTiplo();
                            DT.recuperarListaTiploOP().set(i, posicionTiplo);
                            return;
                        }
                    }
                }
            }
        }
    }

    // Encargado de agregar los else a la estructura de triplo.
    // Este encuentra la palabra Contrario y posteriormente recorre de forma inversa la estructura
    // hasta hayar el último False y actualizarle su renglon de destino en Operadores y finalmente
    // agregar un JMP para el salto respectivo de este.
    void addTiploContrario(ArrayList<Object> instruccionesTiplo) {
        boolean inicial = false;
        boolean modalidad = false;
        boolean paridad = false;

        for (int a = 0; a < instruccionesTiplo.size(); a++) {
            if (a == 0) {
                if (instruccionesTiplo.get(a).toString().compareTo("Contrario") == 0) {
                    inicial = true;
                } else {
                    return;
                }
            }

            if (a == 0 && inicial == true) {
//                for (int i = DT.recuperarsizeTiplo() - 1; i > 0; i--) {
//                    if (DT.recuperarTiploDF(i).compareTo("False") == 0 && DT.recuperarTiploOP(i).compareTo("") != 0) {
//                        int posicion = Integer.parseInt(DT.recuperarListaTiploOP().get(i).toString()) + 1;
//                        DT.recuperarListaTiploOP().set(i, posicion);
//                        DT.addTiplo("", "JMP", "", "Contrario");
//                        return;
//                    }
//                }
                DT.addTiplo("", "JMP", "", "Contrario");
                
                
                for (int e = DT.recuperarsizeTiplo() - 1; e > 0; e--) 
                {
                    if (DT.recuperarTiploDF(e).compareTo("False") == 0) 
                    {
                        int posicion = Integer.parseInt(DT.recuperarListaTiploOP().get(e).toString()) + 1;
                        DT.recuperarListaTiploOP().set(e, posicion);
                        System.out.println("CAMBIE EL VALOR " + posicion);
                        break;
                    }
                    System.out.println("Entre111221123" + " " + this.elseC.size());
                }
                
                for (int e = this.elseC.size() - 1; e >= 0; e --) 
                {
                    System.out.println("Entre111 " +  String.valueOf(this.nivel - 1));
                    if (this.elseC.get(e).getNivel().compareTo(String.valueOf(this.nivel - 1)) == 0)
                    {
//                        if (this.elseC.get(e).getCondicion().compareTo("Or") == 0)
//                        {
//                            int posicion = DT.recuperarsizeTiplo();
//                            DT.recuperarListaTiploOP().set((int) (this.elseC.get(e).getPosiciones().get(1) - 1), posicion);
//                            System.out.println("Entre11111111");
//                        }
                        
                        if (this.elseC.get(e).getCondicion().compareTo("And") == 0)
                        {
                            int posicion = DT.recuperarsizeTiplo();
                            DT.recuperarListaTiploOP().set((int) (this.elseC.get(e).getPosiciones().get(1) - 1), posicion);
                            System.out.println("Entre111222222");
                        }
                    }
                }
                
                deleteElse();
            }
        }
    }
    
    private void deleteElse ()
    {
        if (this.elseC.isEmpty())
        {
            return;
        }
        
        for (int e = this.elseC.size() - 1; e >= 0; e --) 
        {
            if (this.elseC.get(e).nivel.compareTo(String.valueOf(this.nivel)) == 0)
            {
                this.elseC.remove(e);
            }
        }
        
        this.nivel --;
    }

    // Encargado exclusivamente de agregar al triplo todo aquellas condiciones if, 
    // este detecta la estructura base del if y genera los elementos True y False necesarios
    // de acuerdo a las operaciones lógicas o si son de comparación simple o compuesta.
    void addTiploCondicion(ArrayList<Object> instruccionesTiplo) {
        boolean ciclo = false;
        boolean temporal = false;
        boolean oPS1 = true;
        boolean oPS2 = true;
        String cB1I = "";
        String cB2I = "";

        ArrayList<String> operation = new ArrayList<String>();

        for (int a = 0; a < instruccionesTiplo.size(); a++) {
            boolean interrupcionCondicion = false;

            if (a == 0) {
                if (instruccionesTiplo.get(a).toString().compareTo("Condicion") == 0) {
                    this.startB = true;
                    ciclo = true;
                    ifStart.add(DT.recuperarsizeTiplo());
                    deleteElse();
                } else {
                    return;
                }
            }

            if (instruccionesTiplo.get(a).toString().compareTo("Temporal 1") == 0) {
                temporal = false;

                oPS1 = true;
                cB1I = "";
                interrupcionCondicion = true;
            }

            if (instruccionesTiplo.get(a).toString().compareTo("Temporal 2") == 0) {
                temporal = true;

                oPS2 = true;
                cB2I = "";
                interrupcionCondicion = true;

                if (operation.size() > 0 && oPS1 == false) {
                    aritmetyProcessing(operation, false, false);
                } else {
                    this.cT++;
                    jB1 = "T" + this.cT;

                    if (this.startB == true) {
                        DT.addTiplo(jB1, cB1I, "=", "Condicion: Operando 1");
                        this.startB = false;
                    } else {
                        DT.addTiplo(jB1, cB1I, "=", "Operando 1");
                    }
                }
            }

            if (instruccionesTiplo.get(a).toString().compareTo("Or") == 0 || instruccionesTiplo.get(a).toString().compareTo("And") == 0 || (a + 1) == instruccionesTiplo.size()) {
                if ((a + 1) == instruccionesTiplo.size()) {
                    if (instruccionesTiplo.get(a).toString().compareTo("==") == 0 || instruccionesTiplo.get(a).toString().compareTo("<=") == 0 || instruccionesTiplo.get(a).toString().compareTo(">=") == 0 || instruccionesTiplo.get(a).toString().compareTo("!=") == 0 || instruccionesTiplo.get(a).toString().compareTo("<") == 0 || instruccionesTiplo.get(a).toString().compareTo(">") == 0) {
                        if (oPS1 == true && oPS2 == true) {
                            DT.addTiplo("T2", cB2I, "=", "Operando 2");
                            DT.addTiplo("T1", "T2", instruccionesTiplo.get(a).toString(), "Condicion");
                            jB1 = "";
                            jB2 = "";
                            cB1I = "";
                            cB2I = "";
                            oPS1 = true;
                            oPS2 = true;
                            this.cT = 0;
                        } else {
                            if (operation.size() > 0 && oPS2 == false) {
                                aritmetyProcessing(operation, false, true);
                            } else {
                                this.cT++;
                                jB2 = "T" + this.cT;
                                DT.addTiplo(jB2, cB2I, "=", "Operando 2");
                            }

                            DT.addTiplo(jB1, jB2, instruccionesTiplo.get(a).toString(), "Condicion");
                            jB1 = "";
                            jB2 = "";
                            cB1I = "";
                            cB2I = "";
                            oPS1 = true;
                            oPS2 = true;
                            this.cT = 0;
                        }
                    }
                    componentElse compElse = new componentElse();
                    ArrayList<Integer> pos = new ArrayList<Integer>();
                    
                    DT.addTiplo("TR1", "True", String.valueOf(DT.recuperarsizeTiplo() + 2), "Salto true");
                    ifP.add(DT.recuperarsizeTiplo());
                    pos.add(DT.recuperarsizeTiplo());
                    ifID.add(identificador);
                    DT.addTiplo("TR1", "False", "", "Salto false");
                    pos.add(DT.recuperarsizeTiplo());
                    ifP.add(DT.recuperarsizeTiplo());
                    ifID.add(identificador);
                    interrupcionCondicion = true;
                    identificador++;
                    compElse.addElse(String.valueOf(nivel), "Normal", pos);
                    this.elseC.add(compElse);
                } else {
                    if (instruccionesTiplo.get(a).toString().compareTo("And") == 0) {
                        jB1 = "";
                        jB2 = "";
                        cB1I = "";
                        cB2I = "";
                        oPS1 = true;
                        oPS2 = true;
                        this.cT = 0;
                        
                        componentElse compElse = new componentElse();
                        ArrayList<Integer> pos = new ArrayList<Integer>();

                        DT.addTiplo("TR1", "True", String.valueOf(DT.recuperarsizeTiplo() + 2), "Salto true");
                        ifP.add(DT.recuperarsizeTiplo());
                        pos.add(DT.recuperarsizeTiplo());
                        ifID.add(identificador);
                        DT.addTiplo("TR1", "False", "", "Salto false");
                        ifP.add(DT.recuperarsizeTiplo());
                        pos.add(DT.recuperarsizeTiplo());
                        ifID.add(identificador);
                        
                        compElse.addElse(String.valueOf(nivel), "And", pos);
                        this.elseC.add(compElse);
                    } else {
                        jB1 = "";
                        jB2 = "";
                        cB1I = "";
                        cB2I = "";
                        oPS1 = true;
                        oPS2 = true;
                        this.cT = 0;
                        
                        componentElse compElse = new componentElse();
                        ArrayList<Integer> pos = new ArrayList<Integer>();
                        
                        DT.addTiplo("TR1", "True", "", "Salto true");
                        ifP.add(DT.recuperarsizeTiplo());
                        pos.add(DT.recuperarsizeTiplo());
                        ifID.add(identificador);
                        DT.addTiplo("TR1", "False", String.valueOf(DT.recuperarsizeTiplo() + 1), "Salto false");
                        ifP.add(DT.recuperarsizeTiplo());
                        pos.add(DT.recuperarsizeTiplo());
                        ifID.add(identificador);
                        
                        compElse.addElse(String.valueOf(nivel), "Or", pos);
                        this.elseC.add(compElse);
                    }

                    interrupcionCondicion = true;
                }
            }

            if (a != 0 && ciclo == true && interrupcionCondicion == false) {
                if (temporal == false) {
                    if (instruccionesTiplo.get(a).toString().compareTo("+") == 0 || instruccionesTiplo.get(a).toString().compareTo("-") == 0 || instruccionesTiplo.get(a).toString().compareTo("*") == 0 || instruccionesTiplo.get(a).toString().compareTo("-") == 0 || instruccionesTiplo.get(a).toString().compareTo("%") == 0) {
                        operation.add(instruccionesTiplo.get(a).toString());
                        oPS1 = false;
                    } else {
                        operation.add(instruccionesTiplo.get(a).toString());

                        if (cB1I.compareTo("") == 0) {
                            cB1I = instruccionesTiplo.get(a).toString();
                        }
                    }
                } else {
                    if (instruccionesTiplo.get(a).toString().compareTo("==") == 0 || instruccionesTiplo.get(a).toString().compareTo("<=") == 0 || instruccionesTiplo.get(a).toString().compareTo(">=") == 0 || instruccionesTiplo.get(a).toString().compareTo("!=") == 0 || instruccionesTiplo.get(a).toString().compareTo("<") == 0 || instruccionesTiplo.get(a).toString().compareTo(">") == 0) {
                        if (oPS1 == true && oPS2 == true) {
                            DT.addTiplo("T2", cB2I, "=", "Operando 2");
                            DT.addTiplo("T1", "T2", instruccionesTiplo.get(a).toString(), "Condicion");
                            jB1 = "";
                            jB2 = "";
                            cB1I = "";
                            cB2I = "";
                            oPS1 = true;
                            oPS2 = true;
                            this.cT = 0;
                        } else {
                            if (operation.size() > 0 && oPS2 == false) {
                                aritmetyProcessing(operation, false, true);
                            } else {
                                this.cT++;
                                jB2 = "T" + this.cT;
                                DT.addTiplo(jB2, cB2I, "=", "Operando 2");
                            }

                            DT.addTiplo(jB1, jB2, instruccionesTiplo.get(a).toString(), "Condicion");
                            jB1 = "";
                            jB2 = "";
                            cB1I = "";
                            cB2I = "";
                            oPS1 = true;
                            oPS2 = true;
                            this.cT = 0;
                        }
                    } else {
                        if (instruccionesTiplo.get(a).toString().compareTo("+") == 0 || instruccionesTiplo.get(a).toString().compareTo("-") == 0 || instruccionesTiplo.get(a).toString().compareTo("*") == 0 || instruccionesTiplo.get(a).toString().compareTo("/") == 0 || instruccionesTiplo.get(a).toString().compareTo("%") == 0) {
                            operation.add(instruccionesTiplo.get(a).toString());
                            oPS2 = false;
                        } else {
                            operation.add(instruccionesTiplo.get(a).toString());

                            if (cB2I.compareTo("") == 0) {
                                cB2I = instruccionesTiplo.get(a).toString();
                            }
                        }
                    }
                }
            }
        }
        this.nivel ++;
    }

    ArrayList<String> operation = new ArrayList<String>();
    int cT = 0;
    String vP = "";

    // Encargado de traducir los elementos arimeticos de primer, segundo o mixto orden para posteriormente
    // escribirla en el triplo.
    void processingAritmetyJO(ArrayList<String> operation, boolean mode, boolean modetwo) {
        boolean primero = false;
        boolean inicio = true;

        for (int i = 1; i < operation.size() - 1; i++) {
            if (operation.get(i).equals("%") || operation.get(i).equals("/") || operation.get(i).equals("*")) {
                this.cT++;

                if (startB == true) {
                    if (mode == true) {
                        DT.addTiplo("T" + this.cT, operation.get(i - 1), "=", "Aritmetica");
                    }

                    if (mode == false) {
                        DT.addTiplo("T" + this.cT, operation.get(i - 1), "=", "Condicion");
                    }

                    startB = false;
                } else {
                    DT.addTiplo("T" + this.cT, operation.get(i - 1), "=", "");
                }

                if (mode == false) {
                    if (DT.recuperarTiploETQ(DT.recuperarListaTiploETQ().size() - 1).isEmpty() && inicio == true) {
                        if (modetwo == false) {
                            DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, "Operando 1");
                        } else {
                            DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, "Operando 2");
                        }

                        inicio = false;
                    } else {
                        if (inicio == true) {
                            if (modetwo == false) {
                                DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, DT.recuperarTiploETQ(DT.recuperarListaTiploETQ().size() - 1) + ": Operando 1");
                            } else {
                                DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, DT.recuperarTiploETQ(DT.recuperarListaTiploETQ().size() - 1) + ": Operando 2");
                            }

                            inicio = false;
                        }
                    }
                }

                DT.addTiplo("T" + this.cT, operation.get(i + 1), operation.get(i), "");

                operation.set(i - 1, "T" + cT);
                operation.remove(i + 1);
                operation.remove(i);
                i = 0;
            }
        }

        for (int i = 1; i < operation.size() - 1; i++) {
            if (operation.get(i).equals("+") || operation.get(i).equals("-")) {
                if (primero == false) {
                    this.cT++;

                    if (startB == true) {
                        if (mode == true) {
                            DT.addTiplo("T" + this.cT, operation.get(i - 1), "=", "Aritmetica");
                        }

                        if (mode == false) {
                            DT.addTiplo("T" + this.cT, operation.get(i - 1), "=", "Condicion");
                        }

                        startB = false;
                    } else {
                        DT.addTiplo("T" + this.cT, operation.get(i - 1), "=", "");
                    }

                    primero = true;
                }

                if (mode == false) {
                    if (DT.recuperarTiploETQ(DT.recuperarListaTiploETQ().size() - 1).isEmpty() && inicio == true) {
                        if (modetwo == false) {
                            DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, "Operando 1");
                        } else {
                            DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, "Operando 2");
                        }

                        inicio = false;
                    } else {
                        if (inicio == true) {
                            if (modetwo == false) {
                                DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, DT.recuperarTiploETQ(DT.recuperarListaTiploETQ().size() - 1) + ": Operando 1");
                            } else {
                                DT.recuperarListaTiploETQ().set(DT.recuperarListaTiploETQ().size() - 1, DT.recuperarTiploETQ(DT.recuperarListaTiploETQ().size() - 1) + ": Operando 2");
                            }

                            inicio = false;
                        }
                    }
                }

                DT.addTiplo("T" + this.cT, operation.get(i + 1), operation.get(i), "");

                operation.set(i - 1, "T" + this.cT);
                operation.remove(i + 1);
                operation.remove(i);
                i--;
            }
        }

        operation.clear();
    }

    // Metodo secundario del procesamiento de las aritmeticas.
    // Este agrega dentro de la estructura de triplos las operaciones aritmeticas
    void aritmetyProcessing(ArrayList<String> operation, boolean mode, boolean modetwo) {
        this.processingAritmetyJO(operation, mode, modetwo);

        if (mode == true) {
            DT.addTiplo(this.vP, "T" + this.cT, "=", "");

            this.vP = "";
            this.cT = 0;
        }

        if (mode == false && modetwo == false) {
            jB1 = "T" + this.cT;
        }

        if (mode == false && modetwo == true) {
            jB2 = "T" + this.cT;
            this.vP = "";
            this.cT = 0;
        }
    }
    
    // Método principal de las asignaciones y arimeticas, ya que este detecta y procesa
    // segun el orden las operaciones y finalmente los agrega a la estructura del triplo.
    void addTiploAsignacion(ArrayList<Object> instruccionesTiplo) {

        boolean asignacion = false;
        boolean operacionSimple = true;
        String operacionDato = "";

        ArrayList<String> operation = new ArrayList<String>();

        for (int a = 0; a < instruccionesTiplo.size(); a++) {
            if (a == 0) {
                if (instruccionesTiplo.get(a).toString().compareTo("Asignacion") == 0) {
                    asignacion = true;
                    this.startB = true;
                } else {
                    return;
                }
            }

            if (a != 0 && asignacion == true) {

                if ((a + 1) == instruccionesTiplo.size()) {
                    vP = instruccionesTiplo.get(a).toString();
                    asignacion = false;
                    break;
                }

                if (instruccionesTiplo.get(a).toString().compareTo("+") == 0 || instruccionesTiplo.get(a).toString().compareTo("-") == 0 || instruccionesTiplo.get(a).toString().compareTo("*") == 0 || instruccionesTiplo.get(a).toString().compareTo("/") == 0 || instruccionesTiplo.get(a).toString().compareTo("%") == 0) {
                    operation.add(instruccionesTiplo.get(a).toString());
                    operacionSimple = false;
                } else {
                    operation.add(instruccionesTiplo.get(a).toString());

                    if (operacionDato.compareTo("") == 0) {
                        operacionDato = instruccionesTiplo.get(a).toString();
                    }
                }
            }
        }

        if (operation.size() > 0 && operacionSimple == false) {
            aritmetyProcessing(operation, true, false);
        } else {
            DT.addTiplo("T1", operacionDato, "=", "Asignacion");
            DT.addTiplo(vP, "T1", "=", "");
            vP = "";
            this.startB = false;
        }
    }
}
