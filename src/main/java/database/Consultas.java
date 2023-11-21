package database;

import entities.ClientesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.util.List;

public class Consultas {

    public static void listarClientes(EntityManager em) {
        List<ClientesEntity> clientes = em.createQuery("from ClientesEntity",  ClientesEntity.class).getResultList();
        for (ClientesEntity c : clientes){
            System.out.println(c.getNombre());
        }
    }
}
