package co.edu.uceva.microserviciomensajeria.delivery.rest;

import jakarta.validation.Valid;
import co.edu.uceva.microserviciomensajeria.domain.model.Mensajeria ;
import co.edu.uceva.microserviciomensajeria.domain.services.IMensajeriaService;
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

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String CORREO = "correo";
    private static final String CORREOS = "correos";

    public MensajeriaRestController(IMensajeriaService mensajeriaService) {
        this.mensajeriaService = mensajeriaService;
    }

    /**
     * Listar todos los mensajerias.
     */
    @GetMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> getMensajerias() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Mensajeria> mensajerias = mensajeriaService.findAll();

            if (mensajerias.isEmpty()) {
                response.put(MENSAJE, "No hay correos en la base de datos.");
                response.put(CORREOS, mensajerias); // para que sea siempre el mismo campo
                return ResponseEntity.status(HttpStatus.OK).body(response); // 200 pero lista vacía
            }

            response.put(CORREOS, mensajerias);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Listar mensajerias con paginación.
     */
    @GetMapping("/mensajeria/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<Mensajeria> mensajerias = mensajeriaService.findAll(pageable);

            if (mensajerias.isEmpty()) {
                response.put(MENSAJE, "No hay mensajerias en la página solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(mensajerias);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (IllegalArgumentException e) {
            response.put(MENSAJE, "Número de página inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Crear un nuevo mensajer pasando el objeto en el cuerpo de la petición, usando validaciones.
     */
    @PostMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Mensajeria mensajeria,BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Guardar el mensaje en la base de datos
            Mensajeria nuevoMensajeria = mensajeriaService.save(mensajeria);

            response.put(MENSAJE, "El mensajeria ha sido creado con éxito!");
            response.put(CORREO, nuevoMensajeria);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar el mensajeria en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    /**
     * Eliminar un mensajeria pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Mensajeria mensajeria) {
        Map<String, Object> response = new HashMap<>();
        try {
            Mensajeria mensajeriaExistente = mensajeriaService.findById(mensajeria.getId());
            if (mensajeriaExistente == null) {
                response.put(MENSAJE, "El mensajeria ID: " + mensajeria.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            mensajeriaService.delete(mensajeria);
            response.put(MENSAJE, "El mensajeria ha sido eliminado con éxito!");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al eliminar el mensajeria de la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Actualizar un mensajeria pasando el objeto en el cuerpo de la petición.
     * @param mensajeria: Objeto Mensajeria que se va a actualizar
     */
    @PutMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Mensajeria mensajeria, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Verificar si el mensajeria existe antes de actualizar
            if (mensajeriaService.findById(mensajeria.getId()) == null) {
                response.put(MENSAJE, "Error: No se pudo editar, el mensajeria ID: " + mensajeria.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Guardar directamente el mensajeria actualizado en la base de datos
            Mensajeria mensajeriaActualizado = mensajeriaService.save(mensajeria);

            response.put(MENSAJE, "El mensajeria ha sido actualizado con éxito!");
            response.put(CORREO, mensajeriaActualizado);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al actualizar el mensajeria en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener un mensajeria por su ID.
     */
    @GetMapping("/mensajerias/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Mensajeria mensajeria = mensajeriaService.findById(id);

            if (mensajeria == null) {
                response.put(MENSAJE, "El mensajeria ID: " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put(MENSAJE, "El mensajeria ha sido actualizado con éxito!");
            response.put(CORREO, mensajeria);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
