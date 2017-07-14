import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Create a instance of Tray Icon Initialize a popupmenu and adds actions to it. Give a possibility to create a popup messages
 */

public class TrayIconClass extends SettingsPreferences {
    private TrayIcon trayIcon;
    private PopupMenu popupMenu;

    /**
     * Create a tray icon that contains all functionality of a  program.
     * @param toolTip tool tip text
     */
    public TrayIconClass(String toolTip) {
        SystemTray tray = SystemTray.getSystemTray();
        //icon loading
        try {
            BufferedImage icon = ImageIO.read(getClass().getResource("img/icon.png"));
            this.trayIcon = new TrayIcon(icon, toolTip);
        } catch (IOException ex) {
            System.err.println("Image not found");
        }
//        Image image = Toolkit.getDefaultToolkit().createImage(icon);
        menuEntries();
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(toolTip); //text when you hover over icon in tray
        trayIcon.setPopupMenu(popupMenu);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.print("Cannot create tray icon | AWTException");
        }

    }

    /**
     * Creates a memu using PopUpmenu
     */
    private void menuEntries() {
        this.popupMenu = new PopupMenu();
        //adds a exit button
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(e -> {
            try {
                System.exit(0);
            } catch (Exception ex) {
            }
            System.exit(0);
        });
        popupMenu.add(exit);

        //adds a settings button
        MenuItem settings = new MenuItem("Settings");
        settings.addActionListener(e -> EventQueue.invokeLater(SettingsFrame::new));
        popupMenu.add(settings);
        //adds a about button
        MenuItem about = new MenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("http://karoldevblog.me"));
                } catch (URISyntaxException | IOException ex) {
                    System.err.println("Something is wrong about URL");
                }
            }
        });
        popupMenu.add(about);

    }

    /**
     * Display a Windows/Linux notification
     *
     * @param title   Title of that notification
     * @param message message to be shown
     */
    public void displayMessage(String title, String message) {
        this.trayIcon.displayMessage(title, message, MessageType.INFO);
    }

    /**
     * Display a Windows/Linux notification
     *
     * @param message message to be shown
     */
    public void displayMessage(String message) {
        this.trayIcon.displayMessage("Workbreak reminder", message, MessageType.INFO);
    }

}