/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.ControllerImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import las.controller.BackUP;
import las.controller.ClientController;
import las.controller.GramaNiladariDivisionController;
import las.controller.GrantController;
import las.controller.LandController;
import las.controller.LotController;
import las.controller.NominatedSuccessorController;
import las.controller.PermitController;
import las.controller.RemoteFactory;
import las.controller.UserController;

/**
 *
 * @author DinsuG
 */
public class RemoteFactoryImple extends UnicastRemoteObject implements RemoteFactory {

    public RemoteFactoryImple() throws RemoteException {
        super();
    }

    @Override
    public ClientController getClientController() throws RemoteException, SQLException, ClassNotFoundException {
        return new ClientControllerImple();
    }

    @Override
    public GramaNiladariDivisionController getGramaNiladaryDivisionController() throws RemoteException, SQLException, ClassNotFoundException {
        return new GramaNiladariDivisionControllerImpl();
    }

    @Override
    public GrantController getGrantController() throws RemoteException, SQLException, ClassNotFoundException {
        return new GrantControllerImple();
    }

    @Override
    public LandController getLandController() throws RemoteException, SQLException, ClassNotFoundException {
        return new LandControllerImple();
    }

    @Override
    public LotController getLotController() throws RemoteException, SQLException, ClassNotFoundException {
        return new LotControllerImple();
    }

    @Override
    public NominatedSuccessorController getNominatedSuccessorController() throws RemoteException, SQLException, ClassNotFoundException {
        return new NominatedSuccessorControllerImple();
    }

    @Override
    public PermitController getPermitController() throws RemoteException, SQLException, ClassNotFoundException {
        return new PermitControllerImple();
    }

    @Override
    public UserController getUeController() throws RemoteException, SQLException, ClassNotFoundException {
        return new UserControllerImple();
    }

    @Override
    public BackUP getBackUP() throws RemoteException, SQLException, InterruptedException, ClassNotFoundException {
        return  new BackUpControllerImpl();//To change body of generated methods, choose Tools | Templates.
    }
}
