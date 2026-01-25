package ec.com.inventario.Tienda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // RECURSO NO ENCONTRADO (404)
    // =========================
    @ExceptionHandler(RecursoNoEcontradoException.class)
    public ResponseEntity<Object> handleRecursoNoEncontrado(RecursoNoEcontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // =========================
    // RECURSO DUPLICADO (409)
    // =========================
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Object> handleRecursoDuplicado(RecursoDuplicadoException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // =========================
    // LISTA VACÍA (204 o 404)
    // =========================
    @ExceptionHandler(ListaVaciaException.class)
    public ResponseEntity<Object> handleListaVacia(ListaVaciaException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // =========================
    // EXCEPCIÓN DE NEGOCIO GENERAL (400)
    // =========================
    @ExceptionHandler(InventarioException.class)
    public ResponseEntity<Object> handleInventarioException(InventarioException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // =========================
    // VALIDACIONES DTO (@Valid)
    // =========================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidaciones(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo, mensaje);
        });

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errores", errores);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // =========================
    // ERROR GENÉRICO (500)
    // =========================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor"
        );
    }

    // =========================
    // MÉTODO AUXILIAR
    // =========================
    private ResponseEntity<Object> buildResponse(HttpStatus status, String mensaje) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("mensaje", mensaje);

        return new ResponseEntity<>(body, status);
    }
}
