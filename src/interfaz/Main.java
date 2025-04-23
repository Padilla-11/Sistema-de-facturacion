package interfaz;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;
import logica.Papeleria;
import logica.Producto;
import logica.Factura;
import logica.Item;
import logica.Cliente;


public class Main {

    public static void main(String[] args) {
        Papeleria papeleria = new Papeleria("Tali's");
        Scanner leer = new Scanner(System.in);
        
        
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Ver productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Buscar producto");
            System.out.println("6. Facturar pedido");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leer.nextInt();
            leer.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    registrarProducto(papeleria);
                    break;
                case 2:
                    verProductos(papeleria);
                    break;
                case 3:
                    actualizarProducto(papeleria);
                    break;
                case 4:
                    eliminarProducto(papeleria);
                    break;
                case 5:
                    buscarProducto(papeleria);
                    break;
                case 6:
                    facturarPedido(papeleria);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        
    }
    
    
    public static void registrarProducto(Papeleria papeleria) {
        Scanner leer = new Scanner(System.in);
        String codigo = "", nombre = "", categoria = "", ubicacion = "", descripcion = "";
        double precioRegular = 0, precioLista = 0, precioMayorista = 0;

        // Leer y validar código único
    while (true) {
        System.out.print("Codigo: ");
        codigo = leer.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("El código no puede estar vacío.");
        } 
        else if (papeleria.existeProductoConCodigo(codigo)) {
            System.out.println("Ya existe un producto con ese código. Ingrese uno diferente.");
        } 
        else {
            break; // válido y no repetido
        }
    }

        // Leer y validar nombre
        while (nombre.isEmpty()) {
            System.out.print("Nombre: ");
            nombre = leer.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        }

        // Leer y validar categoría
        while (true) {
            System.out.print("Categoria: (oficina / escolar / servicio): ");
            categoria = leer.nextLine().trim().toLowerCase();
            if (categoria.equals("oficina") || categoria.equals("escolar") || categoria.equals("servicio")) {
                break;
            } else {
                System.out.println("Categoría inválida. Debe ser oficina, escolar o servicio.");
            }
        }

