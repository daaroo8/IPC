package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AddListView {
    private JLabel nameLabel;
    private JTextField listToAddTextField;
    private JButton addListButton;
    private JPanel mainPanel;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    public AddListView() {
        initColors();

        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    }

    public void initColors(){
        mainPanel.add(nameLabel);
        mainPanel.add(listToAddTextField);
        mainPanel.add(addListButton);
        mainPanel.setBackground(BACKGROUND_COLOR);
        nameLabel.setForeground(FOREGROUND_COLOR);
        addListButton.setBackground(ELEMENTS_COLOR);
        addListButton.setForeground(FOREGROUND_COLOR);
        listToAddTextField.setBackground(ELEMENTS_COLOR);
        listToAddTextField.setForeground(FOREGROUND_COLOR);
        listToAddTextField.setBorder(new LineBorder(ELEMENTS_COLOR));

        //TODO: INIT COMPONENTS meter esto y llamar a init colors desde ahi
        listToAddTextField.setBorder(new EmptyBorder(5, 10, 5, 10));

        Font font = new Font("Helvetica", Font.BOLD, 14);

        nameLabel.setFont(font);
        addListButton.setFont(font);
        listToAddTextField.setFont(font);
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
