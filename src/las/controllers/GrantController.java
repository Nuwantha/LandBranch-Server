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
import las.models.Grant;
import las.models.Lot;
import las.models.NominatedSuccessor;
import las.models.Permit;

/**
 *
 * @author Gimhani
 */
public class GrantController {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static Grant searchGrant(String grantNumber) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Grant where grantNumber='" + grantNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Permit searchPermit = PermitController.searchPermit(rst.getString("permitnumber"));
                Client client = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor("NICS");
                Grant grant = new Grant(rst.getString("GrantNumber"), rst.getString("GrantIssueDate"), searchPermit, searchLot, client, searchNominateSuccessor);
                return grant;
            } else {
                return null;
            }

        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static boolean addNewGrant(Grant grant) throws ClassNotFoundException, SQLException {

        try {

            readWriteLock.writeLock().lock();

            boolean returnStatue;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                String sql = "Insert into GRANT1 Values('" + grant.getGrantNumber() + "','" + grant.getGrantIssueDate() + "','" + grant.getPermit().getPermitNumber() + "','" + grant.getLot().getLotNumber() + "','" + grant.getClient().getNIC() + "','" + grant.getNominatedSuccessor().getNIC_S() + "')";
                int returnGrantInsert = DBHandler.setData(conn, sql);
                if (returnGrantInsert > 0) {
                    Client client = grant.getClient();
                    int position = ClientController.getnextOwnershiPositionGrant(grant.getGrantNumber());
                    client.setPermitOwnershipPosition(position);
                    int updateClient = ClientController.updateClient(client);
                    if (updateClient > 0) {
                        returnStatue = true;
                    } else {
                        returnStatue = false;
                        conn.rollback();
                    }
                } else {
                    returnStatue = false;
                    conn.rollback();
                }
                if (returnStatue) {
                    conn.commit();
                }
            } catch (SQLException sqlExeption) {
                returnStatue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return returnStatue;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static int getGrantCountOfDivision(String divisionNumber) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();

            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "select count(distinct grantNumber) as grantCount from grant natural join lot natural join land where divisionNumber ='" + divisionNumber + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            int grantCount = 0;
            if (rst.next()) {
                grantCount = rst.getInt("grantCount");

            }
            return grantCount;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static ArrayList<Grant> getSimilarGrantNumbers(String grantNumberPart) throws ClassNotFoundException, SQLException {
        try {

            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From grant1 where grantNumber like '%" + grantNumberPart + "%'  order by grantNumber limit 10";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Grant> grantList = new ArrayList<>();
            while (rst.next()) {
                Permit searchPermit = PermitController.searchPermit(rst.getString("PermitNumber"));
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Grant grant = new Grant(rst.getString("GrantNumber"), rst.getString("GrantIssueDate"), searchPermit, searchLot, searchClient, searchNominateSuccessor);
                grantList.add(grant);
            }
            return grantList;
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static Grant searchGrantByClient(String NIC) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from Grant1 where NIC='" + NIC + "'";
            ResultSet rst = DBHandler.getData(conn, sql);
            if (rst.next()) {
                Permit searchPermit = PermitController.searchPermit(rst.getString("permitnumber"));
                Client client = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor("NICS");
                Grant grant = new Grant(rst.getString("GrantNumber"), rst.getString("GrantIssueDate"), searchPermit, searchLot, client, searchNominateSuccessor);
                return grant;
            } else {
                return null;
            }
        } finally {
            readWriteLock.readLock().lock();
        }
    }

    public static ArrayList<Grant> getSimilarPermitNumberGrants(String permitNumberPart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * From grant1 left join permit on grant1.permitnumber=permit.permitnumber where permitnumber like '%" + permitNumberPart + "%'  order by grantNumber";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Grant> grantList = new ArrayList<>();
            while (rst.next()) {
                Permit searchPermit = PermitController.searchPermit(rst.getString("PermitNumber"));
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Grant grant = new Grant(rst.getString("GrantNumber"), rst.getString("GrantIssueDate"), searchPermit, searchLot, searchClient, searchNominateSuccessor);
                grantList.add(grant);
            }
            return grantList;

        } finally {
            readWriteLock.readLock().lock();
        }
    }

    public static ArrayList<Grant> getSimilarGrantsByName(String namepart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from client right join grant1 on client.NIC=grant1.NIC where ClientName like '%" + namepart + "%'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Grant> grantList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit searchPermit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                Grant grant = new Grant(rst.getString("GrantNumber"), rst.getString("GrantIssueDate"), searchPermit, searchLot, searchClient, searchNominateSuccessor);
                grantList.add(grant);
            }
            return grantList;

        } finally {
            readWriteLock.readLock().lock();
        }
    }

    public static ArrayList<Grant> getSimilarPermitsByNIC(String nicpart) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.readLock().lock();
            Connection conn = DBConnection.getDBConnection().getConnection();
            String sql = "Select * from client right join grant1 on client.NIC=grant1.NIC where grant1.NIC like '%" + nicpart + "%'";
            ResultSet rst = DBHandler.getData(conn, sql);
            ArrayList<Grant> grantList = new ArrayList<>();
            while (rst.next()) {
                Client searchClient = ClientController.searchClient(rst.getString("NIC"));
                Lot searchLot = LotController.searchLot(rst.getString("LotNumber"));
                NominatedSuccessor searchNominateSuccessor = NominatedSuccessorController.searchNominateSuccessor(rst.getString("NIC_Successor"));
                Permit searchPermit = PermitController.searchPermit(rst.getString("PermitNUmber"));

                //   Permit searchPermit = new Permit(rst.getString("PermitNumber"), rst.getString("PermitIssueDate"), searchLot, searchClient, searchNominateSuccessor);
                Grant grant = new Grant(rst.getString("GrantNumber"), rst.getString("GrantIssueDate"), searchPermit, searchLot, searchClient, searchNominateSuccessor);
                grantList.add(grant);
            }
            return grantList;

        } finally {
            readWriteLock.readLock().lock();
        }
    }

    public static boolean changeGrantOwnership(Grant grant) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            boolean returnStatue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                boolean addNewClient = ClientController.addNewClient(grant.getClient());
                if (addNewClient) {
                    System.err.println("new client added");
                    String sql = "Insert into GRANT1 Values('" + grant.getGrantNumber() + "','" + grant.getGrantIssueDate() + "','" + grant.getPermit().getPermitNumber() + "','" + grant.getLot().getLotNumber() + "','" + grant.getClient().getNIC() + "','" + grant.getNominatedSuccessor().getNIC_S() + "')";
                    int returnGrantInsert = DBHandler.setData(conn, sql);
                    if (returnGrantInsert > 0) {
                        System.out.println("grant aded");

                    } else {
                        returnStatue = false;
                        conn.rollback();
                    }
                } else {
                    returnStatue = false;
                    conn.rollback();
                }

                if (returnStatue) {
                    conn.commit();
                }

            } catch (SQLException sqlExeption) {
                returnStatue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return returnStatue;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static boolean changeNominatedSuccessorGrant(Grant grant, NominatedSuccessor newSuccessor) throws ClassNotFoundException, SQLException {
        try {
            readWriteLock.writeLock().lock();

            boolean returnStatue = true;
            Connection conn = DBConnection.getDBConnection().getConnection();
            conn.setAutoCommit(false);
            try {
                boolean addNewNominateSuccessor = NominatedSuccessorController.addNewNominateSuccessor(newSuccessor);
                if (addNewNominateSuccessor) {
                    System.err.println("new successor added");
                    String sql = "Update grant1 set NIC_Successor ='" + newSuccessor.getNIC_S() + "' where grantNumber = '" + grant.getGrantNumber() + "'";
                    int returnPermitDelete = DBHandler.setData(conn, sql);
                    if (returnPermitDelete > 0) {
                        System.out.println("grant update");
                        NominatedSuccessor nominatedSuccessor = grant.getNominatedSuccessor();
                        Permit searchPermitByNominatedSuccessor = PermitController.searchPermitByNominatedSuccessor(nominatedSuccessor.getNIC_S());
                        if (searchPermitByNominatedSuccessor == null) {
                            boolean DeleteNominatedSuccessor = NominatedSuccessorController.DeleteNominatedSuccessor(nominatedSuccessor.getNIC_S());
                            if (DeleteNominatedSuccessor) {
                                System.out.println("old successsor deleted");

                            } else {
                                returnStatue = false;
                                conn.rollback();
                            }
                        }
                    } else {
                        returnStatue = false;
                        conn.rollback();
                    }
                } else {
                    returnStatue = false;
                    conn.rollback();
                }

                if (returnStatue) {
                    conn.commit();
                }

            } catch (SQLException sqlExeption) {
                returnStatue = false;
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
            return returnStatue;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
