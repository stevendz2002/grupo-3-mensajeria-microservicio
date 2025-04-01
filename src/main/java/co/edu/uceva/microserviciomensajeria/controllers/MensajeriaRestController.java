package co.edu.uceva.microserviciomensajeria.controllers;

import co.edu.uceva.microserviciomensajeria.model.entities.Mensajeria ;
import co.edu.uceva.microserviciomensajeria.model.services.IMensajeriaService ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensajeria-service")
public class MensajeriaRestController {

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    private IMensajeriaService mensajeriaService;

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    @Autowired
    public MensajeriaRestController(IMensajeriaService mensajeriaService) {
        this.mensajeriaService = mensajeriaService;
    }

    // Metodo que retorna todos los mensajerias
    @GetMapping("/mensajerias")
    public List<Mensajeria> getMensajerias(){
        return mensajeriaService.findAll();
    }

    // Metodo que retorna todos los productos paginados
    @GetMapping("/mensajerias/page/{page}")
    public Page<Mensajeria> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return mensajeriaService.findAll(pageable);
    }


    // Metodo que guarda un mensajeria pasandolo por el cuerpo de la petición
    @PostMapping("/mensajerias")
    public Mensajeria save(@RequestBody Mensajeria mensajeria) {
        return mensajeriaService.save(mensajeria);
    }

    // Metodo que elimina un mensajeria pasandolo por el cuerpo de la petición
    @DeleteMapping("/mensajerias")
    public void delete(@RequestBody Mensajeria mensajeria){
        mensajeriaService.delete(mensajeria);
    }

    // Metodo que actualiza un mensajeria pasandolo por el cuerpo de la petición
    @PutMapping("/mensajerias")
    public Mensajeria update(@RequestBody Mensajeria mensajeria){
        return mensajeriaService.update(mensajeria);
    }

    // Metodo que retorna un mensajeria por su id pasado por la URL
    @GetMapping("/mensajerias/{id}")
    public Mensajeria findById(@PathVariable("id") Long id){
        return mensajeriaService.findById(id);
    }


}
