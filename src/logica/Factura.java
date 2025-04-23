package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Factura {
    private static int NFACTURAS = 0;
    private long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Cliente cliente;
    private ArrayList<Item> productos;
    private double total;
    
    public Factura(Cliente cliente, ArrayList<Item> items) {
        this.id = NFACTURAS + 1;
        this.cliente = cliente;
        this.productos = items;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
    }

    public static int getNFACTURAS() {
        return NFACTURAS;
    }

    public long getId() {
        return id;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Item> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProductos(ArrayList<Item> productos) {
        this.productos = productos;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void calcularTotal(){
    
        for(Item producto: productos){
            this.total += producto.getSubtotal();
        }
        
    }
    
}
