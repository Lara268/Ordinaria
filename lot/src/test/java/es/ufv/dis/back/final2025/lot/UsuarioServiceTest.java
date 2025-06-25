package es.ufv.dis.back.final2025.lot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {
    private UsuarioService servicio;

    @BeforeEach
    void setUp() {
        servicio = new UsuarioService();
    }

    @Test
    void findAll() {
        List<Usuario> usuarios = servicio.findAll();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void findById() {
        Usuario u = servicio.findAll().get(0);
        Usuario encontrado = servicio.findById(u.getId());
        assertNotNull(encontrado);
        assertEquals(u.getNombre(), encontrado.getNombre());
    }

    @Test
    void actualizarUsuario() {
    }

    @Test
    void crearUsuario() {
    }
}