package es.ufv.dis.back.final2025.lot;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    final String baseUrl = "http://localhost:8081/api/usuarios";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getUsuarios() {
        ResponseEntity<Usuario[]> response = restTemplate.getForEntity(baseUrl, Usuario[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void getUsuario() {
        String id = "aba5b753-d91b-4482-b93d-cb1b1d63bb16";
        ResponseEntity<Usuario> response = restTemplate.getForEntity(baseUrl + "/" + id, Usuario.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void actualizarUsuario() {
        // Este ID debe existir o haber sido creado antes
        String id = "aba5b753-d91b-4482-b93d-cb1b1d63bb16";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Usuario actualizado = new Usuario();
        actualizado.setId(id);
        actualizado.setNombre("Modificado");
        actualizado.setApellidos("Caballero");
        actualizado.setNif("70151725T");
        actualizado.setEmail("nuevo@correo.com");

        HttpEntity<Usuario> request = new HttpEntity<>(actualizado, headers);
        restTemplate.put(baseUrl + "/" + id, request);

        ResponseEntity<Usuario> response = restTemplate.getForEntity(baseUrl + "/" + id, Usuario.class);
        assertEquals("Modificado", response.getBody().getNombre());
    }

    @Test
    void crearUsuario() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Usuario nuevo = new Usuario();
        nuevo.setId(UUID.randomUUID().toString());
        nuevo.setNombre("Test");
        nuevo.setApellidos("User");
        nuevo.setNif("99999999Z");
        nuevo.setEmail("test@example.com");

        HttpEntity<Usuario> request = new HttpEntity<>(nuevo, headers);
        ResponseEntity<Usuario> response = restTemplate.postForEntity(baseUrl, request, Usuario.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(nuevo.getNombre(), response.getBody().getNombre());
    }
}