package view;

import com.toedter.calendar.JDateChooser;
import model.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
    private JList<Task> taskList;
    private DefaultListModel<Task> taskListModel;
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
    private JTextField addCategoryTextField;
    private JButton addCategoryButton;
    private JPanel calendarPanel;
    private JPanel rightTopPanel;
    private JPanel rightBottomPanel;
    private JLabel searchLabel;
    private JDateChooser dateChooser;


    private int lastPercentage = 50;
    private boolean editing = false;

    private ArrayList<Task> tasks;

    public TaskManagerView() {

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(dateChooser, BorderLayout.CENTER);

        tasks = new ArrayList<>();

        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.CYAN);
        rightTopPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
        rightTopPanel.setBackground(Color.CYAN);
//        rightBottomPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
//        rightBottomPanel.setBackground(Color.CYAN);

        leftPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
//        rightPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        taskListModel = new DefaultListModel<>();
        taskList.setModel(taskListModel);
        taskList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        updateJList();
        completedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidCategoryToAdd()) {

                    String category = (String) addCategoryTextField.getText();
                    categoryComboBox.addItem(category);

                } else {
                    JOptionPane.showMessageDialog(null, "La categoria: '" + addCategoryTextField.getText() + "' ya existe.", "¡Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isValidName()) {
                    JOptionPane.showMessageDialog(null, "El nombre debe tener entre 1 y 10 caracteres.", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidDescription()) {
                    JOptionPane.showMessageDialog(null, "La descripción debe tener 100 o menos caracteres.", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidDate()) {
                    JOptionPane.showMessageDialog(null, "La fecha ha de tener el siguiente formato:\ndd/mm/aaaa\nY ser posterior a la actual.", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidCategory()) {
                    JOptionPane.showMessageDialog(null, "Seleccione la categoría.", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isRepeatedTask() && !editing) {
                    JOptionPane.showMessageDialog(null, "La tarea: '" + nameTextField.getText() + "' ya existe.", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String taskName = nameTextField.getText();

                if (!editing) {
                    subtaskComboBox.addItem(taskName);
                    tasks.add(new Task(
                            taskName,
                            descriptionTextArea.getText(),
                            Task.PRIORITY.LOW,
                            dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                            percentageSlider.getValue(),
                            categoryComboBox.getSelectedItem().toString(),
                            subtaskComboBox.getSelectedItem().toString()
                    ));
                } else {
                    Task task = taskList.getSelectedValue();

                    System.out.println(percentageSlider.getValue());

                    task.setName(nameTextField.getText());
                    task.setDescription(descriptionTextArea.getText());
                    task.setDeadline(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    task.setPriority(Task.PRIORITY.HIGH);
                    task.setPercentage(percentageSlider.getValue());
                    task.setCategory(categoryComboBox.getSelectedItem().toString());
                    task.setSubtask(subtaskComboBox.getSelectedItem().toString());
                }

                categoryComboBox.addItem("Seleccionar opción");

                nameTextField.setText("");
                descriptionTextArea.setText("");
                dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                priorityComboBox.setSelectedIndex(0);
                percentageSlider.setValue(50);
                categoryComboBox.setSelectedItem("Seleccionar opción");
                subtaskComboBox.setSelectedItem("No es subtarea");

                editing = false;
                updateJList();
            }
        });


        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                categoryComboBox.removeItem("Seleccionar opción");
            }
        });


        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Task taskSelected = taskList.getSelectedValue();

                if (taskSelected == null) return;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                System.out.println(taskSelected.toString());
                nameInfoTextField.setText(taskSelected.getName());
                completedProgressBar.setValue(taskSelected.getPercentage());
                descriptionInfoTextArea.setText(taskSelected.getDescription());
                priorityInfoTextField.setText(taskSelected.getPriority().toString());
                dateCreationFormattedTextField.setText(taskSelected.getCreationDate().format(formatter));
                deadlineInfoFormattedTextField.setText(taskSelected.getDeadline().format(formatter));
                categoryInfoTextField.setText(taskSelected.getCategory());
                subtaskInfoTextField.setText(taskSelected.getSubtask());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasks.remove(taskList.getSelectedValue());
                updateJList();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editing = true;

                Task selectedTask = taskList.getSelectedValue();

                nameTextField.setText(selectedTask.getName());
                descriptionTextArea.setText(selectedTask.getDescription());
                priorityInfoTextField.setText(selectedTask.getPriority().toString());
                dateChooser.setDate(Date.from(selectedTask.getDeadline().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                percentageSlider.setValue(selectedTask.getPercentage());
                categoryComboBox.setSelectedItem(selectedTask.getCategory());
                subtaskComboBox.setSelectedItem(selectedTask.getSubtask());
            }
        });
    }

    public void updateJList() {

        taskListModel.clear();

        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
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

        boolean found = false;

        for (int i = 0; i < categoryComboBox.getItemCount(); i++) {
            if (categoryComboBox.getItemAt(i).equals(addCategoryTextField.getText())) {
                found = true;
                break;
            }
        }

        return !(addCategoryTextField.getText().isEmpty()) &&
                addCategoryTextField.getText().length() <= 10 &&
                !found;
    }


    public boolean isValidCategory() {
        if (categoryComboBox.getSelectedItem() == null) return false;

        return !categoryComboBox.getSelectedItem().equals("Seleccionar opción");
    }

    /**
     * Consulta si la fecha sea valida (posterior a la actual)
     *
     * @return true: en caso de que sea valida
     * false: en caso de que no lo sea
     */
    public boolean isValidDate() {
        return dateChooser.getDate() != null &&
                dateChooser.getDate().toInstant().isAfter(Instant.now());
    }

    /**
     * Consulta si la tarea a añadir, ya ha sido añadida previamente
     *
     * @return true: en caso de que ya exista
     * false: en caso de que no
     */
    public boolean isRepeatedTask() {
        boolean found = false;

        for (int i = 0; i < subtaskComboBox.getItemCount(); i++) {
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
