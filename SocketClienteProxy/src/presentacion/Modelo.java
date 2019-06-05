/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.io.IOException;
import javax.swing.JOptionPane;
import logica.Sistema;

/**
 *
 * @author Estudiantes
 */
public class Modelo implements Runnable{

    private VistaInicial ventanaInicial;
    private Sistema miSistema;
    private Thread hiloDibujo;
    private boolean conectado;
    
    public void iniciar() {
        getVentanaInicial().getTxtHost().setEnabled(true);
        getVentanaInicial().getTxtPuerto().setEnabled(true);
        getVentanaInicial().getBtnConectar().setEnabled(true);
        getVentanaInicial().getTxaMensajes().setEnabled(false);
        getVentanaInicial().getTxtMensaje().setEnabled(false);
        getVentanaInicial().getBtnEnviar().setEnabled(false);
        getVentanaInicial().getTxaMensajes().setEditable(false);
        
        getVentanaInicial().setSize(640, 520);
        getVentanaInicial().setVisible(true);       
        conectado = false;
    }

    public VistaInicial getVentanaInicial() {
        if(ventanaInicial == null){
            ventanaInicial = new VistaInicial(this);
        }
        return ventanaInicial;
    }

    public Sistema getMiSistema() {
        if(miSistema == null){
            miSistema = new Sistema();
        }
        return miSistema;
    }
    
    public void conectar(){
       
            String host;
            String puerto;
            
            host = getVentanaInicial().getTxtHost().getText();
            if(host.isEmpty()){
                mostrarError("El campo de host no debe estar vacio");
                return;
            }
            
            puerto = getVentanaInicial().getTxtPuerto().getText();
            
            if(puerto.isEmpty()){
                mostrarError("El campo de puerto no debe estar vacio");
                return;
            }
        
            try {    
                getMiSistema().conectar(host, Integer.parseInt(puerto));
                conectado = true;
                hiloDibujo = new Thread(this);
                getVentanaInicial().getTxtHost().setEnabled(false);
                getVentanaInicial().getTxtPuerto().setEnabled(false);
                getVentanaInicial().getBtnConectar().setText("Desconectar");
                getVentanaInicial().getTxaMensajes().setEnabled(true);
                getVentanaInicial().getTxtMensaje().setEnabled(true);
                getVentanaInicial().getBtnEnviar().setEnabled(true);
                hiloDibujo.start();
                
            } catch (IOException ex) {
                mostrarError(ex.getMessage());
            }  
            
    }
    
    public void desconectar(){
        try {
            getMiSistema().desconectar();
            conectado = false;
            hiloDibujo = null;
        } catch (IOException ex) {
            mostrarError(ex.getMessage());            
        }
        getVentanaInicial().getTxtHost().setEnabled(true);        
        getVentanaInicial().getTxtPuerto().setEnabled(true);
        getVentanaInicial().getBtnConectar().setText("Conectar");
        getVentanaInicial().getTxaMensajes().setEnabled(false);
        getVentanaInicial().getTxtMensaje().setEnabled(false);
        getVentanaInicial().getBtnEnviar().setEnabled(false);
        getVentanaInicial().getTxaMensajes().setEditable(false);
    }
    
    public void enviarMensaje(){
        String mensaje;

        mensaje = getVentanaInicial().getTxtMensaje().getText();
        if (mensaje.isEmpty()) {
            mostrarError("El campo de mensaje no debe estar vacio");
            return;
        }
        try {
            getMiSistema().enviarMensaje(mensaje);
        } catch (IOException ex) {
            mostrarError(ex.getMessage());
        }
    }
    
    public void mostrarMensajes(){
        while(getMiSistema().isConectado()){
            getVentanaInicial().getTxaMensajes().setText(getMiSistema().getMensajes().toString());
        }
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(ventanaInicial, msg, "Error de datos", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void run() {        
        mostrarMensajes();
    }
    
    
}
