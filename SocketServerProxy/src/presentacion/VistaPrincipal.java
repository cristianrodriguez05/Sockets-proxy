/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author estudiantes
 */
public class VistaPrincipal extends javax.swing.JFrame {

    private final Modelo miModelo;
    private Controlador control;
    
    public VistaPrincipal(Modelo a) {
        miModelo = a;
        initComponents();
        asignarEventos();
    }

    public Modelo getMiModelo() {
        return miModelo;
    }

    public Controlador getControl() {
        if(control == null){
            control = new Controlador(this);
        }
        return control;
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPuerto = new javax.swing.JTextField();
        btnEscuchar = new javax.swing.JButton();
        lblConectado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel1.setText("Puerto a escuchar:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 24, 100, 20);
        jPanel1.add(txtPuerto);
        txtPuerto.setBounds(120, 20, 80, 30);

        btnEscuchar.setText("Escuchar");
        jPanel1.add(btnEscuchar);
        btnEscuchar.setBounds(310, 10, 140, 60);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 470, 80);

        lblConectado.setBackground(new java.awt.Color(153, 153, 0));
        lblConectado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblConectado.setForeground(new java.awt.Color(255, 255, 255));
        lblConectado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConectado.setText("No se ha conectado nadie aún...");
        lblConectado.setOpaque(true);
        getContentPane().add(lblConectado);
        lblConectado.setBounds(10, 100, 470, 30);

        txaMensajes.setColumns(20);
        txaMensajes.setRows(5);
        jScrollPane1.setViewportView(txaMensajes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 140, 470, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void asignarEventos() {
        btnEscuchar.addActionListener(getControl());
    }

    public JButton getBtnEscuchar() {
        return btnEscuchar;
    }

    public JTextArea getTxaMensajes() {
        return txaMensajes;
    }

    public JTextField getTxtPuerto() {
        return txtPuerto;
    }

    public JLabel getLblConectado() {
        return lblConectado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscuchar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConectado;
    private javax.swing.JTextArea txaMensajes;
    private javax.swing.JTextField txtPuerto;
    // End of variables declaration//GEN-END:variables
}
