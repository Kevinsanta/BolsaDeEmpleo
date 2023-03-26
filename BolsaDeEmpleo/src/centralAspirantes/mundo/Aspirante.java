package centralAspirantes.mundo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "aspirante")
public class Aspirante {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La cedula del aspirante
     */
    @DatabaseField(id = true)
    private int cedula;

    /**
     * El nombre completo del aspirante
     */
    @DatabaseField(canBeNull = false)
    private String nombreCompleto;

    /**
     * Edad del aspirante
     */
    @DatabaseField(canBeNull = false)
    private int edad;

    /**
     * Experiencia en años del aspirante
     */
    @DatabaseField(canBeNull = false)
    private int experiencia;

    /**
     * Profesion del aspirante
     */
    @DatabaseField(canBeNull = false)
    private String profesion;

    /**
     * Telefono del aspirante
     */
    @DatabaseField(canBeNull = false)
    private int telefono;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un aspirante
     */

    //COntructor vacio requerido por ORM Lite
    public Aspirante(){

    }

    public Aspirante(int cedula, String nombreCompleto, int edad, int experiencia, String profesion, int telefono) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.experiencia = experiencia;
        this.profesion = profesion;
        this.telefono = telefono;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    public int darCedula() {
        return cedula;
    }
    public String darNombreCompleto() {
        return nombreCompleto;
    }
    public int darEdad() {
        return edad;
    }
    public int darExperiencia() {
        return experiencia;
    }
    public String darProfesion() {
        return profesion;
    }
    public int darTelefono() {
        return telefono;
    }

    /**
     * Retorna una cadena con la información del aspirante
     */
    public String toString() {
        return "[ " + cedula + " ]: " + nombreCompleto + "," + edad + "," + experiencia + "," + profesion + "," + telefono;
    }
}
