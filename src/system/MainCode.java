package system;

import javax.swing.JTextArea;

public class MainCode {

    /*
        Información:
        Clase principal del compilador, este es el encargado de ejecutar los procesos
        de análisis léxico y de codigo intermedio.
    */
    
    DataExpresion DE = null;
    DataSimbolo DS = null;
    DataError DSE = null;
    DataToken DST = null;
    DataTiplo DT = null;
    JTextArea textAreaJava = null;

    public MainCode(JTextArea textAreaJava, DataExpresion DE, DataSimbolo DS, DataError DSE, DataToken DST, DataTiplo DT) {
        this.textAreaJava = textAreaJava;
        this.DE = DE;
        this.DS = DS;
        this.DSE = DSE;
        this.DST = DST;
        this.DT = DT;
    }

    public void analizarContenidoEdicion(boolean identificacionExpresion) {
        // Obtiene todas las palabras de la entrada de texto del compilador.
        AnalizerCode AC = new AnalizerCode(this.textAreaJava.getText());
        AC.runAnalizer();
        
        // Ejecuta estos módulos a partir de las palabras anteriormente encontradas
        this.lexico(AC);
        this.Intermedio(AC);
    }

    void lexico(AnalizerCode AC) {
        Lexico analisisLexico = new Lexico(DE, DS, DSE, DST, AC);
        analisisLexico.runAnalizerLexica();
    }

    void Intermedio(AnalizerCode AC) {
        AnalizerIntermediate codigoIntermedio = new AnalizerIntermediate(DE, DS, DSE, AC, DT);
        codigoIntermedio.generateCode();
    }
}
