/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.domain;

import java.time.LocalDate;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * @author alber
 */
@Data
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDate fecha;

    private Integer capacidad;

    private Boolean activo;
    
    
}
