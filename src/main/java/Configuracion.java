public class Configuracion {
    private int ultimaOpcion;
    private int totalRecetas;

    public Configuracion() {
    }

    public Configuracion(int ultimaOpcion, int totalRecetas) {
        this.totalRecetas = totalRecetas;
        this.ultimaOpcion = ultimaOpcion;
    }

    public int getTotalRecetas() {
        return totalRecetas;
    }

    public int getUltimaOpcion() {
        return ultimaOpcion;
    }

    public void setTotalRecetas(int totalRecetas) {
        this.totalRecetas = totalRecetas;
    }

    public void setUltimaOpcion(int ultimaOpcion) {
        this.ultimaOpcion = ultimaOpcion;
    }

    @Override
    public String toString() {
        return "Última opcion: " + getUltimaOpcion() + ", Total recetas: " + getTotalRecetas();
    }
}


