package centralAspirantes.mundo;

public class YaExisteException extends Exception {
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción con la cedula del aspirante
     */
    public YaExisteException(int cedula) {
        super("El aspirante con cedula " + cedula + " ya está registrado");
    }
}