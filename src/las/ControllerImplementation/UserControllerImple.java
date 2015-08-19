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
import las.controllers.UserController;
import las.models.User;

/**
 *
 * @author DinsuG
 */
public class UserControllerImple extends UnicastRemoteObject implements las.controller.UserController {

    private final UserController userController;

    public UserControllerImple() throws RemoteException {
        super();
        this.userController = new UserController();
    }

    @Override
    public int addNewUser(User user) throws RemoteException, SQLException, ClassNotFoundException {
        return userController.addNewUser(user);
    }

    @Override
    public int updateUser(User user) throws RemoteException, SQLException, ClassNotFoundException {
        return userController.updateUser(user);
    }

    @Override
    public int updatePassword(User user) throws RemoteException, SQLException, ClassNotFoundException {
        return userController.updatePassword(user);
    }

    @Override
    public ArrayList<User> getAllUsers() throws RemoteException, SQLException, ClassNotFoundException {
        return userController.getAllUsers();
    }

    @Override
    public User searchUser(String name) throws RemoteException, SQLException, ClassNotFoundException {
        return userController.searchUser(name);
    }

    @Override
    public boolean matchPassword(String name, String entertext) throws RemoteException, SQLException, ClassNotFoundException {
        return userController.matchPassword(name, entertext);
    }

    @Override
    public ArrayList<User> getAllSimilarUsers(String typed) throws RemoteException, ClassNotFoundException, SQLException {
        return userController.getAllSimilarUsers(typed); //To change body of generated methods, choose Tools | Templates.
    }
}
