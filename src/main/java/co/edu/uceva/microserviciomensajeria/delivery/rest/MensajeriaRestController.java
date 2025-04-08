package co.edu.uceva.microserviciomensajeria.delivery.rest;

import co.edu.uceva.microserviciomensajeria.domain.exception.NoHayMensajesException;
import co.edu.uceva.microserviciomensajeria.domain.exception.PaginaSinMensajesException;
import co.edu.uceva.microserviciomensajeria.domain.exception.MensajeNoEncontradoException;
import co.edu.uceva.microserviciomensajeria.domain.exception.ValidationException;
import co.edu.uceva.microserviciomensajeria.domain.model.Mensajeria;
import co.edu.uceva.microserviciomensajeria.domain.services.IMensajeriaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mensajeria-service")
public class MensajeriaRestController {

    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final IMensajeriaService mensajeriaService;

    // Constantes para los mensajes de respuesta
    private static final String MENSAJE = "mensaje";
    private static final String MENSAJERIA = "mensajeria";
    private static final String MENSAJERIAS = "mensajerias";

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    public MensajeriaRestController(IMensajeriaService mensajeriaService) {
        this.mensajeriaService = mensajeriaService;
    }

    /**
     * Listar todos los mensajerias.
     */
    @GetMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> getMensajerias() {
        List<Mensajeria> mensajerias = mensajeriaService.findAll();
        if (mensajerias.isEmpty()) {
            throw new NoHayMensajesException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJERIAS, mensajerias);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar mensajerias con paginación.
     */
    @GetMapping("/mensajeria/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Mensajeria> mensajerias = mensajeriaService.findAll(pageable);
        if (mensajerias.isEmpty()) {
            throw new PaginaSinMensajesException(page);
        }
        return ResponseEntity.ok(mensajerias);
    }

    /**
     * Crear un nuevo mensajeria pasando el objeto en el cuerpo de la petición, usando validaciones
     */
    @PostMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Mensajeria mensajeria, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Mensajeria nuevoMensajeria = mensajeriaService.save(mensajeria);
        response.put(MENSAJE, "El mensaje ha sido creado con éxito!");
        response.put(MENSAJERIA, nuevoMensajeria);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Eliminar un mensaje pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Mensajeria mensajeria) {
        mensajeriaService.findById(mensajeria.getId())
                .orElseThrow(() -> new MensajeNoEncontradoException(mensajeria.getId()));
        mensajeriaService.delete(mensajeria);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El mensaje ha sido eliminado con éxito!");
        response.put(MENSAJERIA, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un mensajeria pasando el objeto en el cuerpo de la petición.
     * @param mensajeria: Objeto Mensajeria que se va a actualizar
     */
    @PutMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Mensajeria mensajeria, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        mensajeriaService.findById(mensajeria.getId())
                .orElseThrow(() -> new MensajeNoEncontradoException(mensajeria.getId()));
        Map<String, Object> response = new HashMap<>();
        Mensajeria mensajeriaActualizado = mensajeriaService.update(mensajeria);
        response.put(MENSAJE, "El mensaje ha sido actualizado con éxito!");
        response.put(MENSAJERIA, mensajeriaActualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un mensajeria por su ID.
     */
    @GetMapping("/mensajerias/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Mensajeria mensajeria = mensajeriaService.findById(id)
                .orElseThrow(() -> new MensajeNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El mensaje ha sido encontrado con éxito!");
        response.put(MENSAJERIA, mensajeria);
        return ResponseEntity.ok(response);
    }
}
