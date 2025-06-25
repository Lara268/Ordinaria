package es.ufv.dis.back.final2025.lot;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    }

    @Test
    void crearUsuario() {
    }
}