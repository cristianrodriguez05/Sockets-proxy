

package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;



class Controlador implements ActionListener{

    private final VistaInicial ventana;

    public Controlador(VistaInicial aThis) {
        ventana = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton;
        boton = (JButton)e.getSource();
        
        if(boton == ventana.getBtnConectar()){
            if(boton.getText().equals("Conectar")){
                ventana.getModelo().conectar();
            }else{
                ventana.getModelo().desconectar();
            }
        }else{
            ventana.getModelo().enviarMensaje();
        }
    }    
}
