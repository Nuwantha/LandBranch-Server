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
import las.controller.ClientController;
import las.models.Client;

/**
 *
 * @author DinsuG
 */
public class ClientControllerImple extends UnicastRemoteObject implements ClientController{
    
    private final las.controllers.ClientController clientController;
    
   public ClientControllerImple() throws  RemoteException{
        super();
        clientController=new las.controllers.ClientController();
    }

    @Override
    public boolean addNewClient(Client client) throws RemoteException, SQLException, ClassNotFoundException {
       return clientController.addNewClient(client);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client searchClient(String NIC) throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.searchClient(NIC); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Client> getSimmilarNICs(String nicPart) throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.getSimmilarNICs(nicPart);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Client> getNoPermitOwners(String nicPart) throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.getNoPermitOwners(nicPart); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateClient(Client client) throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.updateClient(client);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Client> getAllClients() throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.getAllClients();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client getLastAddedClient() throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.getLastAddedClient();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getnextOwnershiPositionPermit(String permitNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.getnextOwnershiPositionPermit(permitNumber);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getnextOwnershiPositionGrant(String grantNumber) throws RemoteException, SQLException, ClassNotFoundException {
        return clientController.getnextOwnershiPositionPermit(grantNumber);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Client> getSimilarNames(String namePart) throws RemoteException, ClassNotFoundException, SQLException {
        return  clientController.getSimilarNames(namePart);//To change body of generated methods, choose Tools | Templates.
    }

   
    
}
