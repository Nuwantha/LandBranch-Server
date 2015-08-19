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
import las.controllers.GrantController;
import las.models.Grant;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
public class GrantControllerImple extends UnicastRemoteObject implements las.controller.GrantController {

    private final GrantController grantController;

    public GrantControllerImple() throws RemoteException {
        super();
        this.grantController = new GrantController();
    }

    @Override
    public Grant searchGrant(String grantNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return grantController.searchGrant(grantNumber);
    }

    @Override
    public boolean addNewGrant(Grant grant) throws RemoteException, SQLException, ClassNotFoundException {
        return grantController.addNewGrant(grant);

    }

    @Override
    public int getGrantCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return grantController.getGrantCountOfDivision(divisionNumber);
    }

    @Override
    public ArrayList<Grant> getSimilarGrantNumbers(String grantNumberPart) throws RemoteException, SQLException, ClassNotFoundException {
        return grantController.getSimilarGrantNumbers(grantNumberPart);
    }

    @Override
    public boolean changeGrantOwnership(Grant grant) throws RemoteException, SQLException, ClassNotFoundException {
        return grantController.changeGrantOwnership(grant);

    }

    @Override
    public Grant searchGrantByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException {
        return  grantController.searchGrantByClient(NIC);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Grant> getSimilarPermitNumberGrants(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException {
       return grantController.getSimilarPermitNumberGrants(permitNumberPart);
    }

    @Override
    public ArrayList<Grant> getSimilarGrantsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException {
        return grantController.getSimilarGrantsByName(namepart);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Grant> getSimilarPermitsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException {
        return grantController.getSimilarPermitsByNIC(nicpart);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeNominatedSuccessorGrant(Grant grant, NominatedSuccessor newSuccessor) throws RemoteException, ClassNotFoundException, SQLException {
        return grantController.changeNominatedSuccessorGrant(grant, newSuccessor);
    }
}
