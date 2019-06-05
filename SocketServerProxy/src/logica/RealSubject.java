/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Estudiantes
 */
public class RealSubject implements Subject{

    @Override
    public String Accion(String msg) {
        return "Buenos dias  "+msg;
    }
    
}
