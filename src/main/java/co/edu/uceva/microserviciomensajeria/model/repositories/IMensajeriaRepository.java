package co.edu.uceva.microserviciomensajeria.model.repositories;

import co.edu.uceva.microserviciomensajeria.model.entities.Mensajeria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Producto
 */
public interface IMensajeriaRepository extends JpaRepository<Mensajeria, Long> {
}