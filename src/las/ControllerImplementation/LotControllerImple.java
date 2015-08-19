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
import las.controllers.LotController;
import las.models.Lot;

/**
 *
 * @author DinsuG
 */
public class LotControllerImple extends UnicastRemoteObject implements las.controller.LotController {

    private final LotController lotController;

    public LotControllerImple() throws RemoteException {
        super();
        this.lotController = new LotController();
    }

    @Override
    public boolean addNewLot(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.addNewLot(lot);
    }

    @Override
    public Lot searchLot(String lotNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.searchLot(lotNumber);
    }

    @Override
    public ArrayList<Lot> searchLotOfLand(String planNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.searchLotOfLand(planNumber);
    }

    @Override
    public ArrayList<Lot> getAvailableLotOfLand(String planNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.getAvailableLotOfLand(planNumber);
    }

    @Override
    public boolean updateLot(Lot lot) throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.updateLot(lot);
    }

    @Override
    public Lot getLastAddedLot() throws RemoteException, SQLException, ClassNotFoundException {
        return lotController.getLastAddedLot();
    }

}
