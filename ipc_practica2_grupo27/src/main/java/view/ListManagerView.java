package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ListManagerView {
    private JPanel mainPanel;
    private JPanel leftListManagerPanel;
    private JPanel rightListManagerPanel;
    private JTextField buscarTextField;
    private JButton addListButton;
    private JList listOfLists;
    private JPanel pendingTasksPanel;
    private JPanel completedTasksPanel;
    private JLabel pendingLabel;
    private JLabel completedLabel;
    private JLabel numberPendingTaskLabel;
    private JLabel numberCompletedTaskLabel;
    private JPanel labelPendingPanel;
    private JPanel labelCompletedPanel;

    public ListManagerView() {
        mainPanel.setLayout(new GridLayout(1, 2));

        leftListManagerPanel.setBorder(new EmptyBorder(20, 20, 20, 10));
        rightListManagerPanel.setBorder(new EmptyBorder(20, 10, 20, 20));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new ListManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(frame.getWidth(), 600); // Cambiar '600' al alto deseado
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
