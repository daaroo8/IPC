package controller;

import model.Task;
import model.TaskManagerModel;
import view.TaskManagerView;

import javax.swing.*;
import java.time.ZoneId;

public class TaskManagerController {
    private TaskManagerView view;
    private TaskManagerModel model;

    public TaskManagerController(TaskManagerView view) {
        this.view = view;
        this.model = new TaskManagerModel();
    }

    public void handleSelectCheckBoxEvent() {
        if (view.getCheckBoxSelected()) {
            view.setPercentageSliderValue(view.getMaximumPercentageSliderValue());
        } else {
            view.setPercentageSliderValue(model.getLastPercentage());
        }
    }

    public void handlePercentageSliderChangeEvent() {
        view.setCheckBoxSelected(view.getPercentageSliderValue() != view.getMaximumPercentageSliderValue());

        if (view.getPercentageSliderValue() != view.getMaximumPercentageSliderValue())
            model.setLastPercentage(view.getPercentageSliderValue());
    }

    public void handleAddCategoryEvent() {
        String newCategory = view.getAddCategoryTextFieldValue();

        if (!model.isValidCategoryToAdd(newCategory)) {
            JOptionPane.showMessageDialog(null, "La categoría: '" + newCategory + "' ya existe.", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.addCategory(newCategory);
        view.updateCategoriesList(model.getCategories());
    }

    public void handleSaveButtonEvent() {
        if (!model.isValidName(view.getNameTextFieldValue())) {
            JOptionPane.showMessageDialog(null, "El nombre debe tener entre 1 y 10 caracteres.", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!model.isValidDescription(view.getDescriptionTextAreaValue())) {
            JOptionPane.showMessageDialog(null, "La descripción debe tener 100 o menos caracteres.", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!model.isValidDate(view.getDateValue())) {
            JOptionPane.showMessageDialog(null, "La fecha ha de tener el siguiente formato:\ndd/mm/aaaa\nY ser posterior a la actual.", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!model.isValidCategory(view.getSelectedCategory())) {
            JOptionPane.showMessageDialog(null, "Seleccione la categoría.", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Task newTask = new Task(view.getNameTextFieldValue(), view.getDescriptionTextAreaValue(), view.getPriorityValue(), view.getDateValue(), view.getPercentageSliderValue(), view.getSelectedCategory(), view.getSelectedSubTask());

        if (!model.isValidTask(newTask) && model.getTaskEditing() != null) {
            JOptionPane.showMessageDialog(null, "La tarea: '" + view.getNameTextFieldValue() + "' ya existe.", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.getTaskEditing() == null) {
            model.addTask(newTask);
        } else {
            model.getTaskEditing().setName(view.getNameTextFieldValue());
            model.getTaskEditing().setDescription(view.getDescriptionTextAreaValue());
            model.getTaskEditing().setDeadline(view.getDateValue());
            model.getTaskEditing().setPriority(view.getPriorityValue());
            model.getTaskEditing().setPercentage(view.getPercentageSliderValue());
            model.getTaskEditing().setCategory(view.getSelectedCategory());
            model.getTaskEditing().setSubtask(view.getSelectedSubTask());
        }

        view.updateSubtasksList(model.getTasks());
        view.updateTaskList(model.getTasks());

        // TODO: MAL, SOLUCIONAR CON LAS FUNCIONES DE CADA PARTE
//        categoryComboBox.addItem("Seleccionar opción");
//
//        nameTextField.setText("");
//        descriptionTextArea.setText("");
//        dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        priorityComboBox.setSelectedIndex(0);
//        percentageSlider.setValue(50);
//        categoryComboBox.setSelectedItem("Seleccionar opción");
//        subtaskComboBox.setSelectedItem("No es subtarea");
//
//        taskEditing = null;
//        updateTaskList();
    }
}
