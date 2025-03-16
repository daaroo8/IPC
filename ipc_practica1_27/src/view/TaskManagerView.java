package view;

import com.toedter.calendar.JDateChooser;
import model.PRIORITY;
import model.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import controller.TaskManagerController;

public class TaskManagerView extends JFrame {
    private JTextField nameTextField;
    private JLabel nameLabel;
    private JTextArea descriptionTextArea;
    private JLabel descriptionLabel;
    private JComboBox<PRIORITY> priorityComboBox;
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
    private JFormattedTextField dateCreationInfoFormattedTextField;
    private JFormattedTextField deadLineInfoFormattedTextField;
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

    public static final String SELECT_CATEGORY_PLACEHOLDER = "Seleccionar opción";
    public static final String SELECT_NOT_SUBTASK_PLACEHOLDER = "No es subtarea";

    private final TaskManagerController taskManagerController;

    public TaskManagerView() {
        initComponents();
        taskManagerController = new TaskManagerController(this);
    }

    public boolean getCheckBoxSelected() {
        return completedCheckBox.isSelected();
    }

    public void setCheckBoxSelected(boolean selected) {
        completedCheckBox.setSelected(selected);
    }

    public void setAddCategoryTextFieldValue(String text) {
        addCategoryTextField.setText(text);
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
        if (dateChooser.getDate() == null)
            return null;

        return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getSelectedCategory() {
        return (String) categoryComboBox.getSelectedItem();
    }

    public String getSelectedSubTask() {
        return (String) subtaskComboBox.getSelectedItem();
    }

    public PRIORITY getPriorityValue() {
        // TODO: esto habrá que mirarlo
        //return Task.PRIORITY.valueOf((String) priorityComboBox.getSelectedItem());
        return PRIORITY.valueOf("HIGH");
    }

    public void restartTaskTextFields() {
        nameTextField.setText("");
        descriptionTextArea.setText("");
        dateChooser.setDate(new Date());
        priorityComboBox.setSelectedIndex(0);
        percentageSlider.setValue(50);
        categoryComboBox.setSelectedIndex(0);
        subtaskComboBox.setSelectedIndex(0);
    }

    public void updateTaskList(ArrayList<Task> tasks) {
        taskListModel.clear();

        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    public void updateCategoriesList(ArrayList<String> categories) {
        categoryComboBox.removeAllItems();

        categoryComboBox.addItem(TaskManagerView.SELECT_CATEGORY_PLACEHOLDER);

        for (String category : categories) {
            categoryComboBox.addItem(category);
        }
    }

    public void updateSubtasksList(ArrayList<Task> subtasks) {
        subtaskComboBox.removeAllItems();

        subtaskComboBox.addItem(TaskManagerView.SELECT_NOT_SUBTASK_PLACEHOLDER);

        for (Task subtask : subtasks) {
            subtaskComboBox.addItem(subtask.getName());
        }
    }

    public void removeCategoryItemAt(int categoryIndex) {
        categoryComboBox.removeItemAt(categoryIndex);
    }

    public Task getTaskSelected() {
        return taskList.getSelectedValue();
    }

    public void setEditButtonEnabled(boolean enabled) {
        editButton.setEnabled(enabled);
    }

    public void setNameTextFieldValue(String text) {
        nameTextField.setText(text);
    }

    public void setDescriptionTextAreaValue(String text) {
        descriptionTextArea.setText(text);
    }

    public void setPriorityComboBoxValue(PRIORITY priority) {
        priorityComboBox.setSelectedItem(priority);
    }

    public void setDateChooserValue(LocalDate value) {
        dateChooser.setDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public void setCategoryComboBoxValue(String category) {
        categoryComboBox.setSelectedItem(category);
    }

    public void setSubtaskComboBoxValue(String subtask) {
        subtaskComboBox.setSelectedItem(subtask);
    }

    public void setNameInfoTextFieldValue(String text) {
        nameInfoTextField.setText(text);
    }

    public void setDescriptionInfoTextAreaValue(String text) {
        descriptionInfoTextArea.setText(text);
    }

    public void setPriorityInfoTextFieldValue(String text) {
        priorityInfoTextField.setText(text);
    }

    public void setCompletedProgressBarValue(int progress) {
        completedProgressBar.setValue(progress);
    }

    public void setCategoryInfoTextFieldValue(String text) {
        categoryInfoTextField.setText(text);
    }

    public void setSubtaskInfoTextFieldValue(String text) {
        subtaskInfoTextField.setText(text);
    }

    public void setDateCreationInfoFormattedTextFieldValue(String text) {
        dateCreationInfoFormattedTextField.setText(text);
    }

    public void setDeadLineInfoTextFieldValue(String text) {
        deadLineInfoFormattedTextField.setText(text);
    }

    public String getSearchTextFieldValue() {
        return searchTextField.getText();
    }

    public void showErrorModal(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showErrorModal(String message) {
        JOptionPane.showMessageDialog(null, message, "¡Error!", JOptionPane.ERROR_MESSAGE);
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
        if (anyComponentIsNull()) {
            return;
        }

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(new Date());
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

        nameInfoTextField.setEditable(false);
        descriptionInfoTextArea.setEditable(false);
        categoryInfoTextField.setEditable(false);
        subtaskInfoTextField.setEditable(false);
        dateCreationInfoFormattedTextField.setEditable(false);
        deadLineInfoFormattedTextField.setEditable(false);
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
                taskManagerController.handleSelectComboBoxEvent();
            }
        });


        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                taskManagerController.handleSelectTaskEvent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleDeleteButtonEvent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleEditButtonEvent();
            }
        });

        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                taskManagerController.handleKeyTypedSearchInputEvent(e.getKeyChar());
            }
        });

        filtersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private boolean anyComponentIsNull() {
        return (
                mainPanel == null ||
                rightTopPanel == null ||
                leftPanel == null ||
                calendarPanel == null ||
                taskList == null ||
                nameInfoTextField == null ||
                descriptionInfoTextArea == null ||
                categoryInfoTextField == null ||
                subtaskInfoTextField == null ||
                dateCreationInfoFormattedTextField == null ||
                deadLineInfoFormattedTextField == null ||
                priorityInfoTextField == null ||
                headerListTextField == null ||
                completedCheckBox == null ||
                percentageSlider == null ||
                addCategoryButton == null ||
                saveButton == null ||
                categoryComboBox == null ||
                deleteButton == null ||
                editButton == null ||
                searchTextField == null ||
                filtersButton == null
        );

    }
}
