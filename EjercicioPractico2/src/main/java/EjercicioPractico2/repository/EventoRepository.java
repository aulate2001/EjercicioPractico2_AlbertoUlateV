/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.repository;


import EjercicioPractico2.domain.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author alber
 */

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByActivoTrue();

    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    List<Evento> findByCapacidadBetween(int min, int max);

    @Query("SELECT e FROM Evento e WHERE e.capacidad BETWEEN ?1 AND ?2")
    List<Evento> findByCapacidadBetweenJPQL(int min, int max);

    @Query(value = "SELECT * FROM evento WHERE capacidad BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Evento> findByCapacidadBetweenNative(int min, int max);
    
}
