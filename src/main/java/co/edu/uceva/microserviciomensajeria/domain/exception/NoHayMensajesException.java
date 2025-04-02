package co.edu.uceva.microserviciomensajeria.domain.exception;

public class NoHayMensajesException extends RuntimeException {
    public NoHayMensajesException() {
        super("No hay productos en la base de datos.");
    }
}