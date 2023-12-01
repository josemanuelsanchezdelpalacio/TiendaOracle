package Consultas;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ConsultasListar {

    /**Listar los clientes y el número de artículos que ha comprado**/
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

    /**Listar los diferentes productos y el número de unidades vendidas**/
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
}