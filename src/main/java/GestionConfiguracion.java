import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionConfiguracion {
    private static final String RUTA_FICHERO = "src/main/resources/";
    private static final String NOMBRE_FICHERO = "configuracion.json";

    public void guardarConfiguracion(Configuracion config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fichero = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO)) {
            gson.toJson(config, fichero);
        } catch (IOException e) {
            System.out.println("Error al crear el JSON.");
        }
    }

    public Configuracion leerConfiguracion() {
        Gson gson = new Gson();

        File fichero = new File(RUTA_FICHERO + NOMBRE_FICHERO);

        if (!fichero.exists()) {
            return new Configuracion(0, 0);
        }

        try (FileReader lector = new FileReader(fichero)) {
            Configuracion config = gson.fromJson(lector, Configuracion.class);

            if (config == null) {
                return new Configuracion(0, 0);
            }
            return config;
        } catch (IOException e) {
            System.out.println("Error al leer el JSON.");
            return new Configuracion(0, 0);
        }
    }
}
