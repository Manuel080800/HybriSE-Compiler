package main;

import com.formdev.flatlaf.FlatLightLaf;
import ensambler.AnalizerEnsambler;
import ensambler.DataEnsambler;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import system.MainCode;
import system.DataTiplo;
import system.AnalizerCode;
import system.DataError;
import system.DataExpresion;
import system.DataSimbolo;
import system.DataToken;

public class inicio extends javax.swing.JFrame {

    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    static int linea = 1;
    static int cont2 = -1;
    static boolean existe = false;
    static boolean Existencia = false;

    private DataExpresion DE = new DataExpresion();
    private DataSimbolo DS = new DataSimbolo();
    private DataError DSE = new DataError();
    private DataToken DST = new DataToken();
    private DataTiplo DT = new DataTiplo();
    private AnalizerCode AC = null;
    private DataEnsambler DES = new DataEnsambler();

    /**
     * Creates new form inicio
     */
    ArrayList<String> tipo = new ArrayList<>();
    ArrayList<String> tipotoken = new ArrayList<>();
    ArrayList<String> errores = new ArrayList<>();
    ArrayList<String> Terrores = new ArrayList<>();
    ArrayList<String> descripcion = new ArrayList<>();

    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm2 = new DefaultTableModel();
    DefaultTableModel dtm3 = new DefaultTableModel();
    DefaultTableModel dtm4 = new DefaultTableModel();
    
    private Tipo Tipo;
    public int cont = 0;
    public int cont1 = 0;
    public int cont3 = 0;
    public int cont4 = 0;
    public int cont5 = 0;
    public int cont6 = 0;
    public int cont7 = 0;
    public int cont8 = 0;
    public int cont9 = 0;
    public int cont10 = 0;
    public int cont11 = 0;
    public int cont12 = 0;
    public String valor;
    public String[] letra = new String[10000];
    public String[] simbolo = new String[10000];
    public String[] or = new String[10000];
    public String[] CNE = new String[10000];
    public String[] td = new String[10000];
    public String[] oa = new String[10000];
    public String[] ir = new String[10000];
    public String[] ic = new String[10000];
    public String[] del = new String[10000];
    public String[] sep = new String[10000];
    public String[] id = new String[10000];
    public String[] as = new String[10000];
    public String[] cnpf = new String[10000];
    public String[] ersem = new String[10000];
    public String[] ar = new String[10000];
    public int[] lin = new int[10000];
    public String[] ERR = new String[10000];
    public String[] IDT = new String[10000];
    public String[] br = new String[10000];
    int conpal = 0, e = 0, cont111 = 0, conterr = 0, contiv = 0, bs = 0, idt = 0, cidt = 0, ww = 0;
    int sd = 1, s = 0, contsem = 0, conta = 0, w = 0, l = 0, asy = 0;

