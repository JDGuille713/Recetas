import java.io.Serializable;
import java.util.Arrays;

public class Receta implements Serializable {
    private static int contador = 0;
    private int id;
    private String nombre;
    private int minutosPrep;
    private String[] ingredientes;

    public Receta() {}

    public Receta(String nombre, int minutosPrep,  String[] ingredientes) {
        contador++;
        this.id = contador;
        this.nombre = nombre;
        this.minutosPrep = minutosPrep;
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public int getMinutosPrep() {
        return minutosPrep;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setMinutosPrep(int minutosPrep) {
        this.minutosPrep = minutosPrep;
    }

    @Override
    public String toString() {
        return "Receta: " +
                "id = " + id +
                ", nombre = " + nombre +
                ", minutosPrep = " + minutosPrep +
                ", ingredientes = " + Arrays.toString(ingredientes);
    }
}
