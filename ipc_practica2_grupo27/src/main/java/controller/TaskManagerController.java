package controller;

import main.Main;
import model.Task;
import model.TaskList;
import model.TaskManagerModel;
import view.TaskManagerView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa el controlador del Gestor de Tareas
 */
public class TaskManagerController {
    private final TaskManagerView view;
    private final TaskManagerModel model;

    /**
     * Constructor del controlador de la gestión de tareas.
     *
     * @param view La vista que será utilizada por el controlador.
     */
    public TaskManagerController(TaskManagerView view) {
        this.view = view;
        this.model = Main.getTaskManagerModel();
    }

    /**
     * Maneja el evento para abrir el diálogo de filtros.
     */
    public void handleOpenFilterDialogEvent(){
        view.showFilterDialog();
    }

    /**
     * Maneja el evento de selección del checkbox.
     */
    public void handleSelectCheckBoxEvent() {
        if (view.getCheckBoxSelected()) {
            view.setPercentageSliderValue(view.getMaximumPercentageSliderValue());
        } else {
            view.setPercentageSliderValue(model.getLastPercentage());
        }
    }

    /**
     * Maneja el evento de cambio en el deslizador de porcentaje.
     */
    public void handlePercentageSliderChangeEvent() {
        view.setCheckBoxSelected(view.getPercentageSliderValue() == view.getMaximumPercentageSliderValue());

        if (view.getPercentageSliderValue() != view.getMaximumPercentageSliderValue())
            model.setLastPercentage(view.getPercentageSliderValue());
    }

    /**
     * Maneja el evento de agregar una nueva categoría.
     */
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

    /**
     * Maneja el evento de guardar una tarea.
     */
    public void handleSaveButtonEvent() {
        if (!model.isValidName(view.getNameTextFieldValue().trim())) {
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

        view.setActionStatusLabel("Tarea '" + view.getNameTextFieldValue() + "' guardada.");
        Task newTask = new Task(view.getNameTextFieldValue().trim(), view.getDescriptionTextAreaValue().trim(), view.getPriorityValue(), view.getDateValue(), view.getPercentageSliderValue(), view.getSelectedCategory(), view.getSelectedSubTask());

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
        view.updateTaskList(model.getTasks());

        view.restartTaskTextFields();
    }

    /**
     * Maneja el evento de selección de una tarea.
     */
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

    /**
     * Maneja el evento de eliminar una tarea.
     */
    public void handleDeleteButtonEvent() {
        if (view.getTaskSelected() == null) {
            view.showErrorModal("Selecciona una tarea para eliminar.");
            return;
        }

        view.setActionStatusLabel("Tarea '" + view.getTaskSelected().getName() + "' borrada.");

        model.removeTask(view.getTaskSelected());
        view.updateTaskList(model.getTasks());
    }

    /**
     * Maneja el evento de editar una tarea.
     */
    public void handleEditButtonEvent() {
        if (view.getTaskSelected() == null) {
            view.showErrorModal("Debes seleccionar una tarea para poder editarla.");
            return;
        }

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

    /**
     * Maneja el evento de escritura en el campo de búsqueda.
     */
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

    /**
     * Maneja el evento de aceptar los filtros en el diálogo.
     */
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

    /**
     * Maneja el evento de cancelar el diálogo de filtros.
     */
    public void handleCancelFiltersDialog() {
        view.closeFilterDialog();
    }

    /**
     * Maneja el evento de resetear los filtros en el diálogo.
     */
    public void handleResetFiltersDialog() {
        view.setActionStatusLabel("Reiniciando filtros...");

        view.setPriorityFilterValue(0);
        view.setPercentageFilterNoButton(true);
        view.setPercentageFilterValue(50);
        view.setCreationDateFilterNoButton(true);
        view.setCreationFilterChooserDate(new Date());
        view.setDeadLineFilterNoButton(true);
        view.setDeadlineFilterChooserDate(new Date());
        view.setSelectCategoryFilterValue(0);
        view.setSubtaskFilterValue(0);

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

    public ArrayList<TaskList> getTaskLists() {
        return model.getTaskLists();
    }
}
