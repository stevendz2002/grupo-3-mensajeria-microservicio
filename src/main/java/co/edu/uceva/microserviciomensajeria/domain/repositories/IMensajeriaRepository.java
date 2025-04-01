package co.edu.uceva.microserviciomensajeria.domain.repositories;

import co.edu.uceva.microserviciomensajeria.domain.model.Mensajeria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Producto
 */
public interface IMensajeriaRepository extends JpaRepository<Mensajeria, Long> {
}