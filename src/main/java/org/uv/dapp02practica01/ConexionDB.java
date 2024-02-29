/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ConexionDB {

    private static ConexionDB cx = null;
    private Connection con = null;

    private ConexionDB() {
        try {
            String url = "jdbc:postgresql://localhost:5432/testDB";
            String pwd = "root";
            String usr = "postgres";

            con = DriverManager.getConnection(url, usr, pwd);
            con.setAutoCommit(false);

            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "Se Conecto!");

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConexionDB getInstance() {
        if (cx == null) {
            cx = new ConexionDB();
        }
        return cx;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return con.prepareStatement(sql);
    }
    
    public boolean execute(TransactionDB t){
        return t.execute(con);
    }

    public void commit() throws SQLException {
        con.commit();

    }

    public void rollback()  {

        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean closed() {
        boolean ban = false;
        if (con != null) {
            try {
                con.close();
                ban = true;
            } catch (SQLException ex) {
                Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ban;
    }

}
