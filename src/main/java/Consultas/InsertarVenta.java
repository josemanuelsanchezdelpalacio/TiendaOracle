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
        boolean validacion;

        //inserto los datos
        VentaprodEntity venta = new VentaprodEntity();

        do {
            validacion = true;
            long idCliente = Leer.pedirEntero("Introduce Id de cliente: ");
            //compruebo que el cliente exista
            ClientesEntity cliente = em.find(ClientesEntity.class, idCliente);
            venta.setIdCliente(idCliente);

            if (cliente == null) {
                validacion = false;
                System.out.println("Cliente no encontrado.");
            }
        } while (!validacion);


        do {
            validacion = true;
            long idProducto = Leer.pedirEntero("Introduce Id de producto: ");
            //compruebo que el producto existe
            ProductosEntity producto = em.find(ProductosEntity.class, idProducto);
            venta.setIdProducto(idProducto);

            if (producto == null) {
                validacion = false;
                System.out.println("Producto no encontrado.");
            }
        } while (!validacion);

        short unidades = (short) Leer.pedirEntero("Introduce numero de unidades: ");
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
