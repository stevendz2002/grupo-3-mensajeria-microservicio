package co.edu.uceva.microserviciomensajeria.model.services;


import co.edu.uceva.microserviciomensajeria.model.entities.Mensajeria;

import java.util.List;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Producto
 */
public interface IMensajeriaService {
    Mensajeria save(Mensajeria mensajeria);
    void delete(Mensajeria mensajeria);
    Mensajeria findById(Long id);
    Mensajeria update(Mensajeria mensajeria);
    List<Mensajeria> findAll();
}
