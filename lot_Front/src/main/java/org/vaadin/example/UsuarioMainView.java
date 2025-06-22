package org.vaadin.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class UsuarioMainView extends VerticalLayout {

    public UsuarioMainView() {
        UsuarioService usuarioService = new UsuarioService();
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();

        Grid<Usuario> grid = new Grid<>(Usuario.class);
        grid.setItems(usuarios);

        grid.setColumns("id", "nombre", "apellidos", "nif", "email");

        // Accedemos a los campos anidados manualmente con columnas personalizadas:
        grid.addColumn(usuario -> usuario.getDireccion().getCiudad()).setHeader("Ciudad");
        grid.addColumn(usuario -> usuario.getMetodoPago().getNombreAsociado()).setHeader("Nombre Asociado");

        add(grid);
    }
}
