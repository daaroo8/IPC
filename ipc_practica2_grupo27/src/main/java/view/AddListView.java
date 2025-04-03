package view;

import controller.ListManagerController;
import model.ListElement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListView extends JDialog{
    private JLabel nameLabel;
    private JTextField listToAddTextField;
    private JButton addListButton;
    private JPanel mainPanel;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    private static final Font FONT = new Font("Helvetica", Font.BOLD, 14);

    private final ListManagerController listManagerController;

    public AddListView(ListManagerController listManagerController) {
        initComponents();

        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        setContentPane(mainPanel);
        setModal(true);

        this.listManagerController = listManagerController;

        addListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listManagerController.handleAddListItem();
            }
        });
    }

    public String getNewTaskListTextValue() {
        return listToAddTextField.getText();
    }

    public void restartAddTaskListInput() {
        listToAddTextField.setText("");
    }

    public void initComponents() {
        listToAddTextField.setBorder(new EmptyBorder(5, 10, 5, 10));

        nameLabel.setFont(FONT);
        addListButton.setFont(FONT);
        listToAddTextField.setFont(FONT);

        initColors();
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
    }
}
