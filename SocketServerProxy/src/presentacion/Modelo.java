package presentacion;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.SistemaServer;

public class Modelo implements Runnable{

    private SistemaServer miSistema;
    private VistaPrincipal ventanaPrincipal;
    private Thread hiloDibujo;

    public Modelo() {
        hiloDibujo = new Thread(this);
    }        

    public SistemaServer getMiSistema() {
        if(miSistema == null){
            miSistema = new SistemaServer();
        }
        return miSistema;
    }

    public VistaPrincipal getVentanaPrincipal() {
        if(ventanaPrincipal == null){
            ventanaPrincipal = new VistaPrincipal(this);
        }
        return ventanaPrincipal;
    }
            
    //************representacion de cU
    public void iniciar() {
        getVentanaPrincipal().setSize(640, 500);
        getVentanaPrincipal().setVisible(true);
    }

    public void esperarConexion(){                
            int puerto;
            String puertoTexto = getVentanaPrincipal().getTxtPuerto().getText();
            if (puertoTexto.isEmpty()) {
                mostrarError("Debe especificar un numero de puerto válido");
                return;
            }
            puerto = Integer.parseInt(puertoTexto);

            try {
                getMiSistema().setPuerto(puerto);
                getMiSistema().activarEsperaConexiones();
                getVentanaPrincipal().getLblConectado().setText("Esperando conexiones...");
                hiloDibujo.start();
                
                getVentanaPrincipal().getBtnEscuchar().setText("Detener");
                getVentanaPrincipal().getTxtPuerto().setEnabled(false);
            } catch (IOException ex) {
                mostrarError(ex.getMessage());
            }                
    }
    
    public void recibirMensajes(){
        getVentanaPrincipal().getTxaMensajes().setText(getMiSistema().getSbMensajes().toString());
    }
    
     private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(ventanaPrincipal, msg, "Error de datos", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void run() {
        while(getMiSistema().isEsperandoConexiones()){
            recibirMensajes();
        }
    }

    void terminarConexion() {
        try {
            getMiSistema().setEsperandoConexiones(false);
            getMiSistema().detenerConexiones();
            
            getVentanaPrincipal().getBtnEscuchar().setText("Escuchar");
            getVentanaPrincipal().getTxtPuerto().setEnabled(true);
        } catch (IOException ex) {
        }
    }
}
