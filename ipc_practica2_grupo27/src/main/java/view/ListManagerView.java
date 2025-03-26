package view;

import javax.swing.*;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new ListManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
