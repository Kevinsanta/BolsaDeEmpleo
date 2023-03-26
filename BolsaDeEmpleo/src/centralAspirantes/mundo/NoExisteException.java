package centralAspirantes.mundo;

public class NoExisteException extends Exception {
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción con la cedula del aspirante
     *
     * @param cedula Cedula del aspirante que no está registrado en la central
     */
    public NoExisteException(int cedula) {
        super("El aspirante con cedula " + cedula + " no está registrado");
    }
}
