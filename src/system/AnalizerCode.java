package system;

import java.util.ArrayList;

public class AnalizerCode {

    /*
        Información:
        Clase destinada para obtener las líneas de código existentes en el
        JTextArea. 
    */
    
    String cEC = null;
    ArrayList<StructurCode> eEC = new ArrayList<StructurCode>();
    ArrayList<Integer> lLC = new ArrayList<Integer>();
    int tLC = 0;
    boolean iA = false;

    public AnalizerCode(String cEC) {
        this.cEC = cEC;
    }

    // Método principal:
    public void runAnalizer() {
        if (this.iA == false) {
            ArrayList<String> cEC = analizerCEC();
            int lonA = 0;

            for (int i = 0; i < cEC.size(); i++) {
                StructurCode aEE = new StructurCode(cEC.get(i));
                aEE.analizerCode(i);

                // Eliminación del /n presente en el código:
                for (int j = 0; j < aEE.getSizeStructure(); j++) {
                    String omitir = "/n";

                    if (aEE.gettEstructureID(j).compareTo(omitir) == 0) {
                        aEE.getListEstructure().remove(j);
                        aEE.getListEstructureStart().remove(j);
                        aEE.getListEstructureEnd().remove(j);
                        aEE.setSizeStructure(aEE.getSizeStructure() - 1);
                    }
                }

                lonA += (1 + aEE.getLongitudText());

                this.eEC.add(aEE);
                this.lLC.add(lonA);
            }

            this.iA = true;
        }
    }

    // Encargado de obtener las lineas de código del texto:
    ArrayList<String> analizerCEC() {
        String lC = "";
        ArrayList<String> lEC = new ArrayList<String>();

        for (int i = 0; i < this.cEC.length(); i++) {
            if (this.cEC.charAt(i) == '\n') {
                lEC.add(lC);
                this.tLC++;
                lC = "";
            } else {
                lC += this.cEC.charAt(i);

                if (i == (this.cEC.length() - 1)) {
                    lEC.add(lC);
                    this.tLC++;
                    lC = "";
                }
            }
        }

        return lEC;
    }

    public ArrayList<StructurCode> getEstructura() {
        return this.eEC;
    }

    public ArrayList<Integer> getListaEstructura() {
        return this.lLC;
    }

    public StructurCode getContenidoEstructura(int posicionLineaEdicion) {
        return this.eEC.get(posicionLineaEdicion);
    }

    public int getLogEdicion(int posicionLineaEdicion) {
        return this.lLC.get(posicionLineaEdicion);
    }

    public int getSizeEstructura() {
        return this.tLC;
    }
}
