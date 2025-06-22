package es.ufv.dis.back.final2025.lot;

import es.ufv.dis.back.final2025.lot.Usuario;
import es.ufv.dis.back.final2025.lot.LectorJSON;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {
        // Creamos una instancia de LectorJSON y leemos los usuarios
        LectorJSON lector = new LectorJSON();
        this.usuarios = lector.leeFicheroJson();
    }

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario findById(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(String id) {
        usuarios.removeIf(u -> u.getId().toString().equals(id));
    }

    public ResponseEntity<Usuario> actualizarUsuario(String id, Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.set(i, usuarioActualizado);
                return ResponseEntity.ok(usuarioActualizado);
            }
        }
        return ResponseEntity.notFound().build();
    }
}