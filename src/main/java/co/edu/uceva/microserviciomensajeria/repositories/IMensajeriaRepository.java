package co.edu.uceva.microserviciomensajeria.repositories;

import co.edu.uceva.microserviciomensajeria.entities.mensajeria;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface que hereda de CrudRepository para realizar las
 * operaciones de CRUD sobre la entidad Producto
 */
public interface IMensajeriaRepository extends CrudRepository<mensajeria, Long> {
}
