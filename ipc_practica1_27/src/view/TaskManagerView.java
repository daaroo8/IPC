package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TaskManagerView extends JFrame {
    private JTextField nameTextField;
    private JLabel nameLabel;
    private JTextArea descriptionTextArea;
    private JLabel descriptionLabel;
    private JComboBox priorityComboBox;
    private JLabel dateLabel;
    private JLabel priorityLabel;
    private JSlider percentageSlider;
    private JCheckBox completedCheckBox;
    private JLabel completedPercentageLabel;
    private JLabel completedLabel;
    private JComboBox categoryComboBox;
    private JLabel categoryLabel;
    private JComboBox subtaskComboBox;
    private JLabel subtaskLabel;
    private JButton saveButton;
    private JTextField searchTextField;
    private JButton filtersButton;
    private JList taskList;
    private JTextField nameInfoTextField;
    private JLabel nameInfoLabel;
    private JTextArea descriptionInfoTextArea;
    private JLabel descriptionInfoLabel;
    private JTextField categoryInfoTextField;
    private JLabel categoryInfoLabel;
    private JTextField subtaskInfoTextField;
    private JLabel subtaskInfoLabel;
    private JLabel creationDateInfoLabel;
    private JFormattedTextField dateCreationFormattedTextField;
    private JFormattedTextField deadlineInfoFormattedTextField;
    private JLabel deadLineInfoLabel;
    private JProgressBar completedProgressBar;
    private JTextField priorityInfoTextField;
    private JLabel priorityInfoLabel;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextField headerListTextField;
    private JButton searchButton;
    private JTextField addCategoryTextField;
    private JButton addCategoryButton;
    private JPanel calendarPanel;
    private JDateChooser dateChooser;

    private int lastPercentage = 50;

    public TaskManagerView() {

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(dateChooser, BorderLayout.CENTER);

        completedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(completedCheckBox.isSelected());
                if (completedCheckBox.isSelected()) {
                    percentageSlider.setValue(100);
                } else {
                    percentageSlider.setValue(lastPercentage);
                }
            }
        });


        percentageSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                completedCheckBox.setSelected(percentageSlider.getValue() == percentageSlider.getMaximum());
                if (percentageSlider.getValue() != percentageSlider.getMaximum())
                    lastPercentage = percentageSlider.getValue();
            }
        });

        nameInfoTextField.setEditable(false);
        descriptionInfoTextArea.setEditable(false);
        categoryInfoTextField.setEditable(false);
        subtaskInfoTextField.setEditable(false);
        dateCreationFormattedTextField.setEditable(false);
        deadlineInfoFormattedTextField.setEditable(false);
        priorityInfoTextField.setEditable(false);
        headerListTextField.setEditable(false);

//        dateFormattedTextField.setFormatterFactory(
//            new javax.swing.text.DefaultFormatterFactory(
//            new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))
//            )
//        );
        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isValidCategoryToAdd()) {
                    String category = (String) addCategoryTextField.getText();
                    categoryComboBox.addItem(category);
                } else{
                    System.out.println("No se puede agregar el categoria");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isValidName() && isValidDescription() && isValidPriority() && isValidCategory() && isValidDate() && !isRepeatedtask()) {

                    String task = (String) nameTextField.getText();
                    subtaskComboBox.addItem(task);

                } else {
                    if(!isValidName()){
                        JOptionPane.showMessageDialog(null, "El nombre debe tener entre 1 y 10 caracteres.", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    } else if(!isValidDescription()){
                        JOptionPane.showMessageDialog(null, "La descripción debe tener 100 o menos caracteres.", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    } else if(!isValidDate()){
                        JOptionPane.showMessageDialog(null, "La fecha ha de tener el siguiente formato:\ndd/mm/aaaa\nY ser posterior a la actual.", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    } else if(!isValidPriority()){
                        JOptionPane.showMessageDialog(null, "Seleccione la prioridad.", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    } else if(!isValidCategory()){
                        JOptionPane.showMessageDialog(null, "Seleccione la categoría.", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    } else if(isRepeatedtask()){
                        JOptionPane.showMessageDialog(null, "La tarea: '" + nameTextField.getText() + "' ya existe.", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }

    public boolean isValidName() {
        return !(nameTextField.getText().isEmpty()) &&
                    nameTextField.getText().length() <= 10;
    }

    public boolean isValidDescription() {
        return descriptionTextArea.getText().length() <= 100;
    }

    public boolean isValidSearch() {
        return !(searchTextField.getText().isEmpty()) &&
                searchTextField.getText().length() <= 10;
        //TODO: añadir que exista el nombre en la lista de tareas
    }

    public boolean isValidCategoryToAdd() {
        return !(addCategoryTextField.getText().isEmpty()) &&
                addCategoryTextField.getText().length() <= 10;
    }

    public boolean isValidPriority() {
        return priorityComboBox.getSelectedIndex() != 0;
    }

    public boolean isValidCategory() {
        return categoryComboBox.getSelectedIndex() != 0;
    }

    public boolean isValidDate(){
        return dateChooser.getDate() != null &&
                dateChooser.getDate().toInstant().isAfter(Instant.now());
    }

    public boolean isRepeatedtask() {
        boolean found = false;

        for(int i = 0; i < subtaskComboBox.getItemCount(); i++) {
            if (subtaskComboBox.getItemAt(i).equals(nameTextField.getText())) {
                found = true;
                break;
            }
        }

        return found;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestor de tareas");
        frame.setContentPane(new TaskManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
