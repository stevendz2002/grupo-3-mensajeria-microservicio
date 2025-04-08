package co.edu.uceva.microserviciomensajeria.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Mensajeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 2, max = 255, message = "El tamaño tiene que estar entre 2 y 20")
    @Column(nullable = false)
    private String correoDestinatario;

    @NotEmpty(message = "No puede estar vacío")
    @Size(max = 255, message = "El asunto no puede tener más de 255 caracteres")
    private String asunto;

    @NotEmpty(message = "No puede estar vacío")
    @Size(max = 1000, message = "El mensaje no puede tener más de 1000 caracteres")
    private String cuerpoCorreo;

    @NotNull(message = "La fecha de envío es obligatoria")
    @Temporal(TemporalType.DATE)
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate fechaEnvio;
}