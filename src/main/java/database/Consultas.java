package database;

import entities.ClientesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class Consultas {

    public static void listarClientes(EntityManager em) {
        List<ClientesEntity> clientes = em.createQuery("from ClientesEntity", ClientesEntity.class).getResultList();
        for (ClientesEntity c : clientes){
            long numArticulos = (long) em.createQuery("select sum(v.unidades) from VentaprodEntity v where v.idCliente = :idCliente").setParameter("idCliente", c.getId()).getSingleResult();
            System.out.println(c.getNombre() + " - " + numArticulos);
        }
    }
}
