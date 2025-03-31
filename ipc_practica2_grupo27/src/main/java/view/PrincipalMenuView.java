package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PrincipalMenuView {
    private JPanel mainPanel;
    private JPanel taskManagerPanel;
    private JPanel listManagerPanel;
    private JButton taskManagerButton;
    private JList listExampleMenu;
    private JButton gestionarListasButton;
    private JList taskListInfo;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    public PrincipalMenuView() {

        initColors();
        taskManagerPanel.setBorder(new EmptyBorder(50, 50, 50, 10));
        listManagerPanel.setBorder(new EmptyBorder(50, 10, 50, 50));
    }

    public void initColors() {
        mainPanel.setBackground(BACKGROUND_COLOR);
        taskManagerPanel.setBackground(BACKGROUND_COLOR);
        listManagerPanel.setBackground(BACKGROUND_COLOR);
        taskManagerButton.setBackground(ELEMENTS_COLOR);
        taskManagerButton.setForeground(FOREGROUND_COLOR);
        gestionarListasButton.setBackground(ELEMENTS_COLOR);
        gestionarListasButton.setForeground(FOREGROUND_COLOR);
        listExampleMenu.setBackground(ELEMENTS_COLOR);
        listExampleMenu.setForeground(FOREGROUND_COLOR);
        taskListInfo.setBackground(ELEMENTS_COLOR);
        taskListInfo.setForeground(FOREGROUND_COLOR);

        //TODO: INIT COMPONENTS meter esto y llamar a init colors desde ahi
        listExampleMenu.setBorder(new EmptyBorder(5, 10, 5, 10));
        taskListInfo.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new PrincipalMenuView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
