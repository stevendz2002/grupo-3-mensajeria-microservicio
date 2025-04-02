package co.edu.uceva.microserviciomensajeria.domain.services;

import co.edu.uceva.microserviciomensajeria.domain.model.Mensajeria;
import co.edu.uceva.microserviciomensajeria.domain.repositories.IMensajeriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa los métodos de la interfaz IMensajeriaService
 * para realizar las operaciones de negocio sobre la entidad Mensajeria
 */
@Service
public class MensajeriaServiceImpl implements IMensajeriaService {

    IMensajeriaRepository mensajeriaRepository;

    public MensajeriaServiceImpl(IMensajeriaRepository mensajeriaRepository) {
        this.mensajeriaRepository = mensajeriaRepository;
    }

    @Override
    @Transactional
    public Mensajeria save(Mensajeria mensajeria) {
        return mensajeriaRepository.save(mensajeria);
    }

    @Override
    @Transactional
    public void delete(Mensajeria mensajeria) {
        mensajeriaRepository.delete(mensajeria);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mensajeria> findById(Long id) {
        return mensajeriaRepository.findById(id);
    }

    @Override
    @Transactional
    public Mensajeria update(Mensajeria mensajeria) {
        return mensajeriaRepository.save(mensajeria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mensajeria> findAll() {
        return (List<Mensajeria>) mensajeriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Mensajeria> findAll(Pageable pageable) {
        return mensajeriaRepository.findAll(pageable);
    }
}
