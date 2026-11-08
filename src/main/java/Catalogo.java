import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Catalogo {
    private static final String RUTA_FICHERO = "src/main/resources/";
    private static final String NOMBRE_FICHERO = "recetas.dat";

    Scanner sc = new Scanner(System.in);
    ArrayList<Receta> recetasList = new ArrayList<>();

    public ArrayList<Receta> getRecetasList() {
        return recetasList;
    }

    public void leerRecetas() {
        recetasList.clear();
        try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            while (true) {
                Receta recetaLeida = (Receta) leer.readObject();
                recetasList.add(recetaLeida);
            }
        } catch (EOFException e) {
            System.out.print("");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero.");
            System.out.println(e.getMessage());
        }
    }

    private int siguienteID() {
        int max = 0;
        for (Receta receta : recetasList) {
            if (receta.getId() > max) {
                max = receta.getId();
            }
        }
        return max + 1;
    }

    private void agregarReceta(Receta receta) {
        receta.setId(siguienteID());
        recetasList.add(receta);
    }

    public void escribirRecetas() {
        try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (Receta receta : recetasList) {
                escribir.writeObject(receta);
            }
        } catch (IOException e) {
            System.out.println("Error escribir el fichero.");
        }
    }

    public void crearCatalogoInicial() {
        Receta[] recetas = {
                new Receta("Pizza", 20, new String[]{"masa", "queso", "tomate"}),
                new Receta("Paella", 60, new String[]{"arroz", "marisco", "pollo"}),
                new Receta("Pisto", 40, new String[]{"pimiento", "tomate", "berenejena", "huevo"}),
        };

        for (Receta receta : recetas) {
            agregarReceta(receta);
        }
        escribirRecetas();
    }

    public void comprobarFichero() {
        File fichero = new File(RUTA_FICHERO + NOMBRE_FICHERO);

        if  (fichero.exists()) {
            leerRecetas();
        } else {
            crearCatalogoInicial();
        }
    }

    public void mostrarRecetas() {
        for (Receta receta : recetasList) {
            System.out.println(receta);
        }
    }


    public void anadirReceta() {
        System.out.print("¿Qué plato quieres añadir? ");
        String nombre = sc.nextLine();

        System.out.print("¿Cuánto se tarda en hacer la receta? ");
        int tiempo = sc.nextInt();

        sc.nextLine();
        System.out.print("¿Qué ingredientes lleva? (Separa por espacios) ");
        String[] ingredientes = sc.nextLine().split(" ");

        agregarReceta(new Receta(nombre, tiempo, ingredientes));
        escribirRecetas();
    }


    public void buscarReceta() {
        System.out.println("¿Qué receta quieres buscar?");
        System.out.print("Introduce el ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean encontrado = false;

        for (Receta receta : recetasList) {
            if (receta.getId() == id) {
                System.out.println(receta);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontro la receta.");
        }
    }

    public void modificarTiempo() {
        mostrarRecetas();
        boolean encontrado = false;

        System.out.print("¿Qué receta quieres cambiar? (Introduce el ID): ");
        int id = sc.nextInt();

        System.out.print("Introduce el tiempo: ");
        int tiempo = sc.nextInt();
        sc.nextLine();

        for (Receta receta : recetasList) {
            if (receta.getId() == id) {
                receta.setMinutosPrep(tiempo);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            escribirRecetas();
        } else {
            System.out.println("No se ha encontrado la receta.");
        }

    }
}
