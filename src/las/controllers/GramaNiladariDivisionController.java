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
import las.models.Client;
import las.models.GramaNiladariDivision;
import las.models.Lot;
import las.models.NominatedSuccessor;
import las.models.Permit;

/**
 *
 * @author Gimhani
 */
public class GramaNiladariDivisionController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static boolean addNewGND(GramaNiladariDivision GND) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Insert into GND Values('" + GND.getDivisionNumber() + "','" + GND.getDivisionName() + "','" + GND.getGramaNilardariName() + "','" + GND.getZoneName() + "'";
            int returnValue = DBHandler.setData(conn, sql);
            return returnValue > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static GramaNiladariDivision searchGND(String DivisionNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from GND where DivisionNumber='" + DivisionNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                GramaNiladariDivision GND = new GramaNiladariDivision(rst.getString("DivisionNumber"), rst.getString("DivisionName"), rst.getString("ZoneName"), rst.getString("GramaNiladariName"));
                return GND;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<GramaNiladariDivision> getSimmilarGndDivisionNumbers(String divisionNumberPart) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From GND where divisionNumber like '" + divisionNumberPart + "%'  order by divisionNumber limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<GramaNiladariDivision> GNDList = new ArrayList<>();
            while (rst.next()) {
                GramaNiladariDivision GND = new GramaNiladariDivision(rst.getString("DivisionNumber"), rst.getString("DivisionName"), rst.getString("ZoneName"), rst.getString("GramaNiladariName"));
                GNDList.add(GND);
            }
            return GNDList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean updateGND(GramaNiladariDivision GND) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.writeLock().lock();

            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "Update GND set DivisionName='" + GND.getDivisionName() + "', GramaNiladariName= '" + GND.getGramaNilardariName() + "',ZoneName='" + GND.getZoneName() + "' Where DivisionNumber ='" + GND.getDivisionNumber() + "'";
            int returnV = DBHandler.setData(connection, sql);
            return returnV > 0;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static ArrayList<GramaNiladariDivision> getAllGNDDetail() throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From GND";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<GramaNiladariDivision> GNDList = new ArrayList<>();
            while (rst.next()) {
                GramaNiladariDivision GND = new GramaNiladariDivision(rst.getString("DivisionNumber"), rst.getString("DivisionName"), rst.getString("ZoneName"), rst.getString("GramaNiladariName"));
                GNDList.add(GND);
            }
            return GNDList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static GramaNiladariDivision searchGNDByOfficer(String officer) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from GND where GramaNiladariName='" + officer + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                GramaNiladariDivision GND = new GramaNiladariDivision(rst.getString("DivisionNumber"), rst.getString("DivisionName"), rst.getString("ZoneName"), rst.getString("GramaNiladariName"));
                return GND;
            } else {
                return null;
            }

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<GramaNiladariDivision> getLandCount() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select land.divisionnumber,divisionname,count(plannumber) as noOfLands from land left join GND on land.divisionnumber=GND.divisionnumber group by land.divisionnumber;";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<GramaNiladariDivision> GNDList = new ArrayList<>();
            while (rst.next()) {
                GramaNiladariDivision GND = new GramaNiladariDivision(rst.getString("DivisionNumber"), rst.getString("DivisionName"), rst.getInt("noOfLands"));
                GNDList.add(GND);
            }
            return GNDList;
        } finally {
            readWriteLock.readLock().lock();
        }
    }

    public static ArrayList<Permit> getPermitsToCertify(String divisionnumber) throws SQLException, ClassNotFoundException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "select * from permit natural join lot natural join land natural join gnd where certified=0 and datediff(curdate(),permitissuedate)>300 and gnd.divisionnumber='" + divisionnumber + "' ;";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Permit> permitList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit permit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                permitList.add(permit);
            }
            return permitList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
