package co.edu.uceva.microserviciomensajeria.domain.exception;

public class MensajeExistenteException extends RuntimeException {
    public MensajeExistenteException(String nombre) {
        super("El producto con nombre '" + nombre + "' ya existe.");
    }
}
