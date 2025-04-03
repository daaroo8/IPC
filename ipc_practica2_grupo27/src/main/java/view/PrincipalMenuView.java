package view;

import main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalMenuView extends JFrame {
    private JPanel mainPanel;
    private JPanel taskManagerPanel;
    private JPanel listManagerPanel;
    private JList<String> listExampleMenu;
    private JList<String> taskListInfo;
    private JButton taskManagerButton;
    private JButton listManagerButton;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    private static final Font FONT = new Font("Helvetica", Font.BOLD, 18);
    private static final Font FONT_ELEMENTS = new Font("Helvetica", Font.BOLD, 16);

    public PrincipalMenuView() {
        initComponents();
        taskManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getViewManager().showTaskManagerView();
            }
        });

        listManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getViewManager().showListManagerView();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void initComponents() {

        if (anyComponentIsNull())
            throw new RuntimeException("No se puede inicializar el componente");

        taskManagerPanel.setBorder(new EmptyBorder(50, 50, 50, 10));
        listManagerPanel.setBorder(new EmptyBorder(50, 10, 50, 50));
        listExampleMenu.setBorder(new EmptyBorder(5, 10, 5, 10));
        taskListInfo.setBorder(new EmptyBorder(5, 10, 5, 10));

        taskManagerButton.setFont(FONT);
        listManagerButton.setFont(FONT);
        taskListInfo.setFont(FONT_ELEMENTS);
        listExampleMenu.setFont(FONT_ELEMENTS);

        initColors();
    }


    public void initColors() {
        mainPanel.setBackground(BACKGROUND_COLOR);
        taskManagerPanel.setBackground(BACKGROUND_COLOR);
        listManagerPanel.setBackground(BACKGROUND_COLOR);
        taskManagerButton.setBackground(ELEMENTS_COLOR);
        taskManagerButton.setForeground(FOREGROUND_COLOR);
        listManagerButton.setBackground(ELEMENTS_COLOR);
        listManagerButton.setForeground(FOREGROUND_COLOR);
        listExampleMenu.setBackground(ELEMENTS_COLOR);
        listExampleMenu.setForeground(FOREGROUND_COLOR);
        taskListInfo.setBackground(ELEMENTS_COLOR);
        taskListInfo.setForeground(FOREGROUND_COLOR);
    }

    public boolean anyComponentIsNull() {
        return (mainPanel == null ||
                taskManagerPanel == null ||
                listManagerPanel == null ||
                listExampleMenu == null ||
                taskListInfo == null ||
                taskManagerButton == null ||
                listManagerButton == null);

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
