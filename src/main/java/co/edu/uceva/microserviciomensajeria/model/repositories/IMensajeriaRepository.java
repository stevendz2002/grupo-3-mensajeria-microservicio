package co.edu.uceva.microserviciomensajeria.model.repositories;

import co.edu.uceva.microserviciomensajeria.model.entities.Mensajeria;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface que hereda de CrudRepository para realizar las
 * operaciones de CRUD sobre la entidad Producto
 */
public interface IMensajeriaRepository extends CrudRepository<Mensajeria, Long> {
}
