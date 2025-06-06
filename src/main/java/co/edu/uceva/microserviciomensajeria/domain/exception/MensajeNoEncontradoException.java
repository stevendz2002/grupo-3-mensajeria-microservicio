package co.edu.uceva.microserviciomensajeria.domain.exception;

public class MensajeNoEncontradoException extends RuntimeException {
    public MensajeNoEncontradoException(Long id) {
        super("El correo con ID " + id + " no fue encontrado.");
    }
}