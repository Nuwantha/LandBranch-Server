/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.ControllerImplementation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import las.controller.BackUP;
import las.controllers.BackUp;

/**
 *
 * @author DinsuG
 */
public class BackUpControllerImpl extends UnicastRemoteObject implements BackUP{
    
    private final BackUp backUp;
    public  BackUpControllerImpl()throws RemoteException{
        this.backUp=new BackUp();
    }

    @Override
    public int writeBackup() throws IOException, RemoteException, SQLException ,InterruptedException{
        return backUp.writeBackup();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int restoreBackup() throws RemoteException, IOException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
