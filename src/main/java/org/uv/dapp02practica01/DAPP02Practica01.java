/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ASUS
 */
public class DAPP02Practica01 {

    public static void main(String[] args) {
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session session = sf.getCurrentSession();
//        Transaction tr = session.beginTransaction();
//
//        Venta v = new Venta();
//        v.setClienre("Kolaloca");
//        v.setTotal(8000.0d);
//        v.setFechaventa(new java.sql.Date(new Date().getTime()));
//        System.out.println("Hola y adios " + v.getClienre());
//        
//        for (int i = 0; i < 5; i++) {
//            DetalleVenta dtv = new DetalleVenta();
//            dtv.setPrecio(10);
//            dtv.setProducto("Produc"+(i+1));
//            dtv.setCantidad(10);
//            dtv.setVenta(v);
//            v.getDetalleVenta().add(dtv);
//        }
//        session.save(v);
//        
//        tr.commit();

        IDAO<Venta> dao = new DAOVenta();
        Scanner scanner = new Scanner(System.in);
        Venta pojoVenta = new Venta();

        int opcion;
        do {
            System.out.println("\n===== Menú Principal =====");
            System.out.println("1. Registrar una Venta");
            System.out.println("2. Listar Todas los Ventas");
            System.out.println("3. Actualizar Datos de una Venta");
            System.out.println("4. Eliminar unaa venta");

            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el  nombre del Cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Ingrese el  Total de la venta: ");
                    String total = scanner.nextLine();
                    pojoVenta.setClienre(cliente);
                    pojoVenta.setFechaventa(new java.sql.Date(new Date().getTime()));
                    pojoVenta.setTotal(Double.parseDouble(total));

                    System.out.print("Ingrese el  N° de la Productos de la venta: ");
                    String ndetalles = scanner.nextLine();

                    for (int i = 0; i < Integer.parseInt(ndetalles); i++) {
                        DetalleVenta dtv = new DetalleVenta();
                        System.out.print("Ingrese el  nombre del Producto " + (i + 1) + ": ");
                        String name = scanner.nextLine();
                        dtv.setProducto(name);
                        System.out.print("Ingrese el precio del Producto: ");
                        String precioP = scanner.nextLine();

                        dtv.setPrecio(Integer.parseInt(precioP));
                        System.out.print("Ingrese la cantidad del Producto: ");
                        String cantidad = scanner.nextLine();
                        dtv.setCantidad(Integer.parseInt(cantidad));

                        dtv.setVenta(pojoVenta);
                        pojoVenta.getDetalleVenta().add(dtv);
                    }

                    boolean bandera = dao.guardar(pojoVenta);
                    if (bandera) {
                        System.out.print("Se Ingreso Empleado");

                    } else {
                        System.out.print("Ocurrio un Error");

                    }

                    break;
                case 2:
                    System.out.println("\n===>> VENTAS <<===");

                    for (Venta elemento : dao.buscarAll()) {
                        System.out.println(
                                "\n<<<<<--VENTA--->>>>>>"
                                + "\n id :" + elemento.getId()
                                + "\n Cliente :" + elemento.getClienre()
                                + "\n Fecha :" + elemento.getFechaventa()
                                + "\n Total :" + elemento.getTotal()
                        );
                        for (DetalleVenta dv : elemento.getDetalleVenta()) {
                            System.out.println(
                                    "\n------------Detalle Venta--------"
                                    + "\n id :" + dv.getId()
                                    + "\n Producto :" + dv.getProducto()
                                    + "\n Cantidad :" + dv.getCantidad()
                                    + "\n Precio :" + dv.getPrecio()
                                    + "\n"
                            );
                        }
                    }

                    break;

                case 3:
                    System.out.print("Ingrese la Id de la venta a actualizar: ");
                    String idup = scanner.nextLine();
                    pojoVenta.setId(Integer.parseInt(idup));

                    // Obtener la venta existente para realizar la actualización
                    Venta ventaExistente = dao.buscarById(Long.parseLong(idup));
                    if (ventaExistente != null) {
                        // Permitir al usuario ingresar la nueva información de la venta
                        System.out.print("Ingrese el nuevo nombre del Cliente: ");
                        String nuevoCliente = scanner.nextLine();
                        System.out.print("Ingrese el nuevo Total de la venta: ");
                        String nuevoTotal = scanner.nextLine();

                        pojoVenta.setClienre(nuevoCliente);
                        pojoVenta.setTotal(Double.parseDouble(nuevoTotal));
                        pojoVenta.setFechaventa(pojoVenta.getFechaventa());
                        // Obtener los detalles de venta existentes
                        List<DetalleVenta> detallesActuales = ventaExistente.getDetalleVenta();

                        // Permitir al usuario modificar los detalles de venta existentes
                        for (DetalleVenta detalle : detallesActuales) {
                            System.out.println("Detalles de Venta Actuales:");
                            System.out.println("Producto: " + detalle.getProducto());
                            System.out.println("Precio: " + detalle.getPrecio());
                            System.out.println("Cantidad: " + detalle.getCantidad());

                            System.out.print("¿Desea modificar este detalle de venta? (S/N): ");
                            String respuesta = scanner.nextLine().toUpperCase();

                            if ("S".equals(respuesta)) {
                                System.out.print("Ingrese el nuevo Nombre del Producto: ");
                                String newname = scanner.nextLine();
                                scanner.nextLine(); // Consumir el salto de línea después del número
                                detalle.setProducto(newname);

                                System.out.print("Ingrese el nuevo precio del Producto: ");
                                double nuevoPrecio = scanner.nextDouble();
                                scanner.nextLine(); // Consumir el salto de línea después del número
                                detalle.setPrecio(nuevoPrecio);

                                System.out.print("Ingrese la nueva cantidad del Producto: ");
                                double nuevaCantidad = scanner.nextDouble();
                                scanner.nextLine(); // Consumir el salto de línea después del número
                                detalle.setCantidad(nuevaCantidad);
                            }
                        }

                        pojoVenta.setDetalleVenta(detallesActuales);

                        boolean ban = dao.modificar(pojoVenta);
                        if (ban) {
                            System.out.print("Se Actualizó la Venta");
                        } else {
                            System.out.print("No se logró actualizar la Venta");
                        }
                    } else {
                        System.out.println("La venta con ID " + idup + " no existe.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese la Id del una venta ");
                    String id = scanner.nextLine();
                    pojoVenta.setId(Integer.parseInt(id));
                    if (!dao.eliminar(pojoVenta)) {
                        System.out.print("No se logro Eliminar Empleado :");
                    } else {
                        System.out.print("Se Elimino Empleado ");
                    }

                    break;

                case 5:
                    System.out.println("Saliendo ...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);

    }
}
