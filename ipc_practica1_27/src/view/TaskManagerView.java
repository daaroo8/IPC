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

import controller.TaskManagerController;

public class TaskManagerView extends JFrame {
    private JTextField nameTextField;
    private JLabel nameLabel;
    private JTextArea descriptionTextArea;
    private JLabel descriptionLabel;
    private JComboBox<String> priorityComboBox;
    private JLabel dateLabel;
    private JLabel priorityLabel;
    private JSlider percentageSlider;
    private JCheckBox completedCheckBox;
    private JLabel completedPercentageLabel;
    private JLabel completedLabel;
    private JComboBox<String> categoryComboBox;
    private JLabel categoryLabel;
    private JComboBox<String> subtaskComboBox;
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

    private TaskManagerController taskManagerController;

    private Task taskEditing = null;

    private ArrayList<Task> tasks;


    public TaskManagerView() {
        initComponents();
        taskManagerController = new TaskManagerController(this);
        tasks = new ArrayList<>();

    }

    public boolean getCheckBoxSelected() {
        return completedCheckBox.isSelected();
    }

    public void setCheckBoxSelected(boolean selected) {
        completedCheckBox.setSelected(selected);
    }

    public int getPercentageSliderValue() {
        return percentageSlider.getValue();
    }

    public void setPercentageSliderValue(int percentage) {
        percentageSlider.setValue(percentage);
    }

    public int getMaximumPercentageSliderValue() {
        return percentageSlider.getMaximum();
    }

    public String getAddCategoryTextFieldValue() {
        return addCategoryTextField.getText();
    }

    public String getNameTextFieldValue() {
        return nameTextField.getText();
    }

    public String getDescriptionTextAreaValue() {
        return descriptionTextArea.getText();
    }

    public LocalDate getDateValue() {
        return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getSelectedCategory() {
        // TODO: mirar si se puede eliminar el cast
        return (String) categoryComboBox.getSelectedItem();
    }

    public String getSelectedSubTask() {
        return (String) subtaskComboBox.getSelectedItem();
    }

    public Task.PRIORITY getPriorityValue() {
        return Task.PRIORITY.valueOf((String) priorityComboBox.getSelectedItem());
    }

    public void updateTaskList(ArrayList<Task> tasks) {
        taskListModel.clear();

        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    public void updateCategoriesList(ArrayList<String> categories) {
        categoryComboBox.removeAllItems();

        for (String category : categories) {
            categoryComboBox.addItem(category);
        }
    }

    public void updateSubtasksList(ArrayList<Task> subtasks) {
        subtaskComboBox.removeAllItems();

        for (Task subtask : subtasks) {
            subtaskComboBox.addItem(subtask.getName());
        }
    }

    public boolean isValidSearch() {
        return !(searchTextField.getText().isEmpty()) &&
                searchTextField.getText().length() <= 10;
        //TODO: añadir que exista el nombre en la lista de tareas
    }





    /**
     * Consulta si la tarea a añadir, ya ha sido añadida previamente
     *
     * @return true: en caso de que ya exista
     * false: en caso de que no
     */
    public boolean isRepeatedTask() {
        for (int i = 0; i < subtaskComboBox.getItemCount(); i++) {
            if (subtaskComboBox.getItemAt(i).equals(nameTextField.getText())) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestor de tareas");
        frame.setContentPane(new TaskManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(dateChooser, BorderLayout.CENTER);


        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.CYAN);
        rightTopPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
        rightTopPanel.setBackground(Color.CYAN);

        leftPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        taskListModel = new DefaultListModel<>();
        taskList.setModel(taskListModel);
        taskList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        updateTaskList();

        nameInfoTextField.setEditable(false);
        descriptionInfoTextArea.setEditable(false);
        categoryInfoTextField.setEditable(false);
        subtaskInfoTextField.setEditable(false);
        dateCreationFormattedTextField.setEditable(false);
        deadlineInfoFormattedTextField.setEditable(false);
        priorityInfoTextField.setEditable(false);
        headerListTextField.setEditable(false);

        completedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleSelectCheckBoxEvent();
            }
        });

        percentageSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                taskManagerController.handlePercentageSliderChangeEvent();
            }
        });

        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleAddCategoryEvent();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleSaveButtonEvent();
            }
        });


        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: eliminar magic string
                categoryComboBox.removeItem("Seleccionar opción");
            }
        });


        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Task taskSelected = taskList.getSelectedValue();

                if (taskSelected == null) return;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                System.out.println(taskSelected);
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
                updateTaskList();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskEditing = taskList.getSelectedValue();

                nameTextField.setText(taskEditing.getName());
                descriptionTextArea.setText(taskEditing.getDescription());
                priorityInfoTextField.setText(taskEditing.getPriority().toString());
                dateChooser.setDate(Date.from(taskEditing.getDeadline().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                percentageSlider.setValue(taskEditing.getPercentage());
                categoryComboBox.setSelectedItem(taskEditing.getCategory());
                subtaskComboBox.setSelectedItem(taskEditing.getSubtask());
            }
        });
    }
}
