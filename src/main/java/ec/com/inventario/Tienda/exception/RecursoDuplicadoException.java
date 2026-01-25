package ec.com.inventario.Tienda.exception;

public class RecursoDuplicadoException extends InventarioException {
    public RecursoDuplicadoException(String message) {
        super(message);
    }

    public RecursoDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
