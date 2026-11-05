import java.io.*;
import java.util.Scanner;

public class Catalogo {
    private static final String RUTA_FICHERO = "src/main/resources/";
    private static final String NOMBRE_FICHERO = "recetas.dat";

    Scanner sc = new Scanner(System.in);

    public void crearCatalogoInicial() {
        Receta[] recetas = {
                new Receta("Pizza", 20, new String[]{"masa", "queso", "tomate"}),
                new Receta("Paella", 60, new String[]{"arroz", "marisco", "pollo"}),
                new Receta("Pisto", 40, new String[]{"pimiento", "tomate", "berenejena", "huevo"}),
        };

        try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            for (Receta receta : recetas) {
                escribir.writeObject(receta);
            }
        } catch (IOException e) {
            System.out.println("Error al crear o escribir el fichero.");
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

        Receta receta = new Receta(nombre, tiempo, ingredientes);

        try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO, true))) {
            escribir.writeObject(receta);
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero.");
        }
    }

    public void mostrarRecetas() {
        try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            while (true) {
                Receta recetaLeida = (Receta) leer.readObject();
                System.out.println(recetaLeida);
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero.");
            System.out.println(e.getMessage());
        }
    }

    public void buscarReceta() {
        System.out.println("¿Qué receta quieres buscar?");
        System.out.println("Introduce el ID: ");
        int id = sc.nextInt();

        try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            while (true) {
                Receta recetaLeida = (Receta) leer.readObject();
                if (recetaLeida.getId() == id) {
                    System.out.println(recetaLeida);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero.");
        }
    }

    public void modificarTiempo() {
        mostrarRecetas();
        Receta receta = new Receta();
        System.out.print("¿Qué receta quieres cambiar? (Introduce el ID): ");
        int id = sc.nextInt();

        System.out.print("Introduce el tiempo: ");
        int tiempo = sc.nextInt();

        try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(RUTA_FICHERO + NOMBRE_FICHERO));
            DataOutputStream escribir = new DataOutputStream(new FileOutputStream(RUTA_FICHERO + NOMBRE_FICHERO))) {
            while (true) {
                Receta recetaLeida = (Receta) leer.readObject();
                if (recetaLeida.getId() == id) {
                    recetaLeida.setMinutosPrep(tiempo);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero.");
        }
    }

}
