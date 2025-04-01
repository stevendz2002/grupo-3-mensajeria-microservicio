package co.edu.uceva.microserviciomensajeria.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class mensajeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String remitente;
    private String destinatario;
    private String asunto;
    private String mensaje;
    private String fechaEnvio;
}
