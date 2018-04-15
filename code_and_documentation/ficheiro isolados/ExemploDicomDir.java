package imtp4;


import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import fr.apteryx.imageio.dicom.Tag;
import fr.apteryx.imageio.dicom.DataSet;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;

import fr.apteryx.imageio.dicom.Plugin;

import javax.swing.table.*;
import javax.swing.ListSelectionModel;

public class ExemploDicomDir extends javax.swing.JFrame implements ListSelectionListener{
    
    Vector resultadosLeitura;
    Vector atributosExames;
    Vector dadosTabela;
    Vector filesExames;
    Vector frameTime;
    Vector numFrames;
    DefaultListSelectionModel list;
    int indiceLinhaTabela;
    
    public ExemploDicomDir()
    {
        initComponents();
        resultadosLeitura=new Vector(5);
        atributosExames = new Vector();
        dadosTabela = new Vector();
        filesExames = new Vector();
        frameTime = new Vector();
        numFrames = new Vector();
        list = new DefaultListSelectionModel(); 
        indiceLinhaTabela = -1;
    }
                             
    private void initComponents() {
        
        this.setTitle("IM - TP4 - DICOM                                                                      Edite Figueiras, João Duarte, Ricardo Martins");
        
        txtPath = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableExames = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        btn_mostrarImagens = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        txtPath.setText("C:/");
        txtPath.setName("txtPath");

        lblTitle.setText("DicomDir Path:");
        lblTitle.setName("lblPath");

        jButton1.setText("Show");
        jButton1.setName("btnShow");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        btn_mostrarImagens.setText("Mostrar Imagens");
        btn_mostrarImagens.setName("btn_mostrarImagens");
        btn_mostrarImagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrarImagensActionPerformed(evt);
            }
        });
                
        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);
                

        tableExames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {            
            },
            new String [] {
                "ID Paciente", "Nome Paciente", "Data Nascimento", "Tipo Exame"
            }
        ));
        jScrollPane2.setViewportView(tableExames);

        fileMenu.setText("File");
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As ...");
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText("Help");
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(lblTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 16, Short.MAX_VALUE)
                                .add(txtPath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 631, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jButton1)
                        .add(7, 7,7)
                        .add(btn_mostrarImagens)
                        .add(180,180,180))))
                        
                        
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblTitle)
                    .add(txtPath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                 .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jButton1)                           
                .add(btn_mostrarImagens)  )               
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 402, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
       try
       {                    
            Vector colNames = new Vector();
            colNames.addElement("ID Paciente");
            colNames.addElement("Nome Paciente");
            colNames.addElement("Data Nascimento");
            colNames.addElement("Tipo Exame");
                                                                   
            try
            {
                resultadosLeitura= ReadDicomDir.leDirectorio(txtPath.getText()); 
                dadosTabela = (Vector) resultadosLeitura.elementAt(0);
                atributosExames = (Vector) resultadosLeitura.elementAt(1);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Invalid DicomDir","Warning", JOptionPane.WARNING_MESSAGE);
            }
        
            TableModel tbModel = new DefaultTableModel(dadosTabela,colNames)
            {
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };
        
            tableExames.setModel(tbModel);                    
        
            list.addListSelectionListener(this);
            tableExames.setSelectionModel(list);
            tableExames.validate();
            txtArea.setText("");
       
            this.repaint();
        
       }
       catch(Exception e)
       {
           System.out.println("Error in reading dicomDir.");
       }       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_mostrarImagensActionPerformed(java.awt.event.ActionEvent evt)
    {
        if(indiceLinhaTabela >= 0)
        {
            
            
            filesExames = (Vector) resultadosLeitura.elementAt(2);
            frameTime = (Vector) resultadosLeitura.elementAt(3);
            numFrames = (Vector) resultadosLeitura.elementAt(4);
                       

            Vector linhaSel =(Vector) dadosTabela.elementAt(indiceLinhaTabela);
            String idPac = (String) linhaSel.elementAt(0);
            String nomePac = (String) linhaSel.elementAt(1);
            String tipoEx = (String) linhaSel.elementAt(3);
        
            String caminho = txtPath.getText()+ "/" + filesExames.elementAt(indiceLinhaTabela).toString();
            double dframeT = Double.valueOf((String) frameTime.elementAt(indiceLinhaTabela));
            int frameT = (int) dframeT;
            int numF = Integer.parseInt((String)numFrames.elementAt(indiceLinhaTabela));
                            
            visualizadorAnimacao VA = new visualizadorAnimacao(caminho,idPac,nomePac,tipoEx,frameT,numF);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Seleccione um dos registos da tabela!!","Warning", JOptionPane.WARNING_MESSAGE);
        }
    }    
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    

    public static void main(String args[]) 
    {
        try
        {        
            java.awt.EventQueue.invokeLater(new Runnable() 
            {
                public void run() 
                {
                    new ExemploDicomDir().setVisible(true);
                }
            });            
        }catch(Exception e)
        {
            System.out.println("Error in the Aplication...");
        }
    }
    public void valueChanged(ListSelectionEvent e)
    {
            indiceLinhaTabela = tableExames.getSelectedRow();
            Atributes attTemp = (Atributes)atributosExames.elementAt(indiceLinhaTabela);
            txtArea.setText(attTemp.regImage.toString());                              
    }
    
    
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTable tableExames;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtPath;
    private javax.swing.JButton btn_mostrarImagens;         
}
