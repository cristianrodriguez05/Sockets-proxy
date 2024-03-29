/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Estudiantes
 */
public class Cliente extends Thread implements Runnable{

    private final String clienteConectado;
    private final DataInputStream datosEntrada;
    private final DataOutputStream datosSalida;
    private final Socket cliente;

    private boolean conectado;    
    private Thread hiloLectura;       
    private final SistemaServer sistemaServer;
    Proxy p = new Proxy();
    

    
    public Cliente(SistemaServer aThis, Socket cliente) throws IOException {
        this.sistemaServer = aThis;
        this.cliente = cliente;
        clienteConectado = cliente.getInetAddress().getHostAddress();        
        datosEntrada = new DataInputStream(cliente.getInputStream());
        datosSalida = new DataOutputStream(cliente.getOutputStream());      
        hiloLectura = new Thread(this);   
        conectado = true;
        hiloLectura.start();
    }
    
    private void leerMensaje() throws IOException 
    // Es un mensaje que este cliente envía al servidor. Debemos replicarlo a los demás clientes
    {
        byte buffer[] = new byte[256];
        String mensaje;
        datosEntrada.read(buffer);
        mensaje = new String(buffer);
        sistemaServer.getSbMensajes().append("Se recibe de " + cliente.getInetAddress().getHostAddress() + ": " + mensaje + "\n");
        System.out.println("Se recibe de " + cliente.getInetAddress().getHostAddress() + ": " + mensaje + "\n");
        // replicamos a los demás clientes este mensaje
        String cadena = mensaje.trim();
        String[] recibido = cadena.split(",");
        String orden =(recibido[0]);
        String nombre =(recibido[1]);
        System.out.println("llego aqui1");
        if( orden.equals("saludar")){
            sistemaServer.enviarMensaje(cliente.getInetAddress().getHostAddress() + ": " + p.Accion(nombre));
            System.out.println("llego aqui2");
        }
    }
    
    public void enviarMensaje(String msg) throws IOException {
        datosSalida.write(msg.getBytes());
        //mensajes.append("Enviamos: " + msg + "\n");
    }
    
    public void terminarConexiones() throws IOException{
        conectado = false;
        hiloLectura = null;
        datosEntrada.close();
        datosSalida.close();
        cliente.close();
    }

    @Override
    public void run() {
        while(conectado){
            try {
                leerMensaje();
            } catch (IOException ex) {
            }
        }
    }
    
    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public String getClienteConectado() {
        return clienteConectado;
    }

    
    
}
