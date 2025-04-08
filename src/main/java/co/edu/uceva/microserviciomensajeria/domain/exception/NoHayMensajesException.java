package co.edu.uceva.microserviciomensajeria.domain.exception;

public class NoHayMensajesException extends RuntimeException {
    public NoHayMensajesException() {
        super("No hay correo en la base de datos.");
    }
}