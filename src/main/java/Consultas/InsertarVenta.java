package Consultas;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import libs.Leer;

import java.sql.Date;
import java.time.LocalDate;

public class InsertarVenta {

    /**Insertar una venta (controlando que el cliente exista, el producto exista y tenga stock (el stock no existe en el sql))**/
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
        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println("No se ha podido hacer la inserccion");
        }

        System.out.println("Venta realizada con éxito.");
    }
}
