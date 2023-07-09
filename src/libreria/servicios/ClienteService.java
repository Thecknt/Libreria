package libreria.servicios;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;

public class ClienteService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    EntityManager em = emf.createEntityManager();
    Scanner input = new Scanner(System.in).useDelimiter("\n");

    public Cliente crearCliente() {
        Cliente cliente = new Cliente();
        try {
            System.out.println("Ingrese el Nombre del Alumno");
            String nombreAlumno = input.next();
            cliente.setNombre(nombreAlumno);
        } catch (Exception e) {
            System.out.println("Ingreso un valor incorrecto, intentelo nuevamente.");
            System.out.println("Tipo de Error: " + e.getMessage());
        }
        try {
            System.out.println("Ingrese el Apellido del Alumno");
            String apellidoAlumno = input.next();
            cliente.setApellido(apellidoAlumno);
        } catch (Exception e) {
            System.out.println("Ingreso un valor incorrecto, intentelo nuevamente.");
            System.out.println("Tipo de Error: " + e.getMessage());
        }
        try {
            System.out.println("Ingrese el numero de Documento, sin puntos");
            Long dniAlumno = input.nextLong();
            cliente.setDocumento(dniAlumno);
        } catch (Exception e) {
            System.out.println("Ingreso un valor incorrecto, intentelo nuevamente.");
            System.out.println("Tipo de Error: " + e.getMessage());
        }
        try {
            System.out.println("Ingrese el numero de Telefono del Alumno");
            String telefonoAlumno = input.next();
            cliente.setTelefono(telefonoAlumno);

        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            System.out.println("Ingreso un valor incorrecto, intentelo nuevamente.");
            System.out.println("Tipo de Error: " + e.getMessage());
        }
        System.out.println("Su usuario y contraseña fueron generados automaticamente");
        System.out.println("tome nota:");
        System.out.println("User: alumno");
        System.out.println("Password: ****");
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
        return cliente;
    }
}
