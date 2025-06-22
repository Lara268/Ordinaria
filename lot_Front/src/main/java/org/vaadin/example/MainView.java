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

    public MainView() {
        // Título
        H1 titulo = new H1("Listado de Usuarios");
        add(titulo);

        // Tabla
        Grid<Usuario> grid = new Grid<>(Usuario.class);
        grid.setItems(usuarioService.obtenerUsuarios());

        // Columnas visibles
        grid.setColumns("nombre", "apellidos", "nif", "email");

        // Botón de editar
        grid.addComponentColumn(usuario -> {
            Button editarBtn = new Button("Editar");
            editarBtn.addClickListener(e -> {
                Notification.show("Editando: " + usuario.getNombre());
                // Aquí puedes abrir un diálogo para editar
            });
            return editarBtn;
        });

        // Botón Añadir y Generar PDF
        Button btnAnadir = new Button("Añadir Usuario", e -> {
            Notification.show("Funcionalidad aún no implementada");
        });
        Button btnPdf = new Button("Genera PDF", e -> {
            Notification.show("Exportar a PDF no implementado todavía");
        });

        btnAnadir.getStyle().set("background-color", "#007bff").set("color", "white");
        btnPdf.getStyle().set("background-color", "#007bff").set("color", "white");

        HorizontalLayout botones = new HorizontalLayout(btnAnadir, btnPdf);
        add(grid, botones);
    }

//    /**
//     * Construct a new Vaadin view.
//     * <p>
//     * Build the initial UI state for the user accessing the application.
//     *
//     * @param service
//     *            The message service. Automatically injected Spring managed bean.
//     */
//    public MainView(GreetService service) {
//
//        // Use TextField for standard text input
//        TextField textField = new TextField("Your name");
//        textField.addClassName("bordered");
//
//        // Button click listeners can be defined as lambda expressions
//        Button button = new Button("Say hello", e -> {
//            add(new Paragraph(service.greet(textField.getValue())));
//        });
//
//        // Theme variants give you predefined extra styles for components.
//        // Example: Primary button has a more prominent look.
//        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//
//        // You can specify keyboard shortcuts for buttons.
//        // Example: Pressing enter in this view clicks the Button.
//        button.addClickShortcut(Key.ENTER);
//
//        // Use custom CSS classes to apply styling. This is defined in
//        // styles.css.
//        addClassName("centered-content");
//
//        add(textField, button);
//    }
}
