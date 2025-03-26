package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
        // Agregar padding (10 píxeles en cada lado)
        taskManagerPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        listManagerPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new PrincipalMenuView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
