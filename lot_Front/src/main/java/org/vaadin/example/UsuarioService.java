package org.vaadin.example;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UsuarioService {

    public ArrayList<Usuario> obtenerUsuarios() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            getClass().getClassLoader().getResourceAsStream("usuarios.json")
                    )
            );

            ArrayList<Usuario> listaUsuarios = new Gson().fromJson(reader, new TypeToken<ArrayList<Usuario>>() {}.getType());
            reader.close();
            return listaUsuarios;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}