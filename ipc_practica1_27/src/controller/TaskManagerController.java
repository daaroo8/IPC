package controller;

import model.Task;
import model.TaskManagerModel;
import view.TaskManagerView;

public class TaskManagerController {
    private final TaskManagerView view;
    private final TaskManagerModel model;

    public TaskManagerController(TaskManagerView view) {
        this.view = view;
        this.model = new TaskManagerModel();
    }

    public void handleOpenFilterDialogEvent(){
        view.showFilterDialog();
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
            view.showErrorModal("La categoría: '" + newCategory + "' ya existe o supera los caracteres máximos (10).");
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

        if (!model.isValidTask(newTask) && model.getTaskEditing() == null) {
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
        view.setActionStatusLabel("Buscando...");

        String newTextFilterValue = view.getSearchTextFieldValue();

        if (Character.isLetter(newCharacter) || Character.isDigit(newCharacter)) {
            newTextFilterValue += newCharacter;
        }

        model.filterTasks(
                newTextFilterValue,
                view.getPriorityFilterValue(),
                view.getPercentageFilterMode(),
                view.getPercentageFilterValue(),
                view.getCreationChooserFilterMode(),
                view.getCreationChooserDate(),
                view.getDeadlineChooserFilterMode(),
                view.getDeadlineChooserDate(),
                view.getSelectCategoryFilterValue(),
                view.getSubtaskFilterValue()
        );

        view.updateTaskList(model.getTasksFiltered());
        view.setActionStatusLabel("");
    }

    public void handleAcceptFiltersDialog() {
        view.setActionStatusLabel("Buscando...");

        model.filterTasks(
                view.getSearchTextFieldValue(),
                view.getPriorityFilterValue(),
                view.getPercentageFilterMode(),
                view.getPercentageFilterValue(),
                view.getCreationChooserFilterMode(),
                view.getCreationChooserDate(),
                view.getDeadlineChooserFilterMode(),
                view.getDeadlineChooserDate(),
                view.getSelectCategoryFilterValue(),
                view.getSubtaskFilterValue()
        );

        view.closeFilterDialog();
        view.updateTaskList(model.getTasksFiltered());
        view.setActionStatusLabel("");
    }

    public void handleCancelFiltersDialog() {
        view.closeFilterDialog();
    }
}
