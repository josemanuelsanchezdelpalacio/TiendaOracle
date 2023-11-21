import database.Consultas;
import database.EmfSingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import libs.Leer;

public class Main {

    static EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            System.out.println("0. Salir");
            System.out.println("1. Listar clientes");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0 -> {salir = true;}
                case 1 -> {Consultas.listarClientes(em);}
                default -> {System.out.println("La opcion seleccionada no existe");}
            }
            desconectar();
        } while (!salir);
    }

    private static void desconectar() {
        em.close();
        emf.close();
    }
}