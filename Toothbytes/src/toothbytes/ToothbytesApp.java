package toothbytes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import toothbytes.database.DBAccess;
import toothbytes.model.Patient;
import toothbytes.ui.MainScreen;
import toothbytes.ui.components.AppointmentsWindow;
import toothbytes.ui.components.PaymentsWindow;
import toothbytes.ui.components.RecordsWindow;

/**
 * <h1>ToothbytesApp</h1>
 * The {@code ToothbytesApp} program is the main class that executes the whole 
 * system of Toothbytes.
 */
public class ToothbytesApp {

    public static ArrayList<Patient> patientList;
    
//    private static Font ttfBase = null;
//    private static Font fontChamp = null;
//    private static InputStream myStream = null;
//    private static final String fontPath = "src/fonts/MonaBella Demo.ttf";

    /**
     * It connects to database, initializes the list of patients, instantiates 
     * the User Interfaces of the program Toothbytes and initializes them.
     */
    public static void main(String[] args) throws FileNotFoundException, FontFormatException, IOException {
        try {

            DBAccess.connectDB();
            patientList = DBAccess.initPatientList();

//            LoginWindow lw = new LoginWindow();
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            SwingUtilities.updateComponentTreeUI(lw);
//            lw.init();
//            DentalChart dc = new DentalChart();
//            dc.init();
            
            DBAccess.connectDB(); //connect to database
            patientList = DBAccess.initPatientList(); //initialize list of patients

            RecordsWindow rWin = new RecordsWindow(patientList);
            AppointmentsWindow aWin = new AppointmentsWindow();
            PaymentsWindow pWin = new PaymentsWindow();
            
//            myStream = new BufferedInputStream(new FileInputStream(fontPath));
//            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
//            fontChamp = ttfBase.deriveFont(Font.PLAIN, 16);   
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Walkway SemiBold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Walkway Bold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Walkway Oblique.ttf")));

            MainScreen ui = new MainScreen(rWin, aWin, pWin);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(ui);
            ui.init();
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException |
                SQLException ex) {
            Logger.getLogger(ToothbytesApp.class.getName()).log(Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            System.err.println("Font not loaded.");
        }
    }
}
