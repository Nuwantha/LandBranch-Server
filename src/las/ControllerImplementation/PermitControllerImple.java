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
import las.controllers.PermitController;
import las.models.NominatedSuccessor;
import las.models.Permit;

/**
 *
 * @author DinsuG
 */
public class PermitControllerImple extends UnicastRemoteObject implements las.controller.PermitController {

    private final PermitController permitController;

    public PermitControllerImple() throws RemoteException {
        super();
        this.permitController = new PermitController();
    }

    @Override
    public boolean addNewPermit(Permit permit) throws RemoteException, SQLException, ClassNotFoundException {
        return permitController.addNewPermit(permit);
    }

    @Override
    public boolean cancelPermit(Permit permit) throws RemoteException, SQLException, ClassNotFoundException {
        return permitController.cancelPermit(permit);
    }

    @Override
    public Permit searchPermit(String permitNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return permitController.searchPermit(permitNumber);
    }

    @Override
    public int getPermitCountOfDivision(String divisionNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return permitController.getPermitCountOfDivision(divisionNumber);
    }

    @Override
    public ArrayList<Permit> getSimilarPermitNumbers(String permitNumberPart) throws RemoteException, SQLException, ClassNotFoundException {
        return permitController.getSimilarPermitNumbers(permitNumberPart);
    }

    @Override
    public ArrayList<Permit> getAllPermit() throws RemoteException, SQLException, ClassNotFoundException {
        return permitController.getAllPermit();
    }


    @Override
    public Permit searchPermitByClient(String NIC) throws RemoteException, ClassNotFoundException, SQLException {
        return  permitController.searchPermit(NIC);
    }

    @Override
    public ArrayList<Permit> getSimilarPermitsByName(String namepart) throws RemoteException, ClassNotFoundException, SQLException {
        return permitController.getSimilarPermitsByName(namepart);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Permit> getSimilarPermitsByNIC(String nicpart) throws RemoteException, ClassNotFoundException, SQLException {
        return permitController.getSimilarPermitsByNIC(nicpart);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeNominatedSuccessorPermit(Permit permit, NominatedSuccessor newSuccessor) throws RemoteException, ClassNotFoundException, SQLException {
        return permitController.changeNominatedSuccessorPermit(permit, newSuccessor);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Permit searchPermitByNominatedSuccessor(String NIC) throws RemoteException, ClassNotFoundException, SQLException {
        return permitController.searchPermitByNominatedSuccessor(NIC);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Permit> getGrantHaventPermit(String permitNumberPart) throws RemoteException, ClassNotFoundException, SQLException {
        return permitController.getGrantHaventPermit(permitNumberPart);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changePermitOwnership(Permit permit) throws ClassNotFoundException, SQLException, RemoteException {
        return permitController.cancelPermit(permit);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addGramaNiladaryCertificateToPermit(Permit permit) throws RemoteException, ClassNotFoundException, SQLException {
        return permitController.addGramaNiladaryCertificateToPermit(permit);//To change body of generated methods, choose Tools | Templates.
    }

}
