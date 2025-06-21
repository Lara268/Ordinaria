package es.ufv.dis.back.final2025.lot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LectorJSON {
    public ArrayList<Usuario> leeFicheroJson(){
        try {
            //lee el fichero que le pasemos y lo carga en un reader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            getClass().getClassLoader().getResourceAsStream("usuarios.json")
                    )
            );

            // convierte el array JSON a un arraylist de users
            ArrayList<Usuario> listaUsuario =
                    new Gson().fromJson(reader, new TypeToken<ArrayList<Usuario>>() {}.getType());
            reader.close();// close reader
            return listaUsuario;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }
}