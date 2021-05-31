package app;

import gui.table.MainFrame;

public class Main {

    public static void main(String[] args) {

        AppCore appCore = new AppCore();
        MainFrame mainFrame=MainFrame.getInstance();
        mainFrame.setAppCore(appCore);



       /*  mainFrame.getAppCore().readDataFromTable("JOBS");
        mainFrame.getAppCore().loadResource();






            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //EVO GA UPIT

            mainFrame.getAppCore().readDataFromTable("EMPLOYEES");

*/
        mainFrame.upit();
    }

    }

