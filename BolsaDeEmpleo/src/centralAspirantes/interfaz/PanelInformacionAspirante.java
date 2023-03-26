package centralAspirantes.interfaz;

import centralAspirantes.mundo.Aspirante;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelInformacionAspirante extends JPanel {

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Texto para el nombre del aspirante
     */
    private JTextField textoCedula;

    /**
     * Texto para el nombre del aspirante
     */
    private JTextField textoNombreCompleto;

    /**
     * Texto para la edad del aspirante
     */
    private JTextField textoEdad;

    /**
     * Texto para la experiencia del aspirante
     */
    private JTextField textoExperiencia;

    /**
     * Texto para la profesion del aspirante
     */
    private JTextField textoProfesion;

    /**
     * Texto para el telefono del aspirante
     */
    private JTextField textoTelefono;

    /**
     * Etiqueta para la cedula
     */
    private JLabel etiquetaCedula;

    /**
     * Etiqueta para el nombre
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta para la edad
     */
    private JLabel etiquetaEdad;

    /**
     * Etiqueta para la experiencia
     */
    private JLabel etiquetaExperiencia;

    /**
     * Etiqueta para la profesion
     */
    private JLabel etiquetaProfesion;

    /**
     * Etiqueta para el telefono
     */
    private JLabel etiquetaTelefono;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     */
    public PanelInformacionAspirante() {
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Información del Aspirante"));

        // Cedula del aspirante
        etiquetaCedula = new JLabel("Cedula");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaCedula, gbc);

        textoCedula = new JTextField();
        textoCedula.setColumns(10);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(textoCedula, gbc);

        // Nombre del aspirante
        etiquetaNombre = new JLabel("Nombre");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaNombre, gbc);

        textoNombreCompleto = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(textoNombreCompleto, gbc);

        // Edad del aspirante
        etiquetaEdad = new JLabel("Edad");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaEdad, gbc);

        textoEdad = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(textoEdad, gbc);

        // Experiencia del aspirante
        etiquetaExperiencia = new JLabel("Experiencia");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaExperiencia, gbc);

        textoExperiencia = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(textoExperiencia, gbc);

        // Profesion del aspirante
        etiquetaProfesion = new JLabel("Profesion");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaProfesion, gbc);

        textoProfesion = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(textoProfesion, gbc);

        // Telefono del aspirante
        etiquetaTelefono = new JLabel("Telefono");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaTelefono, gbc);

        textoTelefono = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        add(textoTelefono, gbc);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Habilita todos los componentes del panel
     */
    public void habilitarComponentes() {
        textoCedula.setEditable(true);
        textoNombreCompleto.setEditable(true);
        textoEdad.setEnabled(true);
        textoExperiencia.setEnabled(true);
        textoProfesion.setEditable(true);
        textoTelefono.setEnabled(true);
    }

    /**
     * Deshabilita todos los componentes del panel
     */
    public void deshabilitarComponentes() {
        textoCedula.setEditable(false);
        textoNombreCompleto.setEditable(false);
        textoEdad.setEnabled(false);
        textoExperiencia.setEnabled(false);
        textoProfesion.setEditable(false);
        textoTelefono.setEnabled(false);
    }

    /**
     * Retorna la cedula del aspirante que se va a insertar
     */
    public int darCedulaAspirante() throws NumberFormatException {
        String cadena = textoCedula.getText();
        int ced = Integer.parseInt(cadena);
        return ced;
    }

    /**
     * Retorna el nombre del aspirante que se va a insertar
     */
    public String darNombreAspirante() {
        return textoNombreCompleto.getText();
    }

    /**
     * Retorna la edad del aspirante que se va a insertar
     */
    public int darEdadAspirante() throws NumberFormatException {
        String cadena = textoEdad.getText();
        int edad = Integer.parseInt(cadena);
        return edad;
    }

    /**
     * Retorna experiencia del aspirante que se va a insertar
     */
    public int darExperienciaAspirante() throws NumberFormatException {
        String cadena = textoExperiencia.getText();
        int exp = Integer.parseInt(cadena);
        return exp;
    }

    /**
     * Retorna telefono del aspirante que se va a insertar
     */
    public int darTelefonoAspirante() throws NumberFormatException {
        String cadena = textoTelefono.getText();
        int tel = Integer.parseInt(cadena);
        return tel;
    }

    /**
     * Retorna profesion del aspirante que se va a insertar
     */
    public String darProfesionAspirante() {
        return textoProfesion.getText();
    }

    /**
     * Muestra la información del aspirante
     */
    public void mostrarInformacionAspirante(Aspirante aspirante) {
        textoCedula.setText(Integer.toString(aspirante.darCedula()));
        textoNombreCompleto.setText(aspirante.darNombreCompleto());
        textoEdad.setText(Integer.toString(aspirante.darEdad()));
        textoExperiencia.setText(Integer.toString(aspirante.darExperiencia()));
        textoProfesion.setText(aspirante.darProfesion());
        textoTelefono.setText(Integer.toString(aspirante.darTelefono()));
    }
}
