/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package las.controllers;

import java.io.IOException;

/**
 *
 * @author User
 */
public class BackUp {

    public static int  writeBackup() throws IOException,InterruptedException {
        Runtime runTime = Runtime.getRuntime();
        Process exec = runTime.exec("mysqldump LandBranch -h localhost -u root -pGimhani@1120 -r database.sql");
        return exec.waitFor();
    }

    public static int restoreBackup() throws IOException, InterruptedException {
        String[] executeCmd = new String[]{"mysql", "--user=root", "--password=Gimhani@1120","LandBranch", "-e", " source  E:\\Education\\Semester 03\\Object Oriented Software Development\\Project Final\\LandAdministrationSystem2\\LandAdministrationSystem\\database.sql"};
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int waitFor = runtimeProcess.waitFor();
        return  waitFor;
    }

   /*
    public static int writeBackup(String path) throws IOException, InterruptedException {
        Runtime runTime = Runtime.getRuntime();
        Process runtimeProcess = runTime.exec("mysqldump DrivingSchool -h localhost -u root -p0213 -r " + path + ".txt");
        int res = runtimeProcess.waitFor();
        return res;
        
        
    }

    public static int restoreBackup(String path) throws IOException, InterruptedException {
        String[] executeCmd = new String[]{"mysql", "DrivingSchool", "--user=root", "--password=0213", "-e", " source " + path};
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int res = runtimeProcess.waitFor();
        return res;
    }

    */
}
