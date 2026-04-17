/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.repository;


import EjercicioPractico2.domain.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author alber
 */

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByActivoTrue();

    List<Evento> findByNombreContainingIgnoreCase(String nombre);
    
}
