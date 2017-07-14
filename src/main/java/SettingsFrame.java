import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Class that initialize Setting popup window UI
 * magic is happening here
 */

public class SettingsFrame extends JFrame {
    public SettingsFrame() {
        setSize(new Dimension(300, 160));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) resolution.getWidth() - 600, 50);
        setLayout(new FlowLayout());
        JLabel minutes1 = new JLabel("minutes");
        JLabel minutes = new JLabel("minutes");

        //Create a number format disallow wrong input
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(999);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(false);


        JPanel workTimePanel = new JPanel();
        workTimePanel.setLayout(new FlowLayout());
        JLabel workLabel = new JLabel("Work time: ");
        JFormattedTextField workTimeText = new JFormattedTextField(formatter);
        workTimeText.setValue(new Integer(SettingsPreferences.getWorkTime()));
        workTimeText.setColumns(10);
        workTimePanel.add(workLabel);
        workTimePanel.add(workTimeText);
        workTimePanel.add(minutes1);
        workTimePanel.setVisible(true);

        JPanel breakTimePanel = new JPanel();
        breakTimePanel.setLayout(new FlowLayout());
        JLabel breakLabel = new JLabel("Break time: ");
        JFormattedTextField breakTimeText = new JFormattedTextField(formatter);
        breakTimeText.setText(SettingsPreferences.getBreakTime());
        breakTimeText.setColumns(10);
        breakTimePanel.add(breakLabel);
        breakTimePanel.add(breakTimeText);
        breakTimePanel.add(minutes);
        breakTimePanel.setVisible(true);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        JButton submit = new JButton();
        submit.setText("Save");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsPreferences.savePreferences(workTimeText.getText(), breakTimeText.getText());
                Main.flag = false;
                dispose();
            }
        });

        JButton defaults = new JButton();
        defaults.setText("Defaults");
        defaults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsPreferences.restoreDefaults();
                workTimeText.setText(SettingsPreferences.getWorkTime());
                breakTimeText.setText(SettingsPreferences.getBreakTime());

            }
        });
        buttons.add(submit);
        buttons.add(defaults);
        buttons.setVisible(true);

        add(workTimePanel);
        add(breakTimePanel);
        add(buttons);
        setVisible(true);
    }
}
