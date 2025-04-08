package co.edu.uceva.microserviciomensajeria.domain.exception;

public class MensajeExistenteException extends RuntimeException {
    public MensajeExistenteException(String nombre) {
        super("El correo con nombre '" + nombre + "' ya existe.");
    }
}
