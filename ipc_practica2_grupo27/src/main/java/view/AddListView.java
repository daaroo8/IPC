package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddListView {
    private JLabel nameLabel;
    private JTextField listToAddTextField;
    private JButton addListButton;
    private JPanel mainPanel;

    public AddListView() {
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(nameLabel);
        mainPanel.add(listToAddTextField);
        mainPanel.add(addListButton);
        mainPanel.setBackground(new Color(161, 197, 255));
        nameLabel.setForeground(new Color(51, 51, 51));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Añadir lista");
        frame.setContentPane(new AddListView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
