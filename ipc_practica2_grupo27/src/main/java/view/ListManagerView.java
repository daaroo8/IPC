package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ListManagerView {
    private JPanel mainPanel;
    private JPanel leftListManagerPanel;
    private JPanel rightListManagerPanel;
    private JTextField searchTextField;
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
    private JPanel listSearchPanel;
    private JScrollPane pendingScrollpane;
    private JScrollPane completedScrollpane;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    private static final Font FONT = new Font("Helvetica", Font.BOLD, 14);

    public ListManagerView() {
        initColors();

        mainPanel.setLayout(new GridLayout(1, 2));

    }

    public void initColors(){
        leftListManagerPanel.setBorder(new EmptyBorder(20, 20, 20, 10));
        rightListManagerPanel.setBorder(new EmptyBorder(20, 10, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);
        leftListManagerPanel.setBackground(BACKGROUND_COLOR);
        rightListManagerPanel.setBackground(BACKGROUND_COLOR);
        labelPendingPanel.setBackground(BACKGROUND_COLOR);
        labelCompletedPanel.setBackground(BACKGROUND_COLOR);
        pendingTasksPanel.setBackground(BACKGROUND_COLOR);
        completedTasksPanel.setBackground(BACKGROUND_COLOR);
        listSearchPanel.setBackground(BACKGROUND_COLOR);
        pendingScrollpane.setBackground(ELEMENTS_COLOR);
        completedScrollpane.setBackground(ELEMENTS_COLOR);
        listOfLists.setBackground(ELEMENTS_COLOR);
        listOfLists.setForeground(FOREGROUND_COLOR);
        searchTextField.setBackground(ELEMENTS_COLOR);
        searchTextField.setForeground(FOREGROUND_COLOR);
        addListButton.setBackground(ELEMENTS_COLOR);
        addListButton.setForeground(FOREGROUND_COLOR);

        //TODO: INIT COMPONENTS meter esto y llamar a init colors desde ahi
        searchTextField.setBorder(new EmptyBorder(5, 10, 5, 10));
        listOfLists.setBorder(new EmptyBorder(5, 10, 5, 10));

        listOfLists.setFont(FONT);
        searchTextField.setFont(FONT);
        addListButton.setFont(FONT);
        pendingLabel.setFont(FONT);
        completedLabel.setFont(FONT);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new ListManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 600));
        frame.pack();
        frame.setSize(frame.getWidth(), 600); // Cambiar '600' al alto deseado
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
