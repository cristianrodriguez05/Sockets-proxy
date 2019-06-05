
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Sistema implements Runnable{
    
    private String nombreHost;
    private int puerto;
    private Socket servidor;
    private StringBuffer mensajes;
    private boolean conectado;
    
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;
    private boolean recibeMensaje;
    private Thread hiloLectura;
    
    public Sistema() {
        conectado = false;
        mensajes = new StringBuffer();        
    }

    public String getNombreHost() {
        return nombreHost;
    }

    public int getPuerto() {
        return puerto;
    }

    public StringBuffer getMensajes() {
        return mensajes;
    }

    public boolean isConectado() {
        return conectado;
    }
    
    
    public void conectar(String host, int puerto) throws IOException{
        // 1. Establecer contacto
        servidor = new Socket(host, puerto);
        
        //2. Capturar flujos(stream)
        datosEntrada = new DataInputStream(servidor.getInputStream());
        datosSalida = new DataOutputStream(servidor.getOutputStream());
        
        conectado = true;
        hiloLectura = new Thread(this);
        hiloLectura.start();
    }
    
    public void enviarMensaje(String msg) throws IOException{
        datosSalida.write(msg.getBytes());
        mensajes.append("ENVIADO:  " + msg + "\n");
        System.out.println(">: " + msg + "\n");
    }

    @Override
    public void run() {
        
        byte buffer[] = new byte[256];
        String msg;
        
        while(conectado){
            try {
                datosEntrada.read(buffer);
                // si llego aqui, es porque algo llego
                msg = new String(buffer);
                mensajes.append("RECIBIDO:  " + msg + "\n");
                recibeMensaje = true;
            } catch (IOException ex) {                
            }            
        }
    }

    public void desconectar() throws IOException {
        conectado = false;
        datosEntrada.close();
        datosSalida.close();
        servidor.close();        
    }

    public boolean isRecibeMensaje() {
        return recibeMensaje;
    }

    public void setRecibeMensaje(boolean recibeMensaje) {
        this.recibeMensaje = recibeMensaje;
    }
    
    
    
}
