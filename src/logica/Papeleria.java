package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Papeleria {
    private String nombre;
    private ArrayList<Producto> productos;
    private ArrayList<Factura> facturas;
    private ArrayList<Cliente> clientes;
    
    public Papeleria(String nombre){
        this.nombre = nombre;
        productos = new ArrayList<>();
        facturas = new ArrayList<>();
        clientes = new ArrayList<>();
        
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public boolean existeProductoConCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    
    public void registrarProducto(String codigo, String nombre, double precioRegular, double precioMayorista, double precioLista, String categoria, String ubicacion, String descripcion) {
        Producto producto = new Producto(codigo, nombre, precioRegular, precioMayorista, precioLista, categoria, ubicacion, descripcion);
        this.productos.add(producto);   
    }

    public void actualizarProducto(String codigo, String nuevoNombre, double nuevoPrecioRegular, double nuevoPrecioMayorista, double nuevoPrecioLista, String nuevaCategoria, String nuevaUbicacion) {
        for (Producto producto : this.productos) {
            if (producto.getCodigo().equals(codigo)) {
                if (!nuevoNombre.isEmpty()) {
                    producto.setNombre(nuevoNombre);
                }
                if (nuevoPrecioRegular >= 0) {
                    producto.setPrecioRegular(nuevoPrecioRegular);
                }
                if (nuevoPrecioMayorista >= 0) {
                    producto.setPrecioMayorista(nuevoPrecioMayorista);
                }
                if (nuevoPrecioLista >= 0) {
                    producto.setPrecioLista(nuevoPrecioLista);
                }
                if (!nuevaCategoria.isEmpty()) {
                    producto.setCategoria(nuevaCategoria);
                }
                if (!nuevaUbicacion.isEmpty()) {
                    producto.setUbicacion(nuevaUbicacion);
                }
                System.out.println("Producto actualizado correctamente.");
                return;
            }
        }

    }

    public void eliminarProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigo)) {
                productos.remove(i);
                System.out.println("Producto eliminado correctamente.");
                return;
            }
        }
    }

    public ArrayList<Producto> buscarProductos(String texto) {
        ArrayList<Producto> resultados = new ArrayList<>();
        String textoBusqueda = texto.toLowerCase();

        for (Producto producto : productos) {
            String nombre = producto.getNombre().toLowerCase();
            String descripcion = producto.getDescripcion().toLowerCase();

            if (nombre.contains(textoBusqueda) || descripcion.contains(textoBusqueda)) {
                resultados.add(producto);
            }
        }

        return resultados;
    }
    
    public Cliente registrarCliente(String cedula, String nombre){
        Cliente cliente = new Cliente(cedula,nombre);
        return cliente;
    }
    
    public Cliente buscarCliente(String cedula){
        Cliente clienteEncontrado = null;
        for (Cliente c : clientes) {
            if (c.getCedula().equals(cedula)) {
                clienteEncontrado = c;
                break;
            }
        }
        return clienteEncontrado;
    }
    
    public Factura facturarPedido(Cliente cliente, ArrayList<Item> items, HashSet<String> codigosListas) {
        // Verificamos si hay 12 o más ítems distintos de categoría "listas"
        if (codigosListas.size() >= 12) {
            for (Item item : items) {
                if (item.getProducto().getCategoria().equalsIgnoreCase("listas")) {
                    double nuevoSubtotal = item.getProducto().getPrecioLista() * item.getCantidad();
                    double nuevoDescuento = item.getSubtotal() - nuevoSubtotal;
                    item.setDescuento(nuevoDescuento);
                    item.setSubtotal(nuevoSubtotal);
                }
            }
        }

        // Calcular total
        double total = 0;
        for (Item item : items) {
            total += item.getSubtotal();
        }

        // Crear la factura
        Factura factura = new Factura(cliente, items);
        factura.calcularTotal();
        return factura;
    }

    
}