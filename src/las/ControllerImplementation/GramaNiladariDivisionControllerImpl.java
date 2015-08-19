/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.ControllerImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import las.controllers.GramaNiladariDivisionController;
import las.models.GramaNiladariDivision;
import las.models.Permit;

/**
 *
 * @author DinsuG
 */
public class GramaNiladariDivisionControllerImpl extends UnicastRemoteObject implements las.controller.GramaNiladariDivisionController {

    private final GramaNiladariDivisionController gnd;

    public GramaNiladariDivisionControllerImpl() throws RemoteException {
        this.gnd = new GramaNiladariDivisionController();
    }

    @Override
    public boolean addNewGND(GramaNiladariDivision GND) throws RemoteException, SQLException, ClassNotFoundException {
        return gnd.addNewGND(GND);
    }

    @Override
    public GramaNiladariDivision searchGND(String DivisionNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return gnd.searchGND(DivisionNumber);
    }

    @Override
    public ArrayList<GramaNiladariDivision> getSimmilarGndDivisionNumbers(String divisionNumberPart) throws RemoteException, SQLException, ClassNotFoundException {
        return gnd.getSimmilarGndDivisionNumbers(divisionNumberPart);
    }

    @Override
    public boolean updateGND(GramaNiladariDivision GND) throws RemoteException, SQLException, ClassNotFoundException {
        return gnd.updateGND(GND);
    }

    @Override
    public ArrayList<GramaNiladariDivision> getAllGNDDetail() throws ClassNotFoundException, SQLException, ClassNotFoundException {
        return gnd.getAllGNDDetail();
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GramaNiladariDivision searchGNDByOfficer(String officer) throws RemoteException, ClassNotFoundException, SQLException {
        return gnd.searchGNDByOfficer(officer);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GramaNiladariDivision> getLandCount() throws RemoteException, ClassNotFoundException, SQLException {
        return gnd.getLandCount(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Permit> getPermitsToCertify(String divisionnumber) throws SQLException, ClassNotFoundException {
        return gnd.getPermitsToCertify(divisionnumber);

    }

}
