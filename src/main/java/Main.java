import java.awt.*;

/**
 * Main class handle main loop and startup.
 */
public class Main {
    static boolean flag = true;
    static TrayIconClass trayIconClass = new TrayIconClass("Work time reminder");


    public static void main(String args[]) {
        if (!SettingsPreferences.prefs.isUserNode()) {
            SettingsPreferences.restoreDefaults();
        }
        run();
    }

    /**
     * Handles sleep and running a proper function at proper time
     * loop till change of settings or till disposed
     */
    public static void run() {
        while (flag) {
            try {
                Thread.sleep(Integer.parseInt(SettingsPreferences.getWorkTime()) * 60000); //work time
                if (SystemTray.isSupported()) {
                    if (!flag) {
                        flag = true;
                        continue;
                    }
                    trayIconClass.displayMessage("Take 5 minutes of a break");
                    Thread.sleep(Integer.parseInt(SettingsPreferences.getBreakTime()) * 60000);  //break time
                    trayIconClass.displayMessage("End of break.");

                }
            } catch (InterruptedException ex) {
                System.err.print("Error");
            }
        }
    }
}
