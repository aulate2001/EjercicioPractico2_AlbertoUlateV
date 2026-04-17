/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.service;


import EjercicioPractico2.domain.Evento;
import EjercicioPractico2.repository.EventoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author alber
 */

@Service
public class EventoService {
    
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Transactional(readOnly = true)
    public List<Evento> getEventos() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Evento> getEventosActivos() {
        return eventoRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public Optional<Evento> getEvento(Long id) {
        return eventoRepository.findById(id);
    }

    @Transactional
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Transactional
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }
}
