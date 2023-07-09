package principal;

import java.util.Scanner;
import libreria.servicios.AutorService;
import libreria.servicios.ClienteService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;
import libreria.servicios.PrestamoService;

public class Libreria {

    public static void main(String[] args) {

        AutorService as = new AutorService();
        LibroService ls = new LibroService();
        EditorialService es = new EditorialService();
        ClienteService cs = new ClienteService();
PrestamoService ps = new PrestamoService();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        //para cargar libros modificarlos utilizar admin
        //pass: *****
        //para alumnos alumno
        //pass ****
        try {
            boolean ingreso = false;
            do {
                System.out.println("");
                System.out.println("Ingrese con sus credenciales...");
                System.out.println("Ingrese su Usuario:");
                String user = input.next();
                System.out.println("Ingrese su contraseña");
                String pass = input.next();
                if (user.equals("alumno") && pass.equals("****")) {
                    ingreso = true;
                    int opcionA;
                    do { System.out.println("******************************************************");
                    System.out.println("*             Ingrese la opcion deseada              *");
                    System.out.println("* 1) Pedir prestamo o Devolver un Libro              *");
                    System.out.println("* 2) Conocer la cantidad de Prestamos de un Cliente  *");
                    System.out.println("* 3) Salir                                           *");
                    System.out.println("******************************************************");
                    opcionA = input.nextInt();
                    
                       switch(opcionA){
                           case 1:
                               ps.crearPrestamo();
                               break;
                           case 2:
                               ps.cantidadDePrestamos();
                               break;
                           case 3:
                               System.out.println("Cerrando session....");
                               System.out.println("Gracias por utilizar nuestro servicio");
                               break;
                           default:
                               System.out.println("Ingrese una opcion valida");
                       }
                    } while (opcionA != 3);

                } else if (user.equals("admin") && pass.equals("*****")) {
                    ingreso = true;
                    int opcion;
                    do {
                        System.out.println("");
                        System.out.println("****************************************************");
                        System.out.println("*          Ingrese la opcion deseada               *");
                        System.out.println("* 1) Ingrese este numero para Adicionar            *");
                        System.out.println("* 2) Ingrese este numero para Modificiones         *");
                        System.out.println("* 3) Ingrese esta opcion para Eliminar             *");
                        System.out.println("* 4) Ingrese esta opcion para realizar Busquedas   *");
                        System.out.println("* 5) Salir                                         *");
                        System.out.println("****************************************************");
                        System.out.println("");
                        opcion = input.nextInt();
                        switch (opcion) {
                            case 1:
                                int opc2;
                                do {
                                    System.out.println("");
                                    System.out.println("********************************");
                                    System.out.println("*  Ingrese la opcion deseada   *");
                                    System.out.println("* 1) Agregar un Libro          *");
                                    System.out.println("* 2) Agregar una Editorial     *");
                                    System.out.println("* 3) Agregar un Autor          *");
                                    System.out.println("* 4) Regresar al menu anterior *");
                                    System.out.println("********************************");
                                    System.out.println("");
                                    opc2 = input.nextInt();
                                    switch (opc2) {
                                        case 1:
                                            ls.crearLibro();
                                            break;
                                        case 2:
                                            es.crearEditorial();
                                            break;
                                        case 3:
                                            as.crearAutor();
                                            break;
                                        case 4:
                                            System.out.println("<-----Regresando al menu principal....");
                                            break;
                                        default:
                                            System.out.println("Ingrese una opcion valida");
                                            break;
                                    }
                                } while (opc2 != 4);
                                break;
                            case 2:
                                int opc3;
                                do {
                                    System.out.println("");
                                    System.out.println("********************************");
                                    System.out.println("*  Ingrese la opcion deseada   *");
                                    System.out.println("* 1) Modificar un Libro        *");
                                    System.out.println("* 2) Modificar un Autor        *");
                                    System.out.println("* 3) Modificar una Editorial   *");
                                    System.out.println("* 4) Salir                     *");
                                    System.out.println("********************************");
                                    System.out.println("");
                                    opc3 = input.nextInt();
                                    switch (opc3) {
                                        case 1:
                                            ls.buscarLibroPorAutor();
                                            break;
                                        case 2:
                                            as.modificarAutor();
                                            break;
                                        case 3:
                                            es.modificarEditorial();
                                            break;
                                        case 4:
                                            System.out.println(" <-----Regresando al menu Principal");
                                            break;
                                        default:
                                            System.out.println("Ingrese una opcion valida");
                                            break;
                                    }
                                } while (opc3 != 4);

                                break;
                            case 3:
                                int opc4;
                                do {
                                    System.out.println("");
                                    System.out.println("********************************");
                                    System.out.println("*  Ingrese la opcion deseada   *");
                                    System.out.println("* 1) Eliminar un Libro         *");
                                    System.out.println("* 2) Eliminar un Autor         *");
                                    System.out.println("* 3) Eliminar una Editorial    *");
                                    System.out.println("* 4) Regresar al menu anterior *");
                                    System.out.println("********************************");
                                    System.out.println("");
                                    opc4 = input.nextInt();
                                    switch (opc4) {
                                        case 1:
                                            ls.eliminarLibro();
                                            break;
                                        case 2:
                                            as.eliminarAutor();
                                            break;
                                        case 3:
                                            es.eliminarEditorial();
                                            break;
                                        case 4:
                                            System.out.println(" <-----Regresando al menu Principal");
                                            break;
                                        default:
                                            System.out.println("Ingrese una opcion valida");
                                    }
                                } while (opc4 != 4);
                                break;

                            case 4:
                                int opc5;
                                do {
                                    System.out.println("");
                                    System.out.println("*********************************");
                                    System.out.println("*  Ingrese la opcion deseada    *");
                                    System.out.println("* 1) Desea buscar un Libro      *");
                                    System.out.println("* 2) Desea buscar un Autor      *");
                                    System.out.println("* 3) Desea buscar una Editorial *");
                                    System.out.println("* 4) Regresar al menu anterior  *");
                                    System.out.println("*********************************");
                                    System.out.println("");
                                    opc5 = input.nextInt();
                                    switch (opc5) {
                                        case 1:
                                            ls.buscarLibro();
                                            break;
                                        case 2:
                                            as.buscarAutor();
                                            break;
                                        case 3:
                                            es.buscarEditorial();
                                            break;
                                        case 4:
                                            System.out.println(" <-----Regresando al menu Principal");
                                            break;
                                        default:
                                            System.out.println("Ingrese una opcion valida");
                                    }
                                } while (opc5 != 4);
                                break;
                            case 5:
                                System.out.println("Gracias por utilizar nuestros servicios.");
                                break;
                            default:
                                System.out.println("Ingrese una opcion valida");
                                break;
                        }
                    } while (opcion != 5);
                } else if (ingreso == false) {
                    System.out.println("Si no esta registrado, registrese por favor en caso de ser alumno");
                    System.out.println("En caso de ser empleado admistrativo comuniquese "
                            + "\n con el area correspondiente");
                    System.out.println("");
                    cs.crearCliente();
                }
            } while (ingreso == false);

        } catch (Exception e) {
            System.out.println("Contraseña o usuario incorrecto, intentelo nuevamente.");
            System.out.println("Tipo de Error: " + e.getMessage());
        }
    }

}
