package ec.com.inventario.Tienda.exception;

public class RecursoNoEcontradoException extends InventarioException {
    public RecursoNoEcontradoException(String message) {
        super(message);
    }

    public RecursoNoEcontradoException(String message, Throwable cause) {
        super(message, cause);
    }


}
