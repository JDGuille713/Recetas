import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Catalogo catalogo = new Catalogo();
        CatalogoXML catalogoXML = new CatalogoXML();
        GestionConfiguracion gestion = new GestionConfiguracion();

        catalogo.comprobarFichero();
        Configuracion config = gestion.leerConfiguracion();
        System.out.println(config);

        int opcion = -1;
        do {
            System.out.println("1. Mostrar recetas");
            System.out.println("2. Buscar receta por ID");
            System.out.println("3. Modificar tiempo de preparación");
            System.out.println("4. Añadir receta");
            System.out.println("5. Exportar catálogo a XML");
            System.out.println("0. Salir");
            System.out.print("Escoja una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    catalogo.mostrarRecetas();
                    break;
                case 2:
                    catalogo.buscarReceta();
                    break;
                case 3:
                    catalogo.modificarTiempo();
                    break;
                case 4:
                    catalogo.anadirReceta();
                    break;
                case 5:
                    catalogoXML.crearXML(catalogo.getRecetasList());
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            if (opcion >= 1 && opcion <= 5) {
                config.setUltimaOpcion(opcion);
            }
        } while (opcion != 0);

        // Al salir: actualizar el total y guardar la configuración
        config.setTotalRecetas(catalogo.getRecetasList().size());
        gestion.guardarConfiguracion(config);
    }
}