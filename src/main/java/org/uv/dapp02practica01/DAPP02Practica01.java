/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.uv.dapp02practica01;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.uv.dapp02practica01.mensaje.IMensaje;
import org.uv.dapp02practica01.mensaje.Saludo;
import org.uv.dapp02practica01.venta.DetalleVenta;
import org.uv.dapp02practica01.venta.Venta;

/**
 *
 * @author ASUS
 */
public class DAPP02Practica01 {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
//        PojoEmpleado emp = new PojoEmpleado();
//        emp.setNombre("Fer");
//        emp.setDireccion("av ori");
//        emp.setTelefono("555512");
        Session session = sf.getCurrentSession();
        Transaction tr = session.beginTransaction();
//        session.save(emp);
//        emp.setId(1);

// get recupera directo de la base de datos y load lo carga de cache una ves que ya se recupera de bd
//        PojoEmpleado emp2 = session.get(PojoEmpleado.class, 1);
//        emp2.setNombre("albert");
//        session.save(emp2);
//        session.delete(emp2);
// ventas 
        Venta v = new Venta();
        v.setClienre("Kolaloca");
        v.setTotal(8000.0d);
        v.setFechaventa(new java.sql.Date(new Date().getTime()));
//        session.save(v);
//        tr.commit();
        System.out.println("Hola y adios " + v.getClienre());
        
        for (int i = 0; i < 5; i++) {
            DetalleVenta dtv = new DetalleVenta();
            dtv.setPrecio(10);
            dtv.setProducto("Produc"+(i+1));
            dtv.setCantidad(10);
            dtv.setVenta(v);
            v.getDetalleVenta().add(dtv);
//            session.save(dtv);
        }
        session.save(v);
        
        tr.commit();

    }
//
//        ConexionDB con = ConexionDB.getInstance();
//
//        IDAO<PojoEmpleado> dao = new DAOEmpleados(con);
//        Scanner scanner = new Scanner(System.in);
//        PojoEmpleado pojo = new PojoEmpleado();
//
//        int opcion;
//        do {
//            System.out.println("\n===== Menú Principal =====");
//            System.out.println("1. Registrar un Empleado");
//            System.out.println("2. Listar Todos los Empleado");
//            System.out.println("3. Actualizar Datos de un Empleado");
//            System.out.println("4. Eliminar un Empleado");
//
//            System.out.println("5. Salir");
//            System.out.print("Seleccione una opción: ");
//
//            opcion = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcion) {
//                case 1:
//                    System.out.print("Ingrese el  nombre del empleado: ");
//                    String nombre = scanner.nextLine();
//                    System.out.print("Ingrese la direccion del empleado: ");
//                    String dir = scanner.nextLine();
//                    System.out.print("Ingrese el telefono del empleado: ");
//                    String telf = scanner.nextLine();
//                    pojo.setNombre(nombre);
//                    pojo.setDireccion(dir);
//                    pojo.setTelefono(telf);
//                    boolean bandera = dao.guardar(pojo);
//                    if (bandera) {
//                        System.out.print("Se Ingreso Empleado");
//
//                    } else {
//                        System.out.print("Ocurrio un Error");
//
//                    }
//
//                    break;
//                case 2:
//                    System.out.println("\n===>> Empleados <<===");
//                    
//                    for (PojoEmpleado elemento : dao.buscarAll()) {
//                        System.out.println("id :" + elemento.getId()
//                                + "\n nombre :" + elemento.getNombre()
//                                + "\n dir :" + elemento.getDireccion());
//                    }
//
//                    break;
//                case 3:
//                    System.out.print("Ingrese la Id del empleado a Actualizar ");
//                    String idup = scanner.nextLine();
//                    System.out.print("Ingrese el  nombre del empleado: ");
//                    String nombreup = scanner.nextLine();
//                    System.out.print("Ingrese la direccion del empleado: ");
//                    String dirup = scanner.nextLine();
//                    System.out.print("Ingrese el telefono del empleado: ");
//                    String telfup = scanner.nextLine();
//                    pojo.setId(Integer.parseInt(idup));
//                    pojo.setNombre(nombreup);
//                    pojo.setDireccion(dirup);
//                    pojo.setTelefono(telfup);
//                    boolean ban = dao.modificar(pojo);
//                     if (ban) {
//                        System.out.print("Se Actualizo Empleado");
//
//                    } else {
//                        System.out.print("Actualizo Empleado");
//
//                    }
//                    break;
//                case 4:
//                    System.out.print("Ingrese la Id del empleado a Eliminar: ");
//                    String id = scanner.nextLine();
//                    pojo.setId(Integer.parseInt(id));
//                    if (!dao.eliminar(pojo)) {
//                        System.out.print("No se logro Eliminar Empleado :");
//                    } else {
//                        System.out.print("Se Elimino Empleado ");
//                    }
//
//                    break;
//
//                case 5:
//                    System.out.println("Saliendo ...");
//                    break;
//                default:
//                    System.out.println("Opción no válida. Intente de nuevo.");
//                    break;
//            }
//        } while (opcion != 5);
//
//    }
}
