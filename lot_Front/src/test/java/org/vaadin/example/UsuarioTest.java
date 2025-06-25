package org.vaadin.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void getId() {
        Usuario usuario = new Usuario();
        usuario.setId("abc-123");
        assertEquals("abc-123", usuario.getId());
    }

    @Test
    void setId() {
        Usuario usuario = new Usuario();
        usuario.setId("xyz-789");
        assertEquals("xyz-789", usuario.getId());
    }

    @Test
    void getNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Lara");
        assertEquals("Lara", usuario.getNombre());
    }

    @Test
    void setNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Luis");
        assertEquals("Luis", usuario.getNombre());
    }

}