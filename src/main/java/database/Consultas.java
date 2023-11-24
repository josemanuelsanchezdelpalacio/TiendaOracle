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
    public static void insertarVentas(EntityManager em) {
        List<VentaprodEntity> productos = em.createQuery("from VentaprodEntity where exists(select idCliente, idProducto from VentaprodEntity.idCliente, VentaprodEntity.idProducto)", VentaprodEntity.class).getResultList();
        for (VentaprodEntity v : productos) {
            v.setIdCliente(1);
            v.setIdProducto(1);
            v.setUnidades();
            v.setFecha(Date.valueOf("2005-01-01"));
            em.persist(v);
        }
    }
}
