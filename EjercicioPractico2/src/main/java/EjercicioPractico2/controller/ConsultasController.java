package EjercicioPractico2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EjercicioPractico2.service.EventoService;

/**
 *
 * @author alber
 */

@Controller
@RequestMapping("/consultas")
public class ConsultasController {

    private final EventoService eventoService;

    public ConsultasController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public String listado(Model model) {
        var eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        model.addAttribute("totalEventos", eventos.size());
        return "consultas/listado";
    }

    @GetMapping("/{id}")
    public String listadoPorCategoria(@PathVariable Long id, Model model) {

        var eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        model.addAttribute("idCategoriaActual", id);
        return "consultas/listado";
    }

    @PostMapping("/consultaDerivada")
    public String consultaDerivada(@RequestParam Integer capacidadInf, @RequestParam Integer capacidadSup, Model model) {

        var eventos = eventoService.getEventos().stream()
            .filter(e -> e.getCapacidad() >= capacidadInf && e.getCapacidad() <= capacidadSup)
            .toList();
        model.addAttribute("eventos", eventos);
        model.addAttribute("capacidadInf", capacidadInf);
        model.addAttribute("capacidadSup", capacidadSup);
        return "consultas/listado";
    }

    @PostMapping("/consultaJPQL")
    public String consultaJPQL(@RequestParam Integer capacidadInf, @RequestParam Integer capacidadSup, Model model) {

        var eventos = eventoService.getEventosByCapacidadBetween(capacidadInf, capacidadSup);
        model.addAttribute("eventos", eventos);
        model.addAttribute("capacidadInf", capacidadInf);
        model.addAttribute("capacidadSup", capacidadSup);
        return "consultas/listado";
    }

    @PostMapping("/consultaSQL")
    public String consultaSQL(@RequestParam Integer capacidadInf, @RequestParam Integer capacidadSup, Model model) {

        var eventos = eventoService.getEventosByCapacidadBetweenNative(capacidadInf, capacidadSup);
        model.addAttribute("eventos", eventos);
        model.addAttribute("capacidadInf", capacidadInf);
        model.addAttribute("capacidadSup", capacidadSup);
        return "consultas/listado";
    }

    @PostMapping("/consultaStock")
    public String consultaStock(@RequestParam Integer stock, Model model) {
        var eventos = stock == 1 ? eventoService.getEventosActivos() : eventoService.getEventos().stream().filter(e -> !e.getActivo()).toList();
        model.addAttribute("eventos", eventos);
        model.addAttribute("stockSeleccionado", stock);
        return "consultas/listado";
    }
}