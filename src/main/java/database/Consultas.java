package database;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import libs.Leer;

import java.sql.Date;
import java.util.List;

public class Consultas {

    /**
     * Listar los clientes y el número de artículos que ha comprado
     **/
    public static void listarClientes(EntityManager em) {
        List<ClientesEntity> clientes = em.createQuery("from ClientesEntity", ClientesEntity.class).getResultList();
        for (ClientesEntity c : clientes) {
            int numArticulos = 0;
            Query q = em.createQuery("from VentaprodEntity where idCliente=?1").setParameter(1, c.getId());
            List<VentaprodEntity> articulos = q.getResultList();
            for (VentaprodEntity v : articulos) {
                numArticulos += v.getUnidades();
            }
            System.out.println(c.getNombre() + " - " + numArticulos);
        }
    }

    //long numArticulos = (long) em.createQuery("select sum(v.unidades) from VentaprodEntity v where v.idCliente = :idCliente").setParameter("idCliente", c.getId()).getSingleResult();

    /**
     * Listar los diferentes productos y el número de unidades vendidas
     **/
    public static void listarProductos(EntityManager em) {
        List<ProductosEntity> productos = em.createQuery("from ProductosEntity", ProductosEntity.class).getResultList();
        for (ProductosEntity p : productos) {
            int numUnidades = 0;
            Query q = em.createQuery("from VentaprodEntity where idProducto=?1").setParameter(1, p.getId());
            List<VentaprodEntity> unidades = q.getResultList();
            for (VentaprodEntity v : unidades) {
                numUnidades += v.getUnidades();
            }
            System.out.println(p.getDescripcion() + " - " + numUnidades);
        }
    }

    /**
     * Insertar una venta (controlando que el cliente exista, el producto exista y tenga stock)
     **/
    public static void insertarVenta(EntityManager em, long idCliente, long idProducto, short unidades) {
        // Verificar que el cliente existe
        ClientesEntity cliente = em.find(ClientesEntity.class, idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Verificar que el producto existe y tiene stock
        ProductosEntity producto = em.find(ProductosEntity.class, idProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        if (producto.getStock() < unidades) {
            System.out.println("No hay suficiente stock para realizar la venta.");
            return;
        }

        // Realizar la venta
        VentaprodEntity venta = new VentaprodEntity();
        venta.setIdCliente(idCliente);
        venta.setIdProducto(idProducto);
        venta.setUnidades(unidades);
        venta.setFecha(new Date());

        em.getTransaction().begin();
        em.persist(venta);  // Utilizamos persist para insertar la nueva venta
        em.getTransaction().commit();

        System.out.println("Venta realizada con éxito.");
    }

    public static void listarVentasClienteDetallado(EntityManager em, long idCliente) {
        // Consulta HQL para obtener las ventas del cliente con detalles
        List<ClientesEntity> results = em.createQuery(
                "SELECT v.id, v.fecha, p.descripcion, v.unidades, p.precio " +
                        "FROM VentaprodEntity v " +
                        "JOIN ProductosEntity p ON v.idProducto = p.id " +
                        "WHERE v.idCliente = :idCliente", ClientesEntity.class)
                .setParameter("idCliente", idCliente)
                .getResultList();

        // Variables para cálculos totales
        int numeroTotalVentas = 0;
        double importeTotal = 0.0;

        System.out.println("Ventas del cliente: " + em.find(ClientesEntity.class, idCliente).getNombre() + "\n");

        for (ClientesEntity result : results) {
            long idVenta = (long) result[0];
            Date fechaVenta = (Date) result[1];
            String descripcionProducto = (String) result[2];
            short unidades = (short) result[3];
            Short precioProducto = (Short) result[4];

            // Calcular importe para esta venta
            double importeVenta = unidades * precioProducto;

            // Imprimir información detallada de la venta
            System.out.println("Venta: " + idVenta + " , Fecha venta: " + fechaVenta);
            System.out.println("Producto: " + descripcionProducto);
            System.out.println("Cantidad: " + unidades + " PVP: " + precioProducto);
            System.out.println("Importe: " + importeVenta + "\n");

            // Actualizar totales
            numeroTotalVentas++;
            importeTotal += importeVenta;
        }

        // Imprimir totales
        System.out.println("Número total de ventas: " + numeroTotalVentas);
        System.out.println("Importe Total: " + importeTotal);
    }
}
