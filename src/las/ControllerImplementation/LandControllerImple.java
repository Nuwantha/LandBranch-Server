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
import las.controllers.LandController;
import las.models.Land;

/**
 *
 * @author DinsuG
 */
public class LandControllerImple extends UnicastRemoteObject implements las.controller.LandController {

    private final LandController landController;

   public LandControllerImple() throws RemoteException {
        super();
        this.landController = new LandController();
    }

    @Override
    public boolean addNewLand(Land land) throws RemoteException, SQLException, ClassNotFoundException {
        return landController.addNewLand(land);
    }

    @Override
    public Land getLastAddedLand() throws RemoteException, SQLException, ClassNotFoundException {
        return landController.getLastAddedLand();
    }

    @Override
    public Land searchLand(String PlanNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return landController.searchLand(PlanNumber);
    }

    @Override
    public Land getAvailableLotOfLand(String PlanNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return landController.getAvailableLotOfLand(PlanNumber);
    }

    @Override
    public ArrayList<Land> getAllLandDetail() throws RemoteException, SQLException, ClassNotFoundException {
        return landController.getAllLandDetail();
    }

    @Override
    public ArrayList<Land> getSimmilarPlanNumbers(String planNumberPart) throws RemoteException, SQLException, ClassNotFoundException {
        return landController.getSimmilarPlanNumbers(planNumberPart);
    }

    @Override
    public ArrayList<Land> getLandsOfDivision(String DivisionNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return landController.getLandsOfDivision(DivisionNumber);
    }

    @Override
    public boolean updateLand(Land land) throws RemoteException, SQLException, ClassNotFoundException {
        return landController.updateLand(land);
    }
}
