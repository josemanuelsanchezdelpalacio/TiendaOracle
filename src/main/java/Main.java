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
            System.out.println("1. Listar clientes y numero de articulos que ha comprado");
            System.out.println("2. Listar productos y numero de unidades vendidas");
            System.out.println("3. Insertar venta");
            System.out.println("4. Obtener listado de ventas de un cliente");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    Consultas.listarClientes(em);
                    break;
                case 2:
                    Consultas.listarProductos(em);
                    break;
                case 3:

                    break;
                default:
                    System.out.println("La opcion seleccionada no existe");
            }
            desconectar();
        } while (!salir);
    }

    private static void desconectar() {
        em.close();
        emf.close();
    }
}