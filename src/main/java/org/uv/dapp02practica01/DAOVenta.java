/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ASUS
 */
public class DAOVenta implements IDAO<Venta> {

    private SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public boolean guardar(Venta p) {
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            session.save(p);
            tran.commit(); // Fixed the transaction handling
            return true;
        
    }

    @Override
    public boolean modificar(Venta p) {
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            session.update(p);
            tran.commit();
            return true;
        
    }

    @Override
    public List<Venta> buscarAll() {
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            List<Venta> ventas = session.createQuery("from Venta").list();
            tran.commit();
            return ventas;
       
    }

    @Override
    public Venta buscarById(long id) {
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            Venta venta = session.get(Venta.class, id);
            tran.commit();
            return venta;
        
    }

    @Override
    public boolean eliminar(Venta p) {
        Session session = sf.getCurrentSession();
        Transaction tran = session.beginTransaction();
            session.delete(p);
            tran.commit();
            return true;
        
    }
}