    public inicio() {
        initComponents();
        setLocationRelativeTo(this);
        mostrar();
        applyLook(new FlatLightLaf());

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                updateSize();
            }
        });
    }

    void updateSize() {
        jPanel1.setSize(this.getSize());
        jPanel1.setPreferredSize(this.getSize());
        jPanel1.repaint();
    }

    private void applyLook(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception error) {

            error.printStackTrace();
        }
    }

    public void existencia(String ver) {
        existe = false;
    }

    int k = 0;

    private ArrayList<enlace> lex(String input) {
        final ArrayList<enlace> tokens = new ArrayList<enlace>();
        final StringTokenizer st = new StringTokenizer(input);

        while (st.hasMoreTokens()) {
            String palabra = st.nextToken();
            if ("/n".equals(palabra)) {
                linea++;
            }
            boolean matched = false;

            for (Tipo tokenTipo : Tipo.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher matcher = patron.matcher(palabra);
                if (matcher.find()) {
                    enlace tk = new enlace();
                    tk.setTipo(tokenTipo);  //aqui guarda el que verdaderamente es
                    tk.setValor(palabra);
                    tokens.add(tk);
                    matched = true;
                    lin[s] = linea;
                    s++;
                }
            }
            if (!matched) {
                errores.add(palabra);
                String g = "ERL";
                String o = "Error lexico";
                String u = "Linea";
                for (int i = 0; i < errores.size(); i++) {
                    if ("/n".equals(palabra)) {
                        Existencia = true;
                    } else {
                        descripcion.add(o);
                        String p = g + cont;
                        Terrores.add(p);
                    }
                    if (Existencia == false) {
                        dtm2.addRow(new Object[]{Terrores.get(cont), errores.get(i), linea, descripcion.get(i)});
                    }

                    Existencia = false;
                    if ("/n".equals(palabra)) {
                    } else {
                        cont = cont + 1;
                    }
                }
                errores.clear();

            }

        }
        return tokens;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnabrir = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnanalizar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtentrada = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtarea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnguardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaTriplo = new javax.swing.JTable();
        btnguardar1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        ensambler = new javax.swing.JTable();
        btnguardar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compiler Express SE");
        setBackground(new java.awt.Color(153, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Vista"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnabrir.setBackground(new java.awt.Color(0, 0, 0));
        btnabrir.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        btnabrir.setForeground(new java.awt.Color(255, 255, 255));
        btnabrir.setText("Agregar Archivo");
        btnabrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnabrirActionPerformed(evt);
            }
        });

        btnlimpiar.setBackground(new java.awt.Color(0, 0, 0));
        btnlimpiar.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        btnlimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnlimpiar.setText("Limpiar Tablas");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnanalizar.setBackground(new java.awt.Color(0, 0, 0));
        btnanalizar.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        btnanalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnanalizar.setText("Analizar Texto");
        btnanalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanalizarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        txtentrada.setColumns(20);
        txtentrada.setRows(5);
        jScrollPane1.setViewportView(txtentrada);

        txtarea2.setColumns(20);
        txtarea2.setRows(5);
        jScrollPane4.setViewportView(txtarea2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btnguardar.setBackground(new java.awt.Color(0, 0, 0));
        btnguardar.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Descargar lista de Token  ");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Semantico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaTriplo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tablaTriplo);

        btnguardar1.setBackground(new java.awt.Color(0, 0, 0));
        btnguardar1.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        btnguardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar1.setText("Descargar archivo de Tiplo  ");
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });

        ensambler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(ensambler);

        btnguardar2.setBackground(new java.awt.Color(0, 0, 0));
        btnguardar2.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        btnguardar2.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar2.setText("Descargar archivo ensamblador  ");
        btnguardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnabrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnanalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnguardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnguardar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnabrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnanalizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnlimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.getName().endsWith("txt")) {
                String Documento = txtarea2.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo No Valido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Guardar Documento de Texto");
            }
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnanalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanalizarActionPerformed

        if (txtentrada.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite caracteres en el campo");
        }
        String cad = txtentrada.getText();
        if ("/n".equals(cad)) {
            linea++;
        }
        cad = new StringBuffer(cad).insert(cad.length() - 1, " ").toString();
        ArrayList<enlace> tokens = lex(cad);
        for (enlace token : tokens) {
            String a = ("" + token.getTipo());
            String b = ("" + token.getValor());
            //tipotoken.add(b);
            String c;
            br[cont111] = b;
            ar[cont111] = a;
            cont111 = cont111 + 1;
            for (w = 0; w < cont111; w++) {

                if ("TD".equals(ar[w])) {
                    conta++;
                    if ("ID".equals(ar[w + 1])) {
                        if (",".equals(br[w + 2])) {
                            if ("ID".equals(ar[w + 3])) {
                                if (",".equals(br[w + 4])) {
                                    if ("ID".equals(ar[w + 5])) {
                                        ersem[contsem] = br[w + 1];
                                        contsem++;
                                        ersem[contsem] = br[w + 3];
                                        contsem++;
                                        ersem[contsem] = br[w + 5];
                                        contsem++;
                                    } else {
                                    }
                                }
                            }
                        }
                    }
                }

                if ("TD".equals(ar[w])) {
                    conta++;
                    if ("ID".equals(ar[w + 1])) {
                        if (",".equals(br[w + 2])) {
                            if ("ID".equals(ar[w + 3])) {
                                //if (";".equals(br[w+4])){
                                ersem[contsem] = br[w + 1];
                                contsem++;
                                ersem[contsem] = br[w + 3];
                                contsem++;
                            } else {
                            }
                        }
                    }
                }
                //}
                if ("TD".equals(ar[w])) {
                    conta++;
                    if ("ID".equals(ar[w + 1])) {
                        if (";".equals(br[w + 2])) {
                        }
                        ersem[contsem] = br[w + 1];
                        contsem++;
                    }
                }
            }
            if ("TD".equals(a)) {
                td[cont3] = b;
                for (int z = 0; z <= cont3; z++) {
                    if (b.equals(td[z])) {
                        valor = b;
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont3;
                    tipo.add(c);
                    cont3 = cont3 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("OA".equals(a)) {
                oa[cont4] = b;
                for (int z = 0; z <= cont4; z++) {
                    if (b.equals(oa[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont4;
                    tipo.add(c);
                    cont4 = cont4 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("AS".equals(a)) {
                as[cont5] = b;
                for (int z = 0; z <= cont5; z++) {
                    if (b.equals(as[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont5;
                    tipo.add(c);
                    cont5 = cont5 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("DEL".equals(a)) {
                del[cont6] = b;
                for (int z = 0; z <= cont6; z++) {
                    if (b.equals(del[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont6;
                    tipo.add(c);
                    cont6 = cont6 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("OR".equals(a)) {
                or[cont7] = b;
                for (int z = 0; z <= cont7; z++) {
                    if (b.equals(or[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont7;
                    tipo.add(c);
                    cont7 = cont7 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("SEP".equals(a)) {
                sep[cont9] = b;
                for (int z = 0; z <= cont9; z++) {
                    if (b.equals(sep[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont9;
                    tipo.add(c);
                    cont9 = cont9 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("ID".equals(a)) {
                id[cont10] = b;
                for (int z = 0; z <= cont10; z++) {
                    if (b.equals(id[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    for (int u = 0; u < contsem; u++) {
                        if (b.equals(ersem[u])) {
                            bs++;
                        }
                    }
                    if (bs >= 1 || cont10 == 0) {
                        tipotoken.add(b);
                        c = a + cont10;
                        tipo.add(c);
                        cont10 = cont10 + 1;
                        IDT[idt] = b;
                        idt++;
                        IDT[idt] = valor;
                        idt++;
                        dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j), valor});
                        letra[conpal] = b;
                        simbolo[conpal] = c;
                        //txtarea2.append("\n"+tipo.get(j));
                        j++;
                        conpal++;
                    } else {
                        tipotoken.add(b);
                        c = a + cont10;
                        tipo.add(c);
                        cont10 = cont10 + 1;
                        IDT[idt] = b;
                        idt++;
                        IDT[idt] = "NO";
                        idt++;
                        dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                        letra[conpal] = b;
                        simbolo[conpal] = c;
                        j++;
                        conpal++;
                    }
                } else {
                }
                bs = 0;
                e = 0;
            } else {
            }
            if ("CNE".equals(a)) {
                CNE[cont11] = b;
                for (int z = 0; z <= cont11; z++) {
                    if (b.equals(CNE[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont11;
                    tipo.add(c);
                    cont11 = cont11 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("CNPF".equals(a)) {
                cnpf[cont12] = b;
                for (int z = 0; z <= cont12; z++) {
                    if (b.equals(cnpf[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont12;
                    tipo.add(c);
                    cont12 = cont12 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            } else {
            }
            if ("IC".equals(a)) {
                ic[cont1] = b;
                for (int z = 0; z <= cont1; z++) {
                    if (b.equals(ic[z])) {
                        e++;
                    }
                }
                if (e == 1) {
                    tipotoken.add(b);
                    c = a + cont1;
                    tipo.add(c);
                    cont1 = cont1 + 1;
                    dtm.addRow(new Object[]{tipotoken.get(j), tipo.get(j)});
                    letra[conpal] = b;
                    simbolo[conpal] = c;
                    //txtarea2.append("\n"+tipo.get(j));
                    j++;
                    conpal++;
                }
                e = 0;
            }
            //j++;
        }
        e = 0;

        //INDEFINIDA LA VARIABLE 
        for (int z = 0; z < cont111; z++) {
            for (int o = 0; o <= conpal; o++) {
                if (br[z].equals(letra[o])) {
                    if (lin[z] > sd) {
                        txtarea2.append("\n");
                        sd++;
                    }
                    txtarea2.append(" " + simbolo[o]);
                    //txtarea2.append("\n"+tipo.get(j));
                } else {
                }
            }
        }
        this.DE = new DataExpresion();
        this.DS = new DataSimbolo();
        this.DSE = new DataError();
        this.DST = new DataToken();
        this.DT = new DataTiplo();
        this.DES = new DataEnsambler();

        MainCode analisisContenido = new MainCode(this.txtentrada, this.DE, this.DS, this.DSE, DST, this.DT);
        analisisContenido.analizarContenidoEdicion(false);

        for (int m = 1; m < this.DT.recuperarsizeTiplo(); m++) {
            dtm3.addRow(new Object[]{DT.recuperarTiploID(m), DT.recuperarTiploDO(m), DT.recuperarTiploDF(m), DT.recuperarTiploOP(m)});
        }
        
        AnalizerEnsambler analizerES = new AnalizerEnsambler(DT, DES);
        analizerES.generarCodigoEnsamblador();
        
        for (int m = 0; m < this.DES.getSizeIntrution(); m++) {
            dtm4.addRow(new Object[]{this.DES.getETIQ(m), this.DES.getMEN(m), this.DES.getOPERSAVE(m), this.DES.getOPERREF(m)});
        }

        tablaTriplo.setModel(dtm3);
        ensambler.setModel(dtm4);

        txtarea2.setText("");

        txtarea2.append(DST.getLineaContenidoTokenEscritas());
    }//GEN-LAST:event_btnanalizarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        // TODO add your handling code here:
        txtarea2.setText(null);
        txtentrada.setText(null);
        Terrores.clear();
        descripcion.clear();
        int a = dtm.getRowCount();
        int b = dtm2.getRowCount();
        int c = dtm3.getRowCount();
        int d = dtm4.getRowCount();
        cont = 0;
        for (int i = 0; i < a; i++) {
            dtm.removeRow(0);
        }
        for (int i = 0; i < b; i++) {
            dtm2.removeRow(0);
        }
        for (int i = 0; i < c; i++) {
            dtm3.removeRow(0);
        }
        for (int i = 0; i < d; i++) {
            dtm4.removeRow(0);
        }
        for (int j = 0; j < 10000; j++) {
            br[j] = null;
            td[j] = null;
            oa[j] = null;
            or[j] = null;
            cnpf[j] = null;
            as[j] = null;
            sep[j] = null;
            id[j] = null;
            CNE[j] = null;
            del[j] = null;
            ic[j] = null;
            or[j] = null;
            letra[j] = null;
            simbolo[j] = null;
            lin[j] = 0;
            ar[j] = null;
            ersem[j] = null;
            ERR[j] = null;
            IDT[j] = null;
        }
        cont = 0;
        cont1 = 0;
        cont3 = 0;
        cont4 = 0;
        cont5 = 0;
        cont6 = 0;
        cont7 = 0;
        cont8 = 0;
        cont9 = 0;
        cont10 = 0;
        cont11 = 0;
        cont12 = 0;
        linea = 1;
        conpal = 0;
        sd = 0;
        cont111 = 0;
        e = 0;
        s = 0;
        asy = 0;
        l = 0;
        w = 0;
        conta = 0;
        contsem = 0;
        bs = 0;
        contiv = 0;
        conterr = 0;
        idt = 0;
        cidt = 0;
        ww = 0;
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnabrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnabrirActionPerformed
        // TODO add your handling code here:
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String documento = AbrirArchivo(archivo);
                    txtentrada.setText(documento);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo No Valido");
                }
            }
        }
    }//GEN-LAST:event_btnabrirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //INDEFINIDA LA VARIABLE

        for (w = 0; w < cont111; w++) {
            if ("ID".equals(ar[w])) {
                for (int u = 0; u < contsem; u++) {
                    if (br[w].equals(ersem[u])) {
                        asy++;
                    }
                }
                if (asy >= 1) {
                    int df = 0;
                } else {
                    ERR[conterr] = br[w];
                    for (int z = 0; z <= conterr; z++) {
                        if (br[w].equals(ERR[z])) {
                            e++;
                        }
                    }
                    conterr++;
                    if (e == 1) {
                        String g = "ERRSEM";
                        String o = "Indefinida la variable";
                        String u = "Linea";
                        l = cont;
                        descripcion.add(o);
                        String p = g + contiv;
                        Terrores.add(p);
                        dtm2.addRow(new Object[]{Terrores.get(l), br[w], lin[w], descripcion.get(l)});
                        l++;
                        ww = w;
                        contiv = contiv + 1;
                        cont = cont + 1;
                    }
                }
                e = 0;
                asy = 0;
            }
        }
        //INCOPATIBILIDAD DE TIPO

        for (int v = 0; v < idt; v++) {
            for (int b = 0; b < cont111; b++) {

                if (IDT[cidt].equals(br[b])) {
                    if ("=".equals(br[b + 1])) {
                        if (";".equals(br[b + 3])) {
                            for (int t = 0; t < b; t++) {
                                if (br[b + 2].equals(IDT[t])) {
                                    if (IDT[cidt + 1].equals(IDT[t + 1])) {
                                        //bien
                                    } else {

                                        String g = "ERRSEM";
                                        String o = "Incompatibilidad de tipo";
                                        String ñ = o + (IDT[t + 1]);
                                        l = cont;
                                        descripcion.add(ñ);
                                        String p = g + contiv;
                                        Terrores.add(p);
                                        dtm2.addRow(new Object[]{Terrores.get(l), IDT[t], lin[b], descripcion.get(l)});
                                        l++;
                                        contiv = contiv + 1;
                                        cont = cont + 1;
                                    }
                                }
                            }
                        }
                    }
                }

                if (IDT[cidt].equals(br[b])) {
                    if ("=".equals(br[b + 1])) {
                        if ("OA".equals(ar[b + 3])) {
                            if (";".equals(br[b + 5])) {
                                for (int t = 0; t < b; t++) {
                                    if (br[b + 2].equals(IDT[t])) {
                                        if (IDT[cidt + 1].equals(IDT[t + 1])) {
                                            //comparamos el otro
                                            for (int j = 0; j < b; j++) {
                                                if (br[b + 4].equals(IDT[j])) {
                                                    if (IDT[cidt + 1].equals(IDT[j + 1])) {
                                                        //bien
                                                    } else {
                                                        String g = "ERRSEM";
                                                        String o = "Incompatibilidad de tipo";
                                                        String ñ = o + (IDT[j + 1]);
                                                        l = cont;
                                                        descripcion.add(ñ);
                                                        String p = g + contiv;
                                                        Terrores.add(p);
                                                        dtm2.addRow(new Object[]{Terrores.get(l), IDT[j], lin[b], descripcion.get(l)});
                                                        l++;
                                                        contiv = contiv + 1;
                                                        cont = cont + 1;
                                                    }
                                                }
                                            }
                                        } else {

                                            String g = "ERRSEM";
                                            String o = "Incompatibilidad de tipo";
                                            String ñ = o + (IDT[t + 1]);
                                            l = cont;
                                            descripcion.add(ñ);
                                            String p = g + contiv;
                                            Terrores.add(p);
                                            dtm2.addRow(new Object[]{Terrores.get(l), IDT[t], lin[b], descripcion.get(l)});
                                            l++;
                                            ww++;
                                            contiv = contiv + 1;
                                            cont = cont + 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            cidt++;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
        // TODO add your handling code here:
        if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.getName().endsWith("txt")) {
                String Documento = this.DT.formartPrintF();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo No Valido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Guardar Documento de Texto");
            }
        }
    }//GEN-LAST:event_btnguardar1ActionPerformed

    private void btnguardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar2ActionPerformed
        // TODO add your handling code here:
        if (seleccionar.showDialog(this, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.getName().endsWith("txt")) {
                String Documento = this.DES.getFormartEnsambler();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo No Valido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Guardar Documento de Texto");
            }
        }
    }//GEN-LAST:event_btnguardar2ActionPerformed

    int j = 0;
    int i = 0;

    public void mostrar() {

        try {
            dtm.addColumn("Lexema");
            dtm.addColumn("Token");
            dtm.addColumn("Tipo de dato");
            dtm2.addColumn("Token de error");
            dtm2.addColumn("Lexema");
            dtm2.addColumn("Linea");
            dtm2.addColumn("descripción");
            dtm3.addColumn("Renglon");
            dtm3.addColumn("Dato objecto");
            dtm3.addColumn("Dato fuente");
            dtm3.addColumn("Operador");
            dtm4.addColumn("Etiqueta");
            dtm4.addColumn("Operación");
            dtm4.addColumn("Operando objeto");
            dtm4.addColumn("Operando fuente");

            jTable2.setModel(dtm2);
            jTable1.setModel(dtm);
            tablaTriplo.setModel(dtm3);
            ensambler.setModel(dtm4);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "error mostrar" + ex);
        }
    }

    public String AbrirArchivo(File archivo) {
        String documento = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;
            }
        } catch (IOException e) {
        }
        return documento;

    }

    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje = "Archivo Guardado corectamente";
        } catch (IOException e) {
        }
        return mensaje;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                inicio frame = new inicio();
                frame.applyLook(new FlatLightLaf());
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnabrir;
    private javax.swing.JButton btnanalizar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnguardar2;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JTable ensambler;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tablaTriplo;
    private javax.swing.JTextArea txtarea2;
    private javax.swing.JTextArea txtentrada;
    // End of variables declaration//GEN-END:variables

    private String GuardarArchivo(File archivo, JTableHeader Documento2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
