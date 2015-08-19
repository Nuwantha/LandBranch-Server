/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import las.db_utilities.DBConnection;
import las.db_utilities.DBHandler;
import las.models.Land;
import las.models.Lot;

/**
 *
 * @author Gimhani
 */
public class LotController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static boolean addNewLot(Lot lot) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Insert into Lot Values('" + lot.getLotNumber() + "','" + lot.getNumberOfAcres() + "','" + lot.getNumberOfPerches() + "','" + lot.getNumberofRoods() + "','" + lot.getLand().getPlanNumber() + "')";
            int returnValue = DBHandler.setData(conn, sql);
            return returnValue > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Lot searchLot(String lotNumber) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where LotNumber='" + lotNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land searchLand = LandController.searchLand(rst.getString("PlanNumber"));
                Lot lot = new Lot(rst.getString("LotNumber"), rst.getInt("NumberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"), searchLand);
                return lot;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Lot> searchLotOfLand(String planNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where planNumber='" + planNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Lot> lotList = new ArrayList();
            while (rst.next()) {
                Lot lot = new Lot(rst.getString("LotNumber"), rst.getInt("NumberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"));
                lotList.add(lot);
            }
            return lotList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Lot> getAvailableLotOfLand(String planNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Lot where planNumber='" + planNumber + "' and isAvailabal = 0 ";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Lot> lotList = new ArrayList();
            while (rst.next()) {
                Lot lot = new Lot(rst.getString("LotNumber"), rst.getInt("NumberOfAcres"), rst.getInt("NumberOfRoods"), rst.getInt("NumberOfPerches"));
                lotList.add(lot);
            }
            return lotList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean updateLot(Lot lot) throws ClassNotFoundException, SQLException {

        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update  Lot Set  NumberOfAcres='" + lot.getNumberOfAcres() + "', NumberOfPerches='" + lot.getNumberOfPerches() + "',NumberofRoods='" + lot.getNumberofRoods() + "',isAvailabal='" + lot.getIsAvilable() + "' Where  LotNumber='" + lot.getLotNumber() + "'";
            int res = DBHandler.setData(conn, sql);
            return res > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Lot getLastAddedLot() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Lot order by lotNumber Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Land Land = LandController.searchLand(rst.getString("PlanNumber"));
                Lot lot = new Lot(rst.getString("LotNumber"), rst.getInt("NumberOfAcres"), rst.getInt("NumberOfPerches"), rst.getInt("NumberOfRoods"), Land);
                return lot;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

}
