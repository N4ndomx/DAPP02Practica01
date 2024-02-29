/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsabilidad de Consultar, Acceso a datos
 *
 * @author ASUS
 */
public class DAOEmpleados implements IDAO<PojoEmpleado> {

    private final ConexionDB cnx;
    private static final String INSERT_EMPLEADOS_SQL = "INSERT INTO EMPLEADOS "
            + "  ( nombre, telefono, direccion) VALUES " + " ( ?, ?, ?);";
    private static final String SELECT_EMPLEADOS_SQL = "SELECT * FROM EMPLEADOS ;";
    private static final String UPDATE_EMPLEADOS_SQL = "UPDATE EMPLEADOS SET  nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
    private static final String DELETE_EMPLEADOS_SQL = "DELETE FROM EMPLEADOS WHERE id = ?;";

    public DAOEmpleados(ConexionDB cnx) {
        this.cnx = cnx;
    }

    @Override
    public boolean guardar(PojoEmpleado p) {
        boolean flag = false;

        TransactionDB t = new TransactionDB(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    PreparedStatement insertSt = cnx.getPreparedStatement(INSERT_EMPLEADOS_SQL);
                    insertSt.setString(1, p.getNombre());
                    insertSt.setString(2, p.getTelefono());
                    insertSt.setString(3, p.getDireccion());
                    insertSt.execute();
                    cnx.commit();
                    return  true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleados.class.getName()).log(Level.SEVERE, "Error Insert", ex);
                    return false;
                }
            }
        };

        return cnx.execute(t);
    }

    @Override
    public boolean modificar(PojoEmpleado p) {
        boolean flag = false;

        try {
            PreparedStatement insertSt = cnx.getPreparedStatement(UPDATE_EMPLEADOS_SQL);
            insertSt.setString(1, p.getNombre());
            insertSt.setString(2, p.getTelefono());
            insertSt.setString(3, p.getDireccion());
            insertSt.setInt(4, p.getId());

            flag = insertSt.executeUpdate() > 0;
            cnx.commit();

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleados.class.getName()).log(Level.SEVERE, "Error Modifi", ex);
            cnx.rollback();
        }
        return flag;
    }

    @Override
    public List<PojoEmpleado> buscarAll() {
        List<PojoEmpleado> empleados = new ArrayList<>();
        try {
            PreparedStatement insertSt = cnx.getPreparedStatement(SELECT_EMPLEADOS_SQL);
            ResultSet resultSet = insertSt.executeQuery();
            while (resultSet.next()) {
                int clave = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                PojoEmpleado pojo = new PojoEmpleado();
                pojo.setDireccion(direccion);
                pojo.setId(clave);
                pojo.setNombre(nombre);
                pojo.setTelefono(telefono);
                empleados.add(pojo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleados.class.getName()).log(Level.SEVERE, "Error buscarAll", ex);
            cnx.rollback();

        }
        return empleados;
    }

    @Override
    public PojoEmpleado buscarById(int id) {
        PojoEmpleado pojo = null;
        try {
            PreparedStatement insertSt = cnx.getPreparedStatement(SELECT_EMPLEADOS_SQL + " WHERE clave = ?;");
            insertSt.setInt(1, id);
            ResultSet resultSet = insertSt.executeQuery();
            if (resultSet.next()) {
                int clave = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                pojo = new PojoEmpleado();
                pojo.setId(clave);
                pojo.setNombre(nombre);
                pojo.setDireccion(direccion);
                pojo.setTelefono(telefono);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            cnx.rollback();

        }
        return pojo;
    }

    @Override
    public boolean eliminar(PojoEmpleado p) {
        boolean flag = false;

        try {
            PreparedStatement insertSt = cnx.getPreparedStatement(DELETE_EMPLEADOS_SQL);
            insertSt.setInt(1, p.getId());
            flag = insertSt.executeUpdate() > 0;
            cnx.commit();

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleados.class.getName()).log(Level.SEVERE, "Error Eliminar", ex);
            cnx.rollback();

        }
        return flag;
    }

}
