/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dapp02practica01;

import java.sql.Connection;

/**
 *
 * @author ASUS
 */
public abstract class TransactionDB<T> {
    protected T pojo;

    public TransactionDB(T pojo) {
        this.pojo = pojo;
    }
    
    public abstract boolean execute(Connection con );
}
