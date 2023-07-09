package libreria.servicios;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;

public class PrestamoService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    EntityManager em = emf.createEntityManager();
    Scanner input = new Scanner(System.in).useDelimiter("\n");

    public Prestamo crearPrestamo() {
        Prestamo prestamo = new Prestamo();
        boolean noEncontrado = true;
        int opcion;

        do {
            System.out.println("****************************************************");
            System.out.println("* Ingresando al sistema de prestamos de Libros     *");
            System.out.println("*   Ingrese la opcion deseada                      *");
            System.out.println("* 1) ¿Desea solicitar el prestamo de un Libro?     *");
            System.out.println("* 2) ¿Desea devolver un Libro?                     *");
            System.out.println("* 3) Salir                                         *");
            System.out.println("****************************************************");
            opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del Libro");
                    String nombreLibro = input.next();
                    System.out.println("¿Cuantos libros desea pedir prestado?");
                    int cantidadLibro = input.nextInt();
                    System.out.println("Ingrese su numero de documento sin puntos");
                    Long documento = input.nextLong();
                    try {
                        do {
                            List<Cliente> clientes = em.createQuery("SELECT a FROM Cliente a").getResultList();
                            for (Cliente aux : clientes) {
                                if (aux.getDocumento().equals(documento)) {
                                    prestamo.setCliente(aux);
                                    noEncontrado = false;
                                    prestamo.setCliente(aux);
                                    prestamo.setFechaDePrestamo(new Date());
                                    em.getTransaction().begin();
                                    em.persist(prestamo);
                                    em.getTransaction().commit();
                                }
                            }
                            if (noEncontrado) {
                                System.out.println("El dni no se encuentra registrado, intente nuevamente");
                            }
                        } while (noEncontrado);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        em.getTransaction().rollback();
                        em.close();
                    }
                    try {
                        do {
                            List<Libro> libros = em.createQuery("SELECT a FROM Libro a").getResultList();
                            for (Libro aux : libros) {
                                if (aux.getTitulo().equalsIgnoreCase(nombreLibro)) {
                                    if (aux.getEjemplaresRestantes() > cantidadLibro) {
                                        int restaPrestamo = aux.getEjemplares() - cantidadLibro;
                                        aux.setEjemplaresPrestados(aux.getEjemplaresPrestados() + cantidadLibro);
                                        aux.setEjemplaresRestantes(restaPrestamo);
                                        prestamo.setLibro(aux);
                                        em.getTransaction().begin();
                                        em.persist(prestamo);
                                        em.getTransaction().commit();
                                    } else {
                                        System.out.println("La cantidad supera los ejemplares permitidos");
                                    }
                                    noEncontrado = false;

                                }
                            }
                            if (noEncontrado) {
                                System.out.println("El Libro no se encuentra en la biblioteca, intente con otro titulo");
                            }
                        } while (noEncontrado);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        em.getTransaction().rollback();
                        em.close();
                    }
                    System.out.println("El Libro " + nombreLibro + " se prestara por 48hs habiles."
                            + "\nRecuerde devolverlo en tiempo y forma.");
                    break;
                case 2:
                    System.out.println("Ingresando al sistema de devoluciones---->");
                    System.out.println("Ingrese su dni, sin puntos");
                    Long documentoB = input.nextLong();
                    try {
                        do {
                            List<Cliente> clientes = em.createQuery("SELECT a FROM Cliente a").getResultList();
                            for (Cliente aux : clientes) {
                                if (aux.getDocumento().equals(documentoB)) {
                                    System.out.println(aux);
                                    noEncontrado = false;

                                }
                            }
                            if (noEncontrado) {
                                System.out.println("El dni no se encuentra registrado, intente nuevamente");
                            }
                        } while (noEncontrado);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        em.getTransaction().rollback();
                        em.close();
                    }
                    System.out.println("Ingrese el nombre del Libro");
                    String nombreLibroB = input.next();
                    System.out.println("¿Cuantos Libros devolvera del mismo autor?");
                    int devolucionLibro = input.nextInt();
                    try {
                        do {
                            List<Libro> libros = em.createQuery("SELECT a FROM Libro a").getResultList();
                            for (Libro aux : libros) {
                                if (aux.getTitulo().equalsIgnoreCase(nombreLibroB)) {
                                    if (aux.getEjemplaresRestantes() > devolucionLibro) {
                                        int sumaPrestamo = aux.getEjemplares() - aux.getEjemplaresPrestados();
                                        aux.setEjemplaresPrestados(aux.getEjemplaresPrestados() - devolucionLibro);
                                        aux.setEjemplaresRestantes(sumaPrestamo);
                                        System.out.println("Del Libro " + aux.getTitulo() + ", quedan: " + aux.getEjemplaresRestantes());
                                        prestamo.setLibro(aux);
                                        prestamo.setFechaDevolucion(new Date());
                                        em.getTransaction().begin();
                                        em.persist(prestamo);
                                        em.merge(aux);
                                        em.getTransaction().commit();
                                    } else {
                                        System.out.println("La cantidad supera los ejemplares permitidos");
                                    }
                                    noEncontrado = false;

                                }
                            }
                            if (noEncontrado) {
                                System.out.println("El Libro no se encuentra en la biblioteca, intente con otro titulo");
                            }
                        } while (noEncontrado);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        em.getTransaction().rollback();
                        em.close();
                    }
                    break;
                case 3:
                    System.out.println("<--------------------Regresando al menu principal");
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
            }
        } while (opcion != 3);
        return prestamo;

    }
    
    
    public void cantidadDePrestamos(){
        System.out.println("Ingrese el DNI sin puntos, para conocer la cantidad de prestamos hechos por la persona");
        Long prestamo = input.nextLong();
        try {
            List<Prestamo>prestamos = em.createQuery("SELECT a FROM Prestamo a").getResultList();
            for (Prestamo aux : prestamos) {
                System.out.println(aux);
            }
        } catch (Exception e) {
            System.out.println("Ingreso un valor incorrecto, intentelo nuevamente.");
            System.out.println("Tipo de Error: " + e.getMessage());
        }
        
    
    }
}


/*para setear a cero, cuando me paso de cero en ejemplares prestados.
Hay que solucionar ese metodo de validacion
        em.getTransaction().begin();
Query query = em.createQuery("UPDATE Libro n SET n.ejemplaresPrestados = :nuevoValor WHERE n.isbn = :isbn");
query.setParameter("nuevoValor", 0);
query.setParameter("isbn", Long.parseLong("202301"));

int filasActualizadas = query.executeUpdate();
em.getTransaction().commit();
*/
