package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import controller.TaskManagerController;
import model.Task;
import model.enums.RANGE_SELECTIONS;


//TODO: reinciar filtros

public class TaskManagerFilterDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> priorityFilterComboBox;
    private JSlider percentageFilterSlider;
    private JComboBox<String> categoryFilterComboBox;
    private JComboBox<String> subtaskFilterComboBox;
    private JPanel dateCreationPanel;
    private JPanel deadLinePanel;
    private JPanel sinceDateCreationPanel;
    private JPanel sinceDeadLinePanel;
    private JPanel fromPercentagePanel;
    private JRadioButton untilPercentageRadioButton;
    private JRadioButton fromPercentageRadioButton;
    private JRadioButton untilDateCreationRadioButton;
    private JRadioButton sinceDateCreationRadioButton;
    private JRadioButton untilDeadLineRadioButton;
    private JRadioButton sinceDeadLineRadioButton;
    private JRadioButton noPercentageRadioButton;
    private JRadioButton noDateCreationRadioButton;
    private JRadioButton noDeadLineRadioButton;
    private JButton buttonReset;
    private JDateChooser dateCreationChooser;
    private JDateChooser deadLineChooser;

    public static final String SELECT_CATEGORY_PLACEHOLDER = "Seleccionar opción";
    public static final String SELECT_NOT_SUBTASK_PLACEHOLDER = "No es subtarea";
    public static final String NOT_FILTER_SELECTED_TEXT = "No";

    private final TaskManagerController taskManagerController;

    public TaskManagerFilterDialog(TaskManagerController taskManagerController) {
        initComponents();

        this.taskManagerController = taskManagerController;
    }

    public void updateCategoriesList(ArrayList<String> categories) {
        categoryFilterComboBox.removeAllItems();

        categoryFilterComboBox.addItem(NOT_FILTER_SELECTED_TEXT);
        for (String category : categories) {
            categoryFilterComboBox.addItem(category);
        }
    }

    public void updateSubtaskList(ArrayList<Task> subtasks) {
        subtaskFilterComboBox.removeAllItems();

        subtaskFilterComboBox.addItem(NOT_FILTER_SELECTED_TEXT);
        subtaskFilterComboBox.addItem(SELECT_NOT_SUBTASK_PLACEHOLDER);

        for (Task subtask : subtasks) {
            subtaskFilterComboBox.addItem(subtask.getName());
        }
    }

    public String getPriorityFilterValue() {
        if (priorityFilterComboBox.getSelectedItem() == null || priorityFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return priorityFilterComboBox.getSelectedItem().toString();
    }

    public RANGE_SELECTIONS getPercentageFilterMode() {
        if (untilPercentageRadioButton.isSelected()) {
            return RANGE_SELECTIONS.UNTIL;
        }

        if (fromPercentageRadioButton.isSelected()) {
            return RANGE_SELECTIONS.SINCE;
        }

        return RANGE_SELECTIONS.NO;
    }

    public int getPercentageFilterValue() {
        return percentageFilterSlider.getValue();
    }

    public RANGE_SELECTIONS getCreationChooserFilterMode() {
        if (untilDateCreationRadioButton.isSelected()) {
            return RANGE_SELECTIONS.UNTIL;
        }

        if (sinceDateCreationRadioButton.isSelected()) {
            return RANGE_SELECTIONS.SINCE;
        }

        return RANGE_SELECTIONS.NO;
    }

    public LocalDate getCreationChooserDate() {
        if (dateCreationChooser.getDate() == null)
            return null;

        return dateCreationChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public RANGE_SELECTIONS getDeadlineChooserFilterMode() {
        if (untilDeadLineRadioButton.isSelected()) {
            return RANGE_SELECTIONS.UNTIL;
        }

        if (sinceDeadLineRadioButton.isSelected()) {
            return RANGE_SELECTIONS.SINCE;
        }

        return RANGE_SELECTIONS.NO;
    }

    public LocalDate getDeadlineChooserDate() {
        if (deadLineChooser.getDate() == null)
            return null;

        return deadLineChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getSelectCategoryFilterValue() {
        if (categoryFilterComboBox.getSelectedItem() == null || categoryFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return categoryFilterComboBox.getSelectedItem().toString();
    }

    public String getSubtaskFilterValue() {
        if (subtaskFilterComboBox.getSelectedItem() == null || subtaskFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return subtaskFilterComboBox.getSelectedItem().toString();
    }

    private void initComponents() {
        if (anyComponentIsNull())
            throw new RuntimeException("No se puede inicializar el componente");

        dateCreationChooser = new JDateChooser();
        deadLineChooser = new JDateChooser();

        dateCreationChooser.setDateFormatString("dd/MM/yyyy");
        deadLineChooser.setDateFormatString("dd/MM/yyyy");

        dateCreationChooser.setDate(new Date());
        deadLineChooser.setDate(new Date());

        dateCreationPanel.setLayout(new BorderLayout());
        dateCreationPanel.add(dateCreationChooser, BorderLayout.CENTER);

        deadLinePanel.setLayout(new BorderLayout());
        deadLinePanel.add(deadLineChooser, BorderLayout.CENTER);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleResetFiltersDialog();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleAcceptFiltersDialog();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleCancelFiltersDialog();
            }
        });
    }

    private boolean anyComponentIsNull() {
        return (
            dateCreationPanel == null ||
            deadLinePanel == null ||
            buttonOK == null ||
            buttonCancel == null ||
            buttonReset == null
        );
    }
}
