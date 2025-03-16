package controller;

import model.Task;
import model.TaskManagerModel;
import view.TaskManagerFilterDialog;
import view.TaskManagerView;

// TODO: cuando se da a editar una tarea, y sin tocar nada, deberia no hacer nada, y sale el error de que
// TODO: ya existe

// TODO: no se como poner el mensaje de actionStatusLabel cuando se esta creando por primera vez la tarea

//TODO: quitar el actionStatusLabel cuando se termine de buscar, filtrar...

public class TaskManagerController {
    private final TaskManagerView view;
    private final TaskManagerModel model;

    public TaskManagerController(TaskManagerView view) {
        this.view = view;
        this.model = new TaskManagerModel();
    }

    public void handleOpenFilterDialogEvent(){
        view.setActionStatusLabel("Filtrando...");

        TaskManagerFilterDialog filterDialog = new TaskManagerFilterDialog();
        filterDialog.pack();
        filterDialog.setLocationRelativeTo(null);
        filterDialog.setVisible(true);

    }
    public void handleSelectCheckBoxEvent() {
        if (view.getCheckBoxSelected()) {
            view.setPercentageSliderValue(view.getMaximumPercentageSliderValue());
        } else {
            view.setPercentageSliderValue(model.getLastPercentage());
        }
    }

    public void handlePercentageSliderChangeEvent() {
        view.setCheckBoxSelected(view.getPercentageSliderValue() == view.getMaximumPercentageSliderValue());

        if (view.getPercentageSliderValue() != view.getMaximumPercentageSliderValue())
            model.setLastPercentage(view.getPercentageSliderValue());
    }

    public void handleAddCategoryEvent() {
        String newCategory = view.getAddCategoryTextFieldValue();

        if (!model.isValidCategoryToAdd(newCategory)) {
            view.showErrorModal("La categoría: '" + newCategory + "' ya existe.");
            return;
        }

        model.addCategory(newCategory);
        view.updateCategoriesList(model.getCategories());
        view.setAddCategoryTextFieldValue("");
        view.setCategoryComboBoxValue(newCategory);
    }

    public void handleSaveButtonEvent() {
        if (!model.isValidName(view.getNameTextFieldValue())) {
            view.showErrorModal("El nombre debe tener entre 1 y 10 caracteres.");
            return;
        }

        if (!model.isValidDescription(view.getDescriptionTextAreaValue())) {
            view.showErrorModal("La descripción debe tener 100 o menos caracteres.");
            return;
        }

        if (!model.isValidDate(view.getDateValue())) {
            view.showErrorModal("La fecha ha de tener el siguiente formato:\n" + TaskManagerModel.FORMAT_DATE_TIME_FORMATTER + "\nY ser posterior a la actual.");
            return;
        }

        if (!model.isValidCategory(view.getSelectedCategory())) {
            view.showErrorModal("Seleccione la categoría.");
            return;
        }
        view.setActionStatusLabel("Tarea '" + view.getNameTextFieldValue() + "' guardada.");
        Task newTask = new Task(view.getNameTextFieldValue(), view.getDescriptionTextAreaValue(), view.getPriorityValue(), view.getDateValue(), view.getPercentageSliderValue(), view.getSelectedCategory(), view.getSelectedSubTask());

        if (!model.isValidTask(newTask) && model.getTaskEditing() != null) {
            view.showErrorModal("La tarea: '" + view.getNameTextFieldValue() + "' ya existe.");
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

            model.setTaskEditing(null);
            view.setEditButtonEnabled(true);
        }


        view.updateCategoriesList(model.getCategories());
        view.updateSubtasksList(model.getTasks());
        view.updateTaskList(model.getTasks());

        view.restartTaskTextFields();
    }

    public void handleSelectComboBoxEvent() {
        if (view.getSelectedCategory() == null || view.getSelectedCategory().equals("Seleccionar opción"))
            return;

        view.removeCategoryItemAt(0);
    }

    public void handleSelectTaskEvent() {
        Task taskSelected = view.getTaskSelected();

        if (taskSelected == null) return;

        view.setActionStatusLabel("Mostrando información de: " + taskSelected.getName() + ".");
        view.setNameInfoTextFieldValue(taskSelected.getName());
        view.setDescriptionInfoTextAreaValue(taskSelected.getDescription());
        view.setCategoryInfoTextFieldValue(taskSelected.getCategory());
        view.setSubtaskInfoTextFieldValue(taskSelected.getSubtask());
        view.setCompletedProgressBarValue(taskSelected.getPercentage());
        view.setPriorityInfoTextFieldValue(taskSelected.getPriority().toString());
        view.setDateCreationInfoFormattedTextFieldValue(taskSelected.getCreationDate().format(TaskManagerModel.DATE_TIME_FORMATTER));
        view.setDeadLineInfoTextFieldValue(taskSelected.getDeadline().format(TaskManagerModel.DATE_TIME_FORMATTER));
    }

    public void handleDeleteButtonEvent() {
        view.setActionStatusLabel("Tarea '" + view.getTaskSelected().getName() + "' borrada.");

        model.removeTask(view.getTaskSelected());
        view.updateTaskList(model.getTasks());
        view.updateSubtasksList(model.getTasks());
    }

    public void handleEditButtonEvent() {
        model.setTaskEditing(view.getTaskSelected());

        view.setNameTextFieldValue(model.getTaskEditing().getName());
        view.setDescriptionTextAreaValue(model.getTaskEditing().getDescription());
        view.setPriorityComboBoxValue(model.getTaskEditing().getPriority());
        view.setDateChooserValue(model.getTaskEditing().getDeadline());
        view.setPercentageSliderValue(model.getTaskEditing().getPercentage());
        view.setCategoryComboBoxValue(model.getTaskEditing().getCategory());
        view.setSubtaskComboBoxValue(model.getTaskEditing().getSubtask());

        view.setActionStatusLabel("Editando '" + view.getTaskSelected().getName() + "'.");
        view.setEditButtonEnabled(false);
    }

    public void handleKeyTypedSearchInputEvent(char newCharacter) {
        if (Character.isLetter(newCharacter)) {
            model.filterTasks(view.getSearchTextFieldValue() + newCharacter);
        } else {
            model.filterTasks(view.getSearchTextFieldValue());
        }

        view.updateTaskList(model.getTasksFiltered());
        view.setActionStatusLabel("Buscando...");
    }
}
