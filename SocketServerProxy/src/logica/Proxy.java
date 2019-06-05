/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Estudiantes
 */
public class Proxy implements Subject{
    private int saludos;

    public Proxy() {
        saludos = 0;
    }
    
    

    @Override
    public String Accion(String msg) {
        RealSubject r = new RealSubject();
        saludos++;
        return " "+r.Accion(msg)+".....El servidor ha saludado"+saludos+" vece(s)";
    }
    
}
