package logica;

public class Producto {
    private String codigo;
    private String nombre;
    private double precioRegular;
    private double precioMayorista;
    private double precioLista;
    private String categoria;
    private String ubicacion;
    private String descripcion;
    
    public Producto(String codigo, String nombre, double precioRegular, double precioMayorista, double precioLista, String categoria, String ubicacion, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioRegular = precioRegular;
        this.precioMayorista = precioMayorista;
        this.precioLista = precioLista;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioRegular() {
        return precioRegular;
    }

    public void setPrecioRegular(double precioRegular) {
        this.precioRegular = precioRegular;
    }

    public double getPrecioMayorista() {
        return precioMayorista;
    }

    public void setPrecioMayorista(double precioMayorista) {
        this.precioMayorista = precioMayorista;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void mostrar(){
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio regular: " + this.precioRegular);
        System.out.println("Precio por lista: " + this.precioLista);
        System.out.println("Precio por cantidad: " + this.precioMayorista);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Ubicacion: " + this.ubicacion);
    }


    
        
    
}
