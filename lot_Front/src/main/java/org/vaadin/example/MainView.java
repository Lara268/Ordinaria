package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("")
public class MainView extends VerticalLayout {

    private final UsuarioService usuarioService = new UsuarioService();
    Grid<Usuario> grid = new Grid<>(Usuario.class);

    // Campos a editar
    TextField nombre = new TextField("Nombre");
    TextField apellidos = new TextField("Apellidos");
    TextField nif = new TextField("NIF");
    TextField email = new TextField("Email");

    // Dirección
    TextField calle = new TextField("Calle");
    TextField numero = new TextField("Número");
    TextField codigoPostal = new TextField("Código Postal");
    TextField pisoLetra = new TextField("Piso y Letra");
    TextField ciudad = new TextField("Ciudad");

    // Metodo de pago
    TextField numeroTarjeta = new TextField("Número de Tarjeta");
    TextField nombreAsociado = new TextField("Nombre Asociado a la Tarjeta");

    // Boton para guardar cambios
    Button guardarCambios = new Button("Guardar cambios");
    VerticalLayout formularioEdicion = new VerticalLayout(
            nombre, apellidos, nif, email,
            calle, numero, codigoPostal, pisoLetra, ciudad,
            numeroTarjeta, nombreAsociado,
            guardarCambios
    );

    // Usuario que vamos a editar
    private Usuario usuarioEditando;

    public MainView() {
        // Título
        H1 titulo = new H1("Listado de Usuarios");
        add(titulo);

        // Tabla
        grid.setItems(usuarioService.obtenerUsuarios());

        // Columnas visibles
        grid.setColumns("nombre", "apellidos", "nif", "email");

        // Botón de editar
        grid.addComponentColumn(usuario -> {
            Button editarBtn = new Button("Editar");
            editarBtn.addClickListener(e -> editarUsuario(usuario));
            return editarBtn;
        });

        add(grid);

        // Formulario de edición
        guardarCambios.addClickListener(e -> guardarCambios());
        HorizontalLayout botones = new HorizontalLayout(guardarCambios);
        VerticalLayout formulario = new VerticalLayout(
                nombre, apellidos, nif, email,
                calle, numero, codigoPostal, pisoLetra, ciudad,
                numeroTarjeta, nombreAsociado,
                botones
        );
        formulario.setVisible(false);
        formulario.setId("formulario-edicion");

        add(formulario);

        // Botón Añadir y Generar PDF
        Button btnAnadir = new Button("Añadir Usuario", e -> {
            Notification.show("Funcionalidad aún no implementada");
        });
        Button btnPdf = new Button("Genera PDF", e -> {
            Notification.show("Exportar a PDF no implementado todavía");
        });

        btnAnadir.getStyle().set("background-color", "#007bff").set("color", "white");
        btnPdf.getStyle().set("background-color", "#007bff").set("color", "white");

        HorizontalLayout botones2 = new HorizontalLayout(btnAnadir, btnPdf);
        add(grid, botones2);
    }

    private void guardarCambios() {

        usuarioEditando.setNombre(nombre.getValue());
        usuarioEditando.setApellidos(apellidos.getValue());
        usuarioEditando.setNif(nif.getValue());
        usuarioEditando.setEmail(email.getValue());

        usuarioEditando.setDireccion(new Direccion(
                calle.getValue(),
                Integer.parseInt(numero.getValue()),
                codigoPostal.getValue(),
                pisoLetra.getValue(),
                ciudad.getValue()
        ));

        usuarioEditando.setMetodoPago(new MetodoPago(
                Long.parseLong(numeroTarjeta.getValue()),
                nombreAsociado.getValue()
        ));

        // Llamada al backend para actualizar el usuario en el JSON
        usuarioService.actualizarUsuario(usuarioEditando);

        // Refrescar el grid visual
        grid.setItems(usuarioService.obtenerUsuarios());
        getFormLayout().setVisible(false);
        Notification.show("Usuario actualizado");

    }

    private void editarUsuario(Usuario usuario) {

        this.usuarioEditando = usuario;

        nombre.setValue(usuario.getNombre());
        apellidos.setValue(usuario.getApellidos());
        nif.setValue(usuario.getNif());
        email.setValue(usuario.getEmail());

        if (usuario.getDireccion() != null) {
            calle.setValue(usuario.getDireccion().getCalle());
            numero.setValue(String.valueOf(usuario.getDireccion().getNumero()));
            codigoPostal.setValue(usuario.getDireccion().getCodigoPostal());
            pisoLetra.setValue(usuario.getDireccion().getPisoLetra());
            ciudad.setValue(usuario.getDireccion().getCiudad());
        }

        if (usuario.getMetodoPago() != null) {
            numeroTarjeta.setValue(String.valueOf(usuario.getMetodoPago().getNumeroTarjeta()));
            nombreAsociado.setValue(usuario.getMetodoPago().getNombreAsociado());
        }

        getFormLayout().setVisible(true);

    }

    private VerticalLayout getFormLayout() {
        return (VerticalLayout) getChildren()
                .filter(component -> "formulario-edicion".equals(component.getId().orElse("")))
                .findFirst()
                .orElse(null);
    }

}
