package centralAspirantes.interfaz;

import centralAspirantes.mundo.Aspirante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoMostrarInformacionAspirante extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    private final static String CERRAR = "Cerrar";
    private final static String REGISTRAR_CAMBIOS = "Registrar Cambios";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal
     */
    private InterfazPrincipal principal;

    /**
     * El aspirante del que se va a mostrar la información
     */
    private Aspirante aspirante;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para mostrar la información del aspirante
     */
    private PanelInformacionAspirante panelInformacion;

    /**
     * Botón para cerrar el diálogo
     */
    private JButton botonCerrar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     *
     * @param ventana    Referencia a la ventana principal - ventana!=null
     * @param aspirante El aspirante del que se va a mostrar la información - aspirante!=null
     */
    public DialogoMostrarInformacionAspirante(InterfazPrincipal ventana, Aspirante aspirante) {
        super(ventana, true);
        principal = ventana;
        this.aspirante = aspirante;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(298, 307));
        setTitle("Búsqueda Aspirantes");
        setResizable(false);

        // Panel para ingresar la información
        panelInformacion = new PanelInformacionAspirante();
        GridBagConstraints gbc = new GridBagConstraints();
        panelInformacion.deshabilitarComponentes();
        panelInformacion.mostrarInformacionAspirante(aspirante);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelInformacion, gbc);

        // Panel con el botón de cerrar y actualizar
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        botonCerrar = new JButton(CERRAR);
        botonCerrar.setActionCommand(CERRAR);
        botonCerrar.addActionListener(this);
        panelBotones.add(botonCerrar, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelBotones, gbc);

        pack();

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos de los botones
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals(CERRAR)) {
            dispose();
        }
    }
}