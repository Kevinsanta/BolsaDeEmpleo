package centralAspirantes.interfaz;

import centralAspirantes.mundo.YaExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoInsertarAspirante extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    private final static String AGREGAR = "Agregar";

    private final static String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal
     */
    private InterfazPrincipal principal;

    /**
     * Cedula del aspirante con relación al cual se va a realizar la adición
     */
    private int cedula;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para ingresar la información del aspirante
     */
    private PanelInformacionAspirante panelInformacion;

    /**
     * Botón para agregar un aspirante
     */
    private JButton botonAgregar;

    /**
     * Botón para cancelar la adición del aspirante
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     *
     * @param ventana  Referencia a la ventana principal - ventana!=null
     */
    public DialogoInsertarAspirante(InterfazPrincipal ventana) {
        super(ventana, true);
        principal = ventana;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(400, 307));

        setTitle("Bolsa de Empleo");
        setResizable(true);

        // Panel para ingresar la información
        panelInformacion = new PanelInformacionAspirante();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelInformacion, gbc);

        // Panel con los botones de agregar - cancelar
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        botonAgregar = new JButton();
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        botonAgregar.setIcon(new ImageIcon("data/agregar.gif"));
        botonAgregar.setToolTipText("Agregar Aspirante");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 10);
        panelBotones.add(botonAgregar, gbc);

        botonCancelar = new JButton();
        botonCancelar.setActionCommand(CANCELAR);
        botonCancelar.addActionListener(this);
        botonCancelar.setIcon(new ImageIcon("data/cancelar.gif"));
        botonCancelar.setToolTipText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelBotones.add(botonCancelar, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
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

        if (comando.equals(AGREGAR)) {
            String tituloJOptionPane = "Adicion Aspirante";
            try {
                int cedula = panelInformacion.darCedulaAspirante();
                String nombreCompleto = panelInformacion.darNombreAspirante();
                int edad = panelInformacion.darEdadAspirante();
                int experiencia = panelInformacion.darExperienciaAspirante();
                String profesion = panelInformacion.darProfesionAspirante();
                int telefono = panelInformacion.darTelefonoAspirante();

                if (cedula < 0) {
                    JOptionPane.showMessageDialog(this, "La cedula debe ser un número positivo", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
                }
                else if (nombreCompleto == null || nombreCompleto.equals("")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre completo del aspirante", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
                }
                else if (edad < 18) {
                    JOptionPane.showMessageDialog(this, "La edad debe ser mayor a 18", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
                }
                else if (experiencia < 0) {
                    JOptionPane.showMessageDialog(this, "La experiencia debe ser un numero positivo", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
                }
                else if (profesion == null || profesion.equals("")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar la profesion del aspirante", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
                }
                else if (telefono < 0) {
                    JOptionPane.showMessageDialog(this, "El telefono debe ser un numero positivo", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
                }
                else {
                    principal.agregarAspirante(cedula, nombreCompleto, edad, experiencia, profesion, telefono);
                    principal.refrescarListaAspirantes();
                    dispose();
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cédula debe ser un valor numerico", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
            }
            catch (YaExisteException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "El aspirante con cedula " + cedula + " con relación al cual se va a realizar la adición no se encuentra registrado", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (comando.equals(CANCELAR)) {
            dispose();
        }
    }
}