/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Estudiantes
 */
public class VistaInicial extends javax.swing.JFrame {

    private final Modelo modelo;
    private Controlador control;
    
    public VistaInicial(Modelo aThis) {
        modelo = aThis;
        initComponents();
        asignarEventos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        txtPuerto = new javax.swing.JTextField();
        btnConectar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaMensajes = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel1.setText("Puerto:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 40, 53, 24);

        jLabel2.setText("Host:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 10, 53, 24);
        jPanel1.add(txtHost);
        txtHost.setBounds(56, 10, 330, 20);
        jPanel1.add(txtPuerto);
        txtPuerto.setBounds(56, 40, 90, 20);

        btnConectar.setText("Conectar");
        jPanel1.add(btnConectar);
        btnConectar.setBounds(400, 10, 140, 60);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 20, 560, 80);

        txaMensajes.setColumns(20);
        txaMensajes.setRows(5);
        jScrollPane1.setViewportView(txaMensajes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 560, 270);
        getContentPane().add(txtMensaje);
        txtMensaje.setBounds(10, 390, 450, 50);

        btnEnviar.setText("Enviar");
        getContentPane().add(btnEnviar);
        btnEnviar.setBounds(470, 390, 100, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void asignarEventos() {
        btnConectar.addActionListener(getControl());
        btnEnviar.addActionListener(getControl());
    }

    public Modelo getModelo() {
        return modelo;
    }

    public Controlador getControl() {
        if(control == null){
            control = new Controlador(this);
        }
        return control;
    }

    public JButton getBtnConectar() {
        return btnConectar;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public JTextArea getTxaMensajes() {
        return txaMensajes;
    }

    public JTextField getTxtHost() {
        return txtHost;
    }

    public JTextField getTxtMensaje() {
        return txtMensaje;
    }

    public JTextField getTxtPuerto() {
        return txtPuerto;
    }
 
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txaMensajes;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextField txtPuerto;
    // End of variables declaration//GEN-END:variables
}