        // Leer y validar precio regular
        while (true) {
            try {
                System.out.print("Precio regular: ");
                precioRegular = Double.parseDouble(leer.nextLine());
                if (precioRegular < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un precio válido y positivo.");
            }
        }

        // Leer y validar precio por lista
        while (true) {
            try {
                System.out.print("Precio por lista: ");
                precioLista = Double.parseDouble(leer.nextLine());
                if (precioLista < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un precio válido y positivo.");
            }
        }

        // Leer y validar precio mayorista
        while (true) {
            try {
                System.out.print("Precio por cantidad: ");
                precioMayorista = Double.parseDouble(leer.nextLine());
                if (precioMayorista < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un precio válido y positivo.");
            }
        }

        // Leer y validar ubicación
        while (ubicacion.isEmpty()) {
            System.out.print("Ubicacion: ");
            ubicacion = leer.nextLine().trim();
            if (ubicacion.isEmpty()) {
                System.out.println("La ubicación no puede estar vacía.");
            }
        }

        // Leer y validar descripción
        while (descripcion.isEmpty()) {
            System.out.println("Escriba palabras claves y sinónimos de este producto:");
            descripcion = leer.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("La descripción no puede estar vacía.");
            }
    }

    // Registro del producto
    papeleria.registrarProducto(codigo, nombre, precioRegular, precioMayorista, precioLista, categoria, ubicacion, descripcion);
    System.out.println("Producto registrado exitosamente.");
}

    
    public static void verProductos(Papeleria papeleria){
        ArrayList<Producto> productos = new ArrayList<>(papeleria.getProductos());
        for(Producto producto: productos){
            System.out.println("-----------------------------------");
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Codigo: " + producto.getCodigo());
        }
    }
    
    public static void actualizarProducto(Papeleria papeleria) {
        Scanner leer = new Scanner(System.in);
        verProductos(papeleria);
        System.out.println("INGRESE EL CÓDIGO DEL PRODUCTO QUE DESEA ACTUALIZAR:");
        String codigo = leer.nextLine();

        for(Producto producto: papeleria.getProductos()){
            if(codigo.equals(producto.getCodigo())){
                producto.mostrar();
            }
        }
        
        System.out.println("Ingrese el nuevo nombre (dejar vacío para mantener):");
        String nuevoNombre = leer.nextLine();

        System.out.println("Ingrese el nuevo precio regular (o -1 para mantener):");
        double nuevoPrecioRegular = leer.nextDouble();

        System.out.println("Ingrese el nuevo precio mayorista (o -1 para mantener):");
        double nuevoPrecioMayorista = leer.nextDouble();

        System.out.println("Ingrese el nuevo precio de lista (o -1 para mantener):");
        double nuevoPrecioLista = leer.nextDouble();

        leer.nextLine(); // Consumir salto de línea

        System.out.println("Ingrese la nueva categoría (dejar vacío para mantener):");
        String nuevaCategoria = leer.nextLine();

        System.out.println("Ingrese la nueva ubicación (dejar vacío para mantener):");
        String nuevaUbicacion = leer.nextLine();

        // Enviar a la capa lógica
        papeleria.actualizarProducto(codigo, nuevoNombre, nuevoPrecioRegular, nuevoPrecioMayorista, nuevoPrecioLista, nuevaCategoria, nuevaUbicacion);
    }
    
    public static void eliminarProducto(Papeleria papeleria) {
        Scanner leer = new Scanner(System.in);
        verProductos(papeleria); // Mostrar productos disponibles

        System.out.println("INGRESE EL CÓDIGO DEL PRODUCTO QUE DESEA ELIMINAR:");
        String codigo = leer.nextLine();

        System.out.println("¿Está seguro que desea eliminar este producto? (s/n):");
        String confirmacion = leer.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            papeleria.eliminarProducto(codigo);
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    public static void buscarProducto(Papeleria papeleria){
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese una palabra clave para buscar el producto:");
        String palabraClave = leer.nextLine();

        ArrayList<Producto> encontrados = papeleria.buscarProductos(palabraClave);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos que coincidan con esa palabra.");
        } else {
            System.out.println("Productos encontrados:");
            for (Producto p : encontrados) {
                System.out.println("- " + p.getNombre() + " | Ubicación: " + p.getUbicacion());
            }
        }
    }
    
    public static void facturarPedido(Papeleria papeleria) {
        Scanner leer = new Scanner(System.in);

        // Solicitar cédula del cliente
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = leer.nextLine();
        Cliente cliente = null;

        // Buscar cliente por cédula
        cliente = papeleria.buscarCliente(cedula);

        if (cliente == null) {
            System.out.println("Cliente no registrado. Digite su nombre");
            String nombre = leer.nextLine();
            cliente = papeleria.registrarCliente(cedula,nombre);
        }

        // Crear lista para los items
        ArrayList<Item> items = new ArrayList<>();
        HashSet<String> codigosListas = new HashSet<>(); // para contar ítems únicos de categoría "listas"

        while (true) {
            System.out.print("Ingrese el código del producto (o 'fin' para terminar): ");
            String codigo = leer.nextLine();
            if (codigo.equalsIgnoreCase("fin")) break;

            Producto productoEncontrado = null;
            for (Producto p : papeleria.getProductos()) {
                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    productoEncontrado = p;
                    break;
                }
            }

            if (productoEncontrado == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = Integer.parseInt(leer.nextLine());

            // Agregar producto a la lista de items
            double precioAplicado = productoEncontrado.getPrecioRegular();
            double descuento = 0;

            // Si la cantidad es >= 12, se usa precio mayorista
            if (cantidad >= 12) {
                precioAplicado = productoEncontrado.getPrecioMayorista();
                descuento = (productoEncontrado.getPrecioRegular() - precioAplicado) * cantidad;
            }

            // Si es de categoría "listas", lo añadimos al conteo
            if (productoEncontrado.getCategoria().equalsIgnoreCase("listas")) {
                codigosListas.add(productoEncontrado.getCodigo());
            }

            // Crear item y agregarlo a la lista
            double subtotal = precioAplicado * cantidad;
            Item item = new Item(productoEncontrado, cantidad, descuento, subtotal);
            items.add(item);
        }

        // Llamar a la capa lógica para procesar la facturación
        Factura factura = papeleria.facturarPedido(cliente, items, codigosListas);

        // Mostrar factura
        System.out.println("\nFactura generada:");
        System.out.println("Cliente: " + factura.getCliente().getNombre() + " -> " + factura.getCliente().getCedula());
        System.out.println("Fecha: " + factura.getFecha() + " " + factura.getHora());
        for (Item item : factura.getProductos()) {
            System.out.println("- " + item.getProducto().getNombre() +
                               " x" + item.getCantidad() +
                               " = $" + item.getSubtotal() +
                               " (Descuento aplicado: $" + item.getDescuento() + ")");
        }
        System.out.println("TOTAL: $" + factura.getTotal());
    }
    
}
