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

    @Test
    void getApellidos() {
        Usuario usuario = new Usuario();
        usuario.setApellidos("García");
        assertEquals("García", usuario.getApellidos());
    }

    @Test
    void setApellidos() {
        Usuario usuario = new Usuario();
        usuario.setApellidos("Fernández");
        assertEquals("Fernández", usuario.getApellidos());
    }

    @Test
    void getNif() {
        Usuario usuario = new Usuario();
        usuario.setNif("12345678Z");
        assertEquals("12345678Z", usuario.getNif());
    }

    @Test
    void setNif() {
        Usuario usuario = new Usuario();
        usuario.setNif("87654321X");
        assertEquals("87654321X", usuario.getNif());
    }

    @Test
    void getDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Calle Falsa");
        direccion.setNumero(123);
        direccion.setCodigoPostal("28080");
        direccion.setPisoLetra("3B");
        direccion.setCiudad("Madrid");

        Usuario usuario = new Usuario();
        usuario.setDireccion(direccion);

        assertEquals("Calle Falsa", usuario.getDireccion().getCalle());
        assertEquals(123, usuario.getDireccion().getNumero());
        assertEquals("28080", usuario.getDireccion().getCodigoPostal());
        assertEquals("3B", usuario.getDireccion().getPisoLetra());
        assertEquals("Madrid", usuario.getDireccion().getCiudad());
    }

    @Test
    void setDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Avenida Siempre Viva");

        Usuario usuario = new Usuario();
        usuario.setDireccion(direccion);

        assertEquals("Avenida Siempre Viva", usuario.getDireccion().getCalle());
    }

    @Test
    void getEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("lara@example.com");
        assertEquals("lara@example.com", usuario.getEmail());
    }

    @Test
    void setEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("otra@ufv.es");
        assertEquals("otra@ufv.es", usuario.getEmail());
    }

    @Test
    void getMetodoPago() {
        MetodoPago metodo = new MetodoPago();
        metodo.setNumeroTarjeta(1234567890123456L);
        metodo.setNombreAsociado("Lara Tarjeta");

        Usuario usuario = new Usuario();
        usuario.setMetodoPago(metodo);

        assertEquals(1234567890123456L, usuario.getMetodoPago().getNumeroTarjeta());
        assertEquals("Lara Tarjeta", usuario.getMetodoPago().getNombreAsociado());
    }

    @Test
    void setMetodoPago() {
        MetodoPago metodo = new MetodoPago();
        metodo.setNombreAsociado("Luis Pagador");

        Usuario usuario = new Usuario();
        usuario.setMetodoPago(metodo);

        assertEquals("Luis Pagador", usuario.getMetodoPago().getNombreAsociado());
    }
}