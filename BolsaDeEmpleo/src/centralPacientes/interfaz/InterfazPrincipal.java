package centralPacientes.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InterfazPrincipal extends JFrame {
    public InterfazPrincipal(){

        // Construye la forma
        setLayout(new GridBagLayout());
        setSize(370, 347);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bolsa de Empleo");
        setResizable(false);
    }

    public static void main(String[] args) {
        InterfazPrincipal interfaz = new InterfazPrincipal();
        interfaz.setVisible(true);
    }
}
