package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PrincipalMenuView {
    private JPanel mainPanel;
    private JPanel taskManagerPanel;
    private JPanel listManagerPanel;
    private JButton taskManagerButton;
    private JLabel nameMenuLabel;
    private JLabel descriptionMenuLabel;
    private JLabel dateMenuLabel;
    private JList listExampleMenu;
    private JButton gestionarListasButton;

    public PrincipalMenuView() {
        // Reducir padding para evitar exceso de espacio
        taskManagerPanel.setBorder(new EmptyBorder(50, 50, 50, 10)); // Padding más pequeño
        listManagerPanel.setBorder(new EmptyBorder(50, 10, 50, 50)); // Padding más pequeño
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new PrincipalMenuView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
