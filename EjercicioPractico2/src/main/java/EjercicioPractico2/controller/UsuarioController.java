/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.controller;

import EjercicioPractico2.domain.Evento;
import EjercicioPractico2.domain.Usuario;
import EjercicioPractico2.service.EventoService;
import EjercicioPractico2.service.UsuarioService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author alber
 */

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "/usuario/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/listado";
    }

    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {

        Optional<Usuario> usuario = usuarioService.getUsuario(id);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "/usuario/modifica";
        }

        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.delete(id);
        return "redirect:/usuario/listado";
    }
    
}
