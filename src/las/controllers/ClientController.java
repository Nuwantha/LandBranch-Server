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

/**
 *
 * @author Gimhani
 */
public class ClientController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static boolean addNewClient(Client client) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Insert into Client Values('"+client.getRegNo()+"','" + client.getNIC() + "','" + client.getClientName() + "','" + client.getBirthday() + "','" + client.getTelephone() + "','" + client.getAddress() + "','" + client.getAnnualIncome() + "','" + client.getGrantOwnershipPosition() + "','" + client.getPermitOwnershipPosition() + "','" + client.isMarried() + "','" + client.getNumberOfMarriedSons() + "','" + client.getNumberOfUnmarriedSons() + "')";
            int returnValue = DBHandler.setData(conn, sql);

            return returnValue > 0;

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static Client searchClient(String NIC) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Client where NIC='" + NIC + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                int marriedStatus = 1;
                boolean aBoolean = rst.getBoolean("MarriedStatus");
                if (!aBoolean) {
                    marriedStatus = 0;
                }
                Client client = new Client(rst.getInt("RegNo"),rst.getString("NIC"), rst.getString("ClientName"), rst.getString("Birthday"), rst.getString("Telephone"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("GrantOwnershipPosition"), rst.getInt("PermitOwnershipPosition"), marriedStatus, rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                return client;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Client> getSimmilarNICs(String nicPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From client where NIC like '" + nicPart + "%'  order by NIC limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                int marriedStatus = 1;
                boolean aBoolean = rst.getBoolean("MarriedStatus");
                if (!aBoolean) {
                    marriedStatus = 0;
                }
                Client client = new Client(rst.getInt("RegNo"),rst.getString("NIC"), rst.getString("ClientName"), rst.getString("Birthday"), rst.getString("Telephone"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("GrantOwnershipPosition"), rst.getInt("PermitOwnershipPosition"), marriedStatus, rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Client> getNoPermitOwners(String nicPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From client where permitownershipPosition = '0' and grantownershipposition = '0' and NIC like '" + nicPart + "%'  order by NIC limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                int marriedStatus = 1;
                boolean aBoolean = rst.getBoolean("MarriedStatus");
                if (!aBoolean) {
                    marriedStatus = 0;
                }
                Client client = new Client(rst.getString("NIC"), rst.getString("ClientName"), rst.getString("Birthday"), rst.getString("Telephone"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("GrantOwnershipPosition"), rst.getInt("PermitOwnershipPosition"), marriedStatus, rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;

        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static int updateClient(Client client) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Update  Client Set  clientName='" + client.getClientName() + "', Birthday='" + client.getBirthday() + "',Telephone='" + client.getTelephone() + "', Address='" + client.getAddress() + "', AnnualIncome='" + client.getAnnualIncome() + "', GrantOwnershipPosition='" + client.getGrantOwnershipPosition() + "',PermitOwnershipPosition='" + client.getPermitOwnershipPosition() + "',MarriedStatus='" + client.isMarried() + "',NumberOfMarriedSons='" + client.getNumberOfMarriedSons() + "',NumberOfUnmarriedSons='" + client.getNumberOfUnmarriedSons() + "' Where NIC ='" + client.getNIC() + "'";
            int res = DBHandler.setData(conn, sql);
            return res;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static ArrayList<Client> getAllClients() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Client";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                int marriedStatus = 1;
                boolean aBoolean = rst.getBoolean("MarriedStatus");
                if (!aBoolean) {
                    marriedStatus = 0;
                }
                Client client = new Client(rst.getInt("RegNo"),rst.getString("NIC"), rst.getString("ClientName"), rst.getString("Birthday"), rst.getString("Telephone"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("GrantOwnershipPosition"), rst.getInt("PermitOwnershipPosition"), marriedStatus, rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static Client getLastAddedClient() throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From Client order by RegNo Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {

                int marriedStatus = 1;
                boolean aBoolean = rst.getBoolean("MarriedStatus");
                if (!aBoolean) {
                    marriedStatus = 0;
                }
                Client client = new Client(rst.getInt("regNo"),rst.getString("NIC"), rst.getString("ClientName"), rst.getString("Birthday"), rst.getString("Telephone"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("GrantOwnershipPosition"), rst.getInt("PermitOwnershipPosition"), marriedStatus, rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                return client;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static int getnextOwnershiPositionPermit(String permitNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select ownershipPositionPermit From Client natural join permit where permitNumber ='" + permitNumber + "'  order by ownershipPositionPermit Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            int position = 1;
            if (rst.next()) {
                position = rst.getInt("ownershipPositionPermit") + 1;
            }

            return position;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static int getnextOwnershiPositionGrant(String grantNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select ownershipPositionGrant From Client natural join grant where grantNumber ='" + grantNumber + "'  order by ownershipPositionGrant Desc limit 1";
            ResultSet rst = DBHandler.getData(conn, sql);
            int position = 1;
            if (rst.next()) {
                position = rst.getInt("ownershipPositionPermit") + 1;
            }

            return position;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Client> getSimilarNames(String namePart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From client where ClientName like '" + namePart + "%'  order by NIC ";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Client> clientList = new ArrayList<>();
            while (rst.next()) {
                int marriedStatus = 1;
                boolean aBoolean = rst.getBoolean("MarriedStatus");
                if (!aBoolean) {
                    marriedStatus = 0;
                }
                Client client = new Client(rst.getInt("regNo"),rst.getString("NIC"), rst.getString("ClientName"), rst.getString("Birthday"), rst.getString("Telephone"), rst.getString("Address"), rst.getDouble("AnnualIncome"), rst.getInt("GrantOwnershipPosition"), rst.getInt("PermitOwnershipPosition"), marriedStatus, rst.getInt("NumberOfMarriedSons"), rst.getInt("NumberOfUnmarriedSons"));
                clientList.add(client);
            }
            return clientList;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
