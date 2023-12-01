import Consultas.ConsultasListar;
import Consultas.InsertarVenta;
import Consultas.ListarVentasClientes;
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
                    ConsultasListar.listarClientes(em);
                    break;
                case 2:
                    ConsultasListar.listarProductos(em);
                    break;
                case 3:
                    InsertarVenta.insertarVenta(em);
                    break;
                case 4:
                    ListarVentasClientes.listarVentasClienteDetallado(em, Leer.pedirEntero("Ingresa el ID del cliente: "));
                default:
                    System.out.println("La opcion seleccionada no existe");
            }
        } while (!salir);
        desconectar();
    }

    private static void desconectar() {
        em.close();
        emf.close();
    }
}