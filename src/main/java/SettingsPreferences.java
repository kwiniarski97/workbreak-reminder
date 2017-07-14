import java.util.prefs.Preferences;

/**
 * Holds user preferences using Preferences class.
 */

public class SettingsPreferences {

    private static final String WORK_TIME_KEY = "workTime";
    private static final String BREAK_TIME_KEY = "breakTime";
    static Preferences prefs = Preferences.userNodeForPackage(Main.class);

    /**
     * Overrides a settings in preferences
     *
     * @param workTime  work time in minutes
     * @param breakTime break time in minutes
     */
    public static void savePreferences(String workTime, String breakTime) {
        prefs.put(WORK_TIME_KEY, workTime);
        prefs.put(BREAK_TIME_KEY, breakTime);
    }

    /**
     * Restore default settings 55 minutes of work time and 5 minutes of break
     */
    public static void restoreDefaults() {
        prefs.put(WORK_TIME_KEY, "55");
        prefs.put(BREAK_TIME_KEY, "5");
    }

    /**
     * Work time getter
     *
     * @return current work time value
     */
    public static String getWorkTime() {
        return prefs.get(WORK_TIME_KEY, "");
    }

    /**
     * Break time getter
     *
     * @return current break time value
     */
    public static String getBreakTime() {
        return prefs.get(BREAK_TIME_KEY, "");
    }


}
