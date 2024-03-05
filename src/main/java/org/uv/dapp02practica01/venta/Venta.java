/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01.venta;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity()
@Table(name = "venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "venta_idventa_seq",
            sequenceName = "venta_idventa_seq",allocationSize = 1,initialValue = 1)
    @Column(name = "idventa")
            
    private long id;
    @Column(name = "cliente")
    private String clienre;
    @Column(name = "fecha")
    private Date fechaventa;
    @Column(name = "total")
    private double  total;
    
    
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DetalleVenta> detalleVenta;
     public Venta() {
        detalleVenta = new ArrayList<>();
    }

    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

    public String getClienre() {
        return clienre;
    }

    public void setClienre(String clienre) {
        this.clienre = clienre;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }
    
    
    
}
