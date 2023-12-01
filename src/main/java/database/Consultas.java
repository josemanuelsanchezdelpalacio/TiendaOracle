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
import java.time.Instant;
import java.time.LocalDate;
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
     * Insertar una venta (controlando que el cliente exista, el producto exista y tenga stock (el stock no existe en el sql))
     **/
    public static void insertarVenta(EntityManager em) {
        long idCliente = Leer.pedirEntero("Introduce Id de cliente: ");
        long idProducto = Leer.pedirEntero("Introduce Id de producto: ");
        short unidades = (short) Leer.pedirEntero("Introduce numero de unidades: ");

        //compruebo que el cliente exista
        ClientesEntity cliente = em.find(ClientesEntity.class, idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        //compruebo que el producto existe
        ProductosEntity producto = em.find(ProductosEntity.class, idProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        //inserto los datos
        VentaprodEntity venta = new VentaprodEntity();
        venta.setIdCliente(idCliente);
        venta.setIdProducto(idProducto);
        venta.setUnidades(unidades);
        venta.setFecha(Date.valueOf(LocalDate.now()));

        //inserto la nueva venta
        em.getTransaction().begin();
        em.persist(venta);
        em.getTransaction().commit();

        System.out.println("Venta realizada con éxito.");
    }

    /**Obtener un listado de ventas de un cliente (usa una consulta HQL para obtener la información). El listado se realizará en un método que recibirá como parámetro el id del cliente y mostrará la información como se muestra en la imagen adjunta**/
    public static void listarVentasClienteDetallado(EntityManager em, long idCliente) {

        int numeroTotalVentas = 0;
        double importeTotal = 0.0;

        ClientesEntity cliente = em.find(ClientesEntity.class, idCliente);
        System.out.println("Ventas del cliente: " + cliente.getNombre() + "\n");

        List<ProductosEntity> productos = em.createQuery("from ProductosEntity", ProductosEntity.class).getResultList();
        for (ProductosEntity producto : productos) {

            Query q = em.createQuery("from VentaprodEntity where idProducto=?1").setParameter(1, producto.getId());
            List<VentaprodEntity> ventas = q.getResultList();
            for (VentaprodEntity venta : ventas) {
                double importeVenta = venta.getUnidades() * producto.getPrecio();

                System.out.println("Venta: " + venta.getId() + ", Fecha venta: " + venta.getFecha());
                System.out.println("\tProducto: " + producto.getDescripcion());
                System.out.println("\tCantidad: " + venta.getUnidades() + " PVP: " + producto.getPrecio());
                System.out.println("\tImporte: " + importeVenta + "\n");

                numeroTotalVentas++;
                importeTotal += importeVenta;
            }

            System.out.println("Número total de ventas: " + numeroTotalVentas);
            System.out.println("Importe Total: " + importeTotal);
        }
    }
}
