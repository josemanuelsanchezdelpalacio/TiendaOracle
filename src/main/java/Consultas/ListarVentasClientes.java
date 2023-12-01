package Consultas;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ListarVentasClientes {

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