package gui.table;
import app.AppCore;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

@Data
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    public AppCore appCore;
    public JTable jTable;
    public JScrollPane jsp;
    public JPanel bottomStatus;
    public JButton Jbutton;
    public JTextArea JtextArea;

    MainFrame() {

    }

    public static MainFrame getInstance() {
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    public void initialise() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Jbutton = new JButton("Button ");
        Jbutton.setPreferredSize(new Dimension(50, 120));
        JtextArea = new JTextArea(3, 2);
        this.add(JtextArea, BorderLayout.NORTH);
        this.add(Jbutton, BorderLayout.SOUTH);
        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        jTable.setFillsViewportHeight(true);
        this.add(new JScrollPane(jTable), BorderLayout.CENTER);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
        public void upit()
        {
            Jbutton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String kod = JtextArea.getText();
                    AppCore appCore = new AppCore();
                    MainFrame mainFrame=MainFrame.getInstance();
                    mainFrame.setAppCore(appCore);
                    mainFrame.getAppCore().readDataFromTable(kod);
                    mainFrame.getAppCore().loadResource();

                }
            });
    }
    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
    }
    @Override
    public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
        }

        else{
            jTable.setModel((TableModel) notification.getData());
        }

    }
    
    //GETTERI I SETERI

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public AppCore getAppCore() {
        return appCore;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public JScrollPane getJsp() {
        return jsp;
    }

    public void setJsp(JScrollPane jsp) {
        this.jsp = jsp;
    }

    public JPanel getBottomStatus() {
        return bottomStatus;
    }

    public void setBottomStatus(JPanel bottomStatus) {
        this.bottomStatus = bottomStatus;
    }

    public JButton getJbutton() {
        return Jbutton;
    }

    public void setJbutton(JButton jbutton) {
        Jbutton = jbutton;
    }


    public JTextArea getJtextArea() {
        return JtextArea;
    }

    public void setJtextArea(JTextArea jtextArea) {
        JtextArea = jtextArea;
    }
}
