package es.ufv.dis.back.final2025.lot;

import es.ufv.dis.back.final2025.lot.Usuario;
import es.ufv.dis.back.final2025.lot.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable String id) {
        return servicio.findById(id);
    }

//    @DeleteMapping("/{id}")
//    public void deleteUsuario(@PathVariable String id) {
//        servicio.delete(id);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuarioActualizado) {
        return servicio.actualizarUsuario(id, usuarioActualizado);
    }
}