/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.ControllerImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import las.controllers.NominatedSuccessorController;
import las.models.NominatedSuccessor;

/**
 *
 * @author DinsuG
 */
public class NominatedSuccessorControllerImple extends UnicastRemoteObject implements las.controller.NominatedSuccessorController {

    private final NominatedSuccessorController nominatedSuccessorController;

    public NominatedSuccessorControllerImple() throws RemoteException {
        super();
        this.nominatedSuccessorController = new NominatedSuccessorController();
    }

    @Override
    public NominatedSuccessor searchNominateSuccessor(String NICS) throws RemoteException, SQLException, ClassNotFoundException {
        return nominatedSuccessorController.searchNominateSuccessor(NICS);
    }

    @Override
    public boolean addNewNominateSuccessor(NominatedSuccessor NOS) throws RemoteException, SQLException, ClassNotFoundException {
        return nominatedSuccessorController.addNewNominateSuccessor(NOS);
    }

    @Override
    public boolean updateNiminateSuccessor(NominatedSuccessor nominateSuccessor) throws RemoteException, SQLException, ClassNotFoundException {
        return nominatedSuccessorController.updateNiminateSuccessor(nominateSuccessor);
    }

    @Override
    public boolean DeleteNominatedSuccessor(String nic) throws RemoteException, ClassNotFoundException, SQLException {
        return nominatedSuccessorController.DeleteNominatedSuccessor(nic);//To change body of generated methods, choose Tools | Templates.
    }
}
