package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import model.PRIORITY;

public class TaskManagerFilterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> priorityFilterComboBox;
    private JSlider percentageFilterSlider;
    private JComboBox categoryFilterComboBox;
    private JComboBox subtaskFilterComboBox;
    private JPanel dateCreationPanel;
    private JPanel deadLinePanel;
    private JPanel sinceDateCreationPanel;
    private JPanel sinceDeadLinePanel;
    private JPanel fromPercentagePanel;
    private JRadioButton untilPercentageRadioButton;
    private JRadioButton fromPercentageRadioButton;
    private JRadioButton untilDateCreationRadioButton;
    private JRadioButton sinceDateCreationRadioButton;
    private JRadioButton untilDeadLineRadioButton;
    private JRadioButton sinceDeadLineRadioButton;
    private JRadioButton noPercentageRadioButton;
    private JToggleButton dateCreationToggleButton;
    private JToggleButton deadLineToggleButton;
    private JToggleButton percentageToggleButton;

    public static final String SELECT_CATEGORY_PLACEHOLDER = "Seleccionar opción";
    public static final String SELECT_NOT_SUBTASK_PLACEHOLDER = "No es subtarea";

    public TaskManagerFilterDialog() {
        initComponents();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TaskManagerFilterDialog dialog = new TaskManagerFilterDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void initComponents() {
        JDateChooser dateCreationChooser = new JDateChooser();
        JDateChooser deadLineChooser = new JDateChooser();

        dateCreationChooser.setDateFormatString("dd/MM/yyyy");
        deadLineChooser.setDateFormatString("dd/MM/yyyy");

        dateCreationChooser.setDate(new Date());
        deadLineChooser.setDate(new Date());

        dateCreationPanel.setLayout(new BorderLayout());
        dateCreationPanel.add(dateCreationChooser, BorderLayout.CENTER);

        deadLinePanel.setLayout(new BorderLayout());
        deadLinePanel.add(deadLineChooser, BorderLayout.CENTER);
    }
}
