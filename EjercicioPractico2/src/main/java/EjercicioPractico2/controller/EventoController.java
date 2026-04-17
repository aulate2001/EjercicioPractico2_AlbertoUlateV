/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.controller;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import EjercicioPractico2.domain.Evento;
import EjercicioPractico2.service.EventoService;

/**
 *
 * @author alber
 */

@Controller
@RequestMapping("/evento")
public class EventoController {
   
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        model.addAttribute("totalEventos", eventos.size());
        return "/evento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("evento", new Evento());
        return "/evento/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Evento evento) {
        eventoService.save(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {

        Optional<Evento> evento = eventoService.getEvento(id);

        if (evento.isPresent()) {
            model.addAttribute("evento", evento.get());
            return "/evento/modifica";
        }

        return "redirect:/evento/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        eventoService.delete(id);
        return "redirect:/evento/listado";
    }
}
