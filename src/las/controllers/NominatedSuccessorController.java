/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import las.db_utilities.DBConnection;
import las.db_utilities.DBHandler;
import las.models.NominatedSuccessor;

/**
 *
 * @author Gimhani
 */
public class NominatedSuccessorController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static NominatedSuccessor searchNominateSuccessor(String NICS) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from NominatedSuccessor where NIC_S='" + NICS + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                NominatedSuccessor nominateSuccessor = new NominatedSuccessor(rst.getString("NIC_S"), rst.getString("Name"), rst.getString("Address"));
                return nominateSuccessor;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static boolean addNewNominateSuccessor(NominatedSuccessor NOS) throws ClassNotFoundException, SQLException {

        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Insert into nominatedsuccessor Values('" + NOS.getName() + "','" + NOS.getNIC_S() + "','" + NOS.getAddress() + "')";
            int returnValue = DBHandler.setData(conn, sql);
            return returnValue > 0;

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean updateNiminateSuccessor(NominatedSuccessor nominateSuccessor) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update  nominatedsuccessor Set  Name='" + nominateSuccessor.getName() + "', Address='" + nominateSuccessor.getAddress() + "' Where NIC_S ='" + nominateSuccessor.getNIC_S() + "'";
            int res = DBHandler.setData(conn, sql);
            return res > 0;

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean DeleteNominatedSuccessor(String nic) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Delete  from nominatedSuccessor where NIC_S = '" + nic + "'";
        int returnDelete = DBHandler.setData(conn, sql);
        return returnDelete > 0;
    }

}
