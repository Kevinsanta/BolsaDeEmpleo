package centralAspirantes.interfaz;

import centralAspirantes.mundo.Aspirante;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Panel donde se muestra la lista de aspirantes
 */
public class PanelListaAspirantes extends JPanel implements ActionListener, ListSelectionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String AGREGAR = "Agregar Aspirante";

    private static final String BUSCARPORCEDULA = "Buscar por cedula";

    private static final String BUSCARPORNOMBRE = "Buscar por nombre";

    private static final String ORDENARPOREXPERIENCIA = "Ordernar por experiencia";

    private static final String ORDERNARPOREDAD = "Ordernar por edad";

    private static final String ORDERNARPORPROFESION = "Ordernar por profesión";

    private static final String CONTRATAR = "Contratar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal
     */
    private InterfazPrincipal principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
    /**
     * Componente para desplegar la lista de aspirantes
     */
    private JList listaAspirantes;

    /**
     * Barra de desplazamiento de la lista
     */
    private JScrollPane barraDesplazamientoLista;

    /**
     * Botón para insertar un aspirante
     */
    private JButton botonAgregar;

    /**
     * Botón para contratar un aspirante
     */
    private JButton botonContratar;

    /**
     * Botón para buscar un aspirante
     */
    private JButton botonBuscar;

    /**
     * Botón para buscar por nombre un aspirante
     */
    private JButton botonBuscarPorNombre;

    /**
     * Botón para ordenar por experiencia
     */
    private JButton botonOrdernarExperiencia;

    /**
     * Botón para ordenar por edad
     */
    private JButton botonOrdernarEdad;

    /**
     * Botón para ordenar por profesion
     */
    private JButton botonOrdernarProfesion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param ventana Referencia a la ventana principal
     */
    public PanelListaAspirantes(InterfazPrincipal ventana) {
        principal = ventana;

        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Aspirantes"));

        // Lista de aspirantes
        listaAspirantes = new JList<>();
        listaAspirantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAspirantes.addListSelectionListener(this);
        barraDesplazamientoLista = new JScrollPane();
        barraDesplazamientoLista.setViewportView(listaAspirantes);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 10);
        add(barraDesplazamientoLista, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 7, 10, 10));

        // Botones
        botonAgregar = new JButton();
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        botonAgregar.setIcon(new ImageIcon("data/nuevo.gif"));
        botonAgregar.setToolTipText("Agregar Aspirante");
        panelBotones.add(botonAgregar);

        botonBuscar = new JButton();
        botonBuscar.setActionCommand(BUSCARPORCEDULA);
        botonBuscar.addActionListener(this);
        botonBuscar.setIcon(new ImageIcon("data/buscar.gif"));
        botonBuscar.setToolTipText("Buscar Aspirante por cédula");
        panelBotones.add(botonBuscar);

        botonBuscarPorNombre = new JButton();
        botonBuscarPorNombre.setActionCommand(BUSCARPORNOMBRE);
        botonBuscarPorNombre.addActionListener(this);
        botonBuscarPorNombre.setIcon(new ImageIcon("data/buscar.gif"));
        botonBuscarPorNombre.setToolTipText("Buscar Aspirante por nombre");
        panelBotones.add(botonBuscarPorNombre);

        botonOrdernarExperiencia = new JButton();
        botonOrdernarExperiencia.setActionCommand(ORDENARPOREXPERIENCIA);
        botonOrdernarExperiencia.addActionListener(this);
        botonOrdernarExperiencia.setIcon(new ImageIcon("data/ordenar.png"));
        botonOrdernarExperiencia.setToolTipText("Ordenar Aspirante por experiencia");
        panelBotones.add(botonOrdernarExperiencia);

        botonOrdernarEdad = new JButton();
        botonOrdernarEdad.setActionCommand(ORDERNARPOREDAD);
        botonOrdernarEdad.addActionListener(this);
        botonOrdernarEdad.setIcon(new ImageIcon("data/ordenar.png"));
        botonOrdernarEdad.setToolTipText("Ordenar Aspirante por edad");
        panelBotones.add(botonOrdernarEdad);

        botonOrdernarProfesion = new JButton();
        botonOrdernarProfesion.setActionCommand(ORDERNARPORPROFESION);
        botonOrdernarProfesion.addActionListener(this);
        botonOrdernarProfesion.setIcon(new ImageIcon("data/ordenar.png"));
        botonOrdernarProfesion.setToolTipText("Ordenar Aspirante por profesion");
        panelBotones.add(botonOrdernarProfesion);

        botonContratar = new JButton();
        botonContratar.setActionCommand(CONTRATAR);
        botonContratar.addActionListener(this);
        botonContratar.setIcon(new ImageIcon("data/eliminar.gif"));
        botonContratar.setToolTipText("Contratar Aspirante");
        panelBotones.add(botonContratar);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(panelBotones, gbc);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de aspirantes en la interfaz
     */
    public void refrescarLista(ArrayList<Aspirante> aspirantes) {
        Object[] objects = aspirantes.toArray();
        listaAspirantes.setListData(objects);
    }

    /**
     * Retorna el aspirante que se encuentra seleccionado de la lista. Si no hay un aspirante seleccionado se retorna null.
     */
    private Aspirante darAspiranteSeleccionado() {
        return (Aspirante) listaAspirantes.getSelectedValue();

    }

    /**
     * Manejo de eventos de los botones
     *
     * @param e Evento que generó la acción - e != null.
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals(AGREGAR)) {
                principal.mostrarDialogoOpcionesAgregarAspirante();
            }
            else if (comando.equals(BUSCARPORCEDULA)) {
                principal.buscarAspirantePorCedula();
            }
            else if (comando.equals(BUSCARPORNOMBRE)) {
                principal.buscarAspirantePorNombre();
            }
            else if (comando.equals(ORDENARPOREXPERIENCIA)) {
                principal.ordenarPorExperiencia();
            }
            else if (comando.equals(ORDERNARPOREDAD)) {
                principal.ordenarPorEdad();
            }
            else if (comando.equals(ORDERNARPORPROFESION)) {
                principal.ordenarPorProfesion();
            }
            else if (comando.equals(CONTRATAR)) {
                principal.contratarAspirante();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * Muestra la información del aspirante seleccionado
     *
     * @param e El evento de cambio del ítem seleccionado en la lista
     */
    public void valueChanged(ListSelectionEvent e) {
        Aspirante aspiranteSeleccionado = darAspiranteSeleccionado();
        if (darAspiranteSeleccionado() != null) {
            principal.mostrarInformacionAspirante(aspiranteSeleccionado);
        }
    }
}
