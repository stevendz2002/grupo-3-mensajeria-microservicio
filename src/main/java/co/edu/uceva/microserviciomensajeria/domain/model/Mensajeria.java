package co.edu.uceva.microserviciomensajeria.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensajeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 2, max = 20, message = "El tamaño tiene que estar entre 2 y 20")
    @Column(nullable = false)
    private String remitente;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 2, max = 20, message = "El tamaño tiene que estar entre 2 y 20")
    @Column(nullable = false)
    private String destinatario;

    @NotEmpty(message = "No puede estar vacío")
    @Size(max = 255, message = "El asunto no puede tener más de 255 caracteres")
    private String asunto;

    @NotEmpty(message = "No puede estar vacío")
    @Size(max = 1000, message = "El mensaje no puede tener más de 1000 caracteres")
    private String mensaje;

    @NotNull(message = "La fecha de envío es obligatoria")
    @Column(nullable = false)
    private String fechaEnvio;
}