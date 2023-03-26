package centralAspirantes.interfaz;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelExtension extends JPanel implements ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_MAYOR_EXPERIENCIA = "MAYOREXPERIENCIA";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_MAS_JOVEN = "MASJOVEN";

    /**
     * Comando Opción 3
     */
    private static final String OPCION_ELIMINAR_MENOS_EXPERIENCIA = "ELIMINARMENOSEXPERIENCIA";

    /**
     * Comando Opción 4
     */
    private static final String OPCION_PROMEDIO = "PROMEDIO";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazPrincipal principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnMayorExperiencia;

    /**
     * Botón Opción 2
     */
    private JButton btnMasJoven;

    /**
     * Botón Opción 3
     */
    private JButton btnEliminarMenosExperiencia;

    /**
     * Botón Opción 4
     */
    private JButton btnPromedio;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param ventana Ventana principal
     */
    public PanelExtension(InterfazPrincipal ventana) {
        principal = ventana;

        setBorder(new TitledBorder("Opciones"));
        setLayout(new GridBagLayout());

        // Botón opción 1
        btnMayorExperiencia = new JButton("Mayor Experiencia");
        btnMayorExperiencia.setActionCommand(OPCION_MAYOR_EXPERIENCIA);
        btnMayorExperiencia.addActionListener(this);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnMayorExperiencia, gbc);

        // Botón opción 2
        btnMasJoven = new JButton("Más Joven");
        btnMasJoven.setActionCommand(OPCION_MAS_JOVEN);
        btnMasJoven.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnMasJoven, gbc);

        // Botón opción 3
        btnEliminarMenosExperiencia = new JButton("Eliminar menos experiencia");
        btnEliminarMenosExperiencia.setActionCommand(OPCION_ELIMINAR_MENOS_EXPERIENCIA);
        btnEliminarMenosExperiencia.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnEliminarMenosExperiencia, gbc);

        // Botón opción 4
        btnPromedio = new JButton("Promedio Edad");
        btnPromedio.setActionCommand(OPCION_PROMEDIO);
        btnPromedio.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(btnPromedio, gbc);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     *
     * @param e Acción que generó el evento.
     */
    public void actionPerformed(ActionEvent e) {
        try {
            if (OPCION_MAYOR_EXPERIENCIA.equals(e.getActionCommand())) {
                principal.obtenerAspiranteConMayorExperiencia();
            }
            else if (OPCION_MAS_JOVEN.equals(e.getActionCommand())) {
                principal.obtenerAspiranteMasJoven();
            }
            else if (OPCION_ELIMINAR_MENOS_EXPERIENCIA.equals(e.getActionCommand())) {
                principal.eliminarAspirantesConMenosExperiencia();
            }
            else if (OPCION_PROMEDIO.equals(e.getActionCommand())) {
                principal.obtenerPromedioEdadAspirantes();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
