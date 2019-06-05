/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author estudiantes
 */
class Controlador implements ActionListener{

    private final VistaPrincipal ventana;
    private Modelo sistema;

    public Controlador(VistaPrincipal aThis) {
        ventana = aThis;
        sistema = ventana.getMiModelo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton;
        boton = (JButton) e.getSource();
        if (ventana.getBtnEscuchar().getText().equals("Escuchar")) {
                sistema.esperarConexion();
        }
        else{
                sistema.terminarConexion();
        }
    }
    
}
