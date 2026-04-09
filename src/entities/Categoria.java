package entities;

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private boolean activo;

    //Creation of Constructors
    public Categoria(){
    }
    public Categoria(int id, String nombre, String descripcion, boolean activo){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // Getters And Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // toString Method
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                '}';
    }
}
