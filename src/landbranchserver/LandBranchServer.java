/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package landbranchserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import las.ControllerImplementation.RemoteFactoryImple;

/**
 *
 * @author DinsuG
 */
public class LandBranchServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry myRegistry = LocateRegistry.createRegistry(344);
            myRegistry.rebind("LandBranchServer", new RemoteFactoryImple());
            System.out.println("LandBranch server started...");

        } catch (ExportException e) {
            System.out.println("Server already running");
        }

    }
}
