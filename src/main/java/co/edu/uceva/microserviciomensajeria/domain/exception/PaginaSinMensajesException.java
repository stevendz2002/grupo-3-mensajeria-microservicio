package co.edu.uceva.microserviciomensajeria.domain.exception;

public class PaginaSinMensajesException extends RuntimeException {
    public PaginaSinMensajesException(int page) {
        super("No hay correos en la página solicitada: " + page);
    }
}