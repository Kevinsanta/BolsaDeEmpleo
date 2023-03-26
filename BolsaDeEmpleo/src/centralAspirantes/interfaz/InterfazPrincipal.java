package centralAspirantes.interfaz;

import centralAspirantes.mundo.Aspirante;
import centralAspirantes.mundo.CentralAspirantes;
import centralAspirantes.mundo.YaExisteException;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InterfazPrincipal extends JFrame {

    /**
     * Panel con la imagen decorativa
     */
    private PanelImagen panelImagen;

    /**
     * Panel con la lista de aspirantes
     */
    private PanelListaAspirantes panelLista;

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Clase principal del mundo
     */
    private CentralAspirantes central;

    public InterfazPrincipal() throws Exception {
        // Crea la clase principal
        central = new CentralAspirantes();

        // Construye la forma
        setLayout(new GridBagLayout());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bolsa de Empleo");
        setResizable(true);

        // Paneles
        panelImagen = new PanelImagen();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelImagen, gbc);

        panelLista = new PanelListaAspirantes(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelLista, gbc);

        panelExtension = new PanelExtension(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelExtension, gbc);

        refrescarListaAspirantes();

        //setLocationRelativeTo(null);

        //pack();
    }

    public void refrescarListaAspirantes() throws Exception {
        panelLista.refrescarLista(central.darAspirantes());
    }

    public void mostrarDialogoOpcionesAgregarAspirante() {
        DialogoInsertarAspirante dialogo = new DialogoInsertarAspirante(this);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    public void agregarAspirante(int cedula, String nombreCompleto, int edad, int experiencia, String profesion, int telefono) throws Exception {
        Aspirante aspirante = new Aspirante(cedula, nombreCompleto, edad, experiencia, profesion, telefono);

        // Se verifica que no exista un aspirante con la cédula proporcionada
        if (central.localizar(cedula) != null) {
            throw new YaExisteException(cedula);
        }
        else {
            central.agregarAspirante(aspirante);
        }
    }

    /**
     * Muestra en un diálogo la información del aspirante
     * @param aspirante El aspirante al que se le va a mostrar la información. aspirante!=null.
     */
    public void mostrarInformacionAspirante(Aspirante aspirante) {
        DialogoMostrarInformacionAspirante dialogo = new DialogoMostrarInformacionAspirante(this, aspirante);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    /**
     * Busca aspiante por cedula
     */
    public void buscarAspirantePorCedula() {
        String tituloJOptionPane = "Buscar Aspirante";
        String cedula = JOptionPane.showInputDialog(this, "Cédula:", tituloJOptionPane, JOptionPane.QUESTION_MESSAGE);
        try {
            if (cedula != null) {
                int ced = Integer.parseInt(cedula);

                Aspirante aspirante = central.localizar(ced);

                if (aspirante != null) {
                    mostrarInformacionAspirante(aspirante);
                }
                else {
                    JOptionPane.showMessageDialog(this, "El aspirante con cédula " + ced + " no se encuentra registrado", tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula aspirante debe ser un valor numérico", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error consultando aspirante en la base de datos", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca aspiante por nombre
     */
    public void buscarAspirantePorNombre() {
        String tituloJOptionPane = "Buscar Aspirante";
        String nombre = JOptionPane.showInputDialog(this, "Nombre Completo:", tituloJOptionPane, JOptionPane.QUESTION_MESSAGE);
        try {
            if (nombre != null) {

                Aspirante aspirante = central.localizarPorNombre(nombre);

                if (aspirante != null) {
                    mostrarInformacionAspirante(aspirante);
                }
                else {
                    JOptionPane.showMessageDialog(this, "El aspirante con nombre " + nombre + " no se encuentra registrado", tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula del aspirante debe ser un valor numérico", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error consultando aspirante en la base de datos", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ordenarPorExperiencia() throws Exception {
        ArrayList<Aspirante> aspirantesOrdernados = central.ordenarPorExperiencia();
        panelLista.refrescarLista(aspirantesOrdernados);
    }

    public void ordenarPorEdad() throws Exception {
        ArrayList<Aspirante> aspirantesOrdernados = central.ordenarPorEdad();
        panelLista.refrescarLista(aspirantesOrdernados);
    }

    public void ordenarPorProfesion() throws Exception {
        ArrayList<Aspirante> aspirantesOrdernados = central.ordenarPorProfesion();
        panelLista.refrescarLista(aspirantesOrdernados);
    }

    /**
     * Pide la cedula del aspirante que se desea contratar
     */
    public void contratarAspirante() {
        String tituloJOptionPane = "Contratar Aspirante";
        String cedula = JOptionPane.showInputDialog(this, "Cédula:", tituloJOptionPane, JOptionPane.QUESTION_MESSAGE);
        try {
            if (cedula != null) {
                int ced = Integer.parseInt(cedula);

                Aspirante aspirante = central.localizar(ced);

                if (aspirante != null) {
                    central.eliminarAspirante(ced);
                    refrescarListaAspirantes();
                    JOptionPane.showMessageDialog(this, "El aspirante con cédula " + ced + " fue contratado", tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "El aspirante con cédula " + ced + " no se encuentra registrado", tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula del aspirante debe ser un valor numérico", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al contratar aspirante", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void obtenerAspiranteConMayorExperiencia() throws SQLException {
        String tituloJOptionPane = "Mayor Experiencia";
        Aspirante aspirante = central.obtenerAspiranteConMayorExperiencia();
        if(aspirante!=null){
            JOptionPane.showMessageDialog(this, "El aspirante con mayor experiencia es " + "[" + aspirante.darCedula() + "]: " + aspirante.darNombreCompleto(), tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "No existe un aspirante con mayor experiencia", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void obtenerAspiranteMasJoven() throws SQLException {
        String tituloJOptionPane = "Más joven";
        Aspirante aspirante = central.obtenerAspiranteMasJoven();
        if(aspirante!=null){
            JOptionPane.showMessageDialog(this, "El aspirante mas joven es " + "[" + aspirante.darCedula() + "]: " + aspirante.darNombreCompleto(), tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "No existe un aspirante mas joven", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarAspirantesConMenosExperiencia(){
        String tituloJOptionPane = "Eliminar Aspirantes";
        String experiencia = JOptionPane.showInputDialog(this, "Años de experiencia:", tituloJOptionPane, JOptionPane.QUESTION_MESSAGE);
        try {
            if (experiencia != null) {
                int exp = Integer.parseInt(experiencia);

                central.eliminarAspirantesConMenosExperiencia(exp);

                refrescarListaAspirantes();

                JOptionPane.showMessageDialog(this, "Se eliminaron los aspirantes con experiencia menor que " + exp + " año(s)", tituloJOptionPane, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El numero de años de experiencia debe ser un valor numérico", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar aspirantes", tituloJOptionPane, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void obtenerPromedioEdadAspirantes(){
        double promedio = central.obtenerPromedioEdadAspirantes();
        JOptionPane.showMessageDialog(this, "El promedio de edad de los aspirantes es " + promedio, "Promedio Aspirantes", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws Exception {
        InterfazPrincipal interfaz = new InterfazPrincipal();
        interfaz.setVisible(true);
    }
}
