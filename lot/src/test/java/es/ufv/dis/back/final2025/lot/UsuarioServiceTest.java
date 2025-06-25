package es.ufv.dis.back.final2025.lot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

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
        Usuario original = servicio.findAll().get(0);
        Usuario modificado = new Usuario();
        modificado.setId(original.getId());
        modificado.setNombre("NuevoNombre");
        modificado.setApellidos(original.getApellidos());
        modificado.setNif(original.getNif());
        modificado.setEmail(original.getEmail());

        ResponseEntity<Usuario> respuesta = servicio.actualizarUsuario(original.getId(), modificado);
        assertEquals("NuevoNombre", servicio.findById(original.getId()).getNombre());
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    void crearUsuario() {
        Usuario nuevo = new Usuario();
        nuevo.setId(UUID.randomUUID().toString());
        nuevo.setNombre("Lara");
        nuevo.setApellidos("Test");
        nuevo.setNif("99999999Z");
        nuevo.setEmail("lara@ufv.es");

        servicio.crearUsuario(nuevo);

        Usuario encontrado = servicio.findById(nuevo.getId());
        assertNotNull(encontrado);
        assertEquals("Lara", encontrado.getNombre());
    }
}