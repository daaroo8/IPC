package view;

import com.toedter.calendar.JDateChooser;
import model.enums.Priority;
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
import model.enums.RangeSelections;

/**
 * Clase que representa la vista del Gestor de Tareas
 */
public class TaskManagerView extends JFrame {
    private JTextField nameTextField;
    private JLabel nameLabel;
    private JTextArea descriptionTextArea;
    private JLabel descriptionLabel;
    private JComboBox<Priority> priorityComboBox;
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
    private JLabel actionStatusLabel;
    private JDateChooser dateChooser;

    public static final String SELECT_CATEGORY_PLACEHOLDER = "Seleccionar opción";
    public static final String SELECT_NOT_SUBTASK_PLACEHOLDER = "No es subtarea";
    public static final int DEFAULT_PERCENTAGE = 50;

    private final TaskManagerController taskManagerController;
    private final TaskManagerFilterDialog filterDialogView;

    /**
     * Constructor de la vista del gestor de tareas.
     */
    public TaskManagerView() {
        initComponents();
        taskManagerController = new TaskManagerController(this);
        filterDialogView = new TaskManagerFilterDialog(taskManagerController);
    }

    /**
     * Muestra el cuadro de diálogo para los filtros de tareas.
     */
    public void showFilterDialog() {
        filterDialogView.pack();
        filterDialogView.setLocationRelativeTo(null);
        filterDialogView.setVisible(true);
    }

    /**
     * Cierra el cuadro de diálogo de filtros de tareas.
     */

    public void closeFilterDialog() {
        filterDialogView.setVisible(false);
    }

    /**
     * Obtiene el valor del filtro de prioridad desde el cuadro de diálogo.
     *
     * @return El valor del filtro de prioridad.
     */

    public String getPriorityFilterValue() {
        return filterDialogView.getPriorityFilterValue();
    }

    /**
     * Obtiene el modo de filtro de porcentaje desde el cuadro de diálogo.
     *
     * @return El modo de filtro de porcentaje.
     */
    public RangeSelections getPercentageFilterMode() {
        return filterDialogView.getPercentageFilterMode();
    }

    /**
     * Obtiene el valor del filtro de porcentaje desde el cuadro de diálogo.
     *
     * @return El valor del filtro de porcentaje.
     */
    public int getPercentageFilterValue() {
        return filterDialogView.getPercentageFilterValue();
    }

    public RangeSelections getCreationChooserFilterMode() {
        return filterDialogView.getCreationChooserFilterMode();
    }

    /**
     * Obtiene la fecha seleccionada en el selector de fecha de creación desde el cuadro de diálogo.
     *
     * @return La fecha seleccionada en el selector de fecha de creación.
     */
    public LocalDate getCreationChooserDate() {
        return filterDialogView.getCreationChooserDate();
    }

    /**
     * Obtiene el modo de filtro de la fecha límite desde el cuadro de diálogo.
     *
     * @return El modo de filtro de la fecha límite.
     */
    public RangeSelections getDeadlineChooserFilterMode() {
        return filterDialogView.getDeadlineChooserFilterMode();
    }

    /**
     * Obtiene la fecha seleccionada en el selector de fecha límite desde el cuadro de diálogo.
     *
     * @return La fecha seleccionada en el selector de fecha límite.
     */
    public LocalDate getDeadlineChooserDate() {
        return filterDialogView.getDeadlineChooserDate();
    }

    /**
     * Obtiene el valor del filtro de categoría desde el cuadro de diálogo.
     *
     * @return El valor del filtro de categoría.
     */
    public String getSelectCategoryFilterValue() {
        return filterDialogView.getSelectCategoryFilterValue();
    }

    /**
     * Obtiene el valor del filtro de subtareas desde el cuadro de diálogo.
     *
     * @return El valor del filtro de subtareas.
     */
    public String getSubtaskFilterValue() {
        return filterDialogView.getSubtaskFilterValue();
    }

    /**
     * Obtiene el estado de selección del checkbox de tareas completadas.
     *
     * @return true si el checkbox está seleccionado, false en caso contrario.
     */
    public boolean getCheckBoxSelected() {
        return completedCheckBox.isSelected();
    }

    /**
     * Establece el estado de selección del checkbox de tareas completadas.
     *
     * @param selected El estado de selección a establecer (true o false).
     */
    public void setCheckBoxSelected(boolean selected) {
        completedCheckBox.setSelected(selected);
    }

    /**
     * Establece el valor del campo de texto para agregar una categoría.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setAddCategoryTextFieldValue(String text) {
        addCategoryTextField.setText(text);
    }

    /**
     * Obtiene el valor actual del slider de porcentaje.
     *
     * @return El valor del slider de porcentaje.
     */
    public int getPercentageSliderValue() {
        return percentageSlider.getValue();
    }

    /**
     * Establece el valor del slider de porcentaje.
     *
     * @param percentage El valor a establecer en el slider de porcentaje.
     */
    public void setPercentageSliderValue(int percentage) {
        percentageSlider.setValue(percentage);
    }

    /**
     * Obtiene el valor máximo del slider de porcentaje.
     *
     * @return El valor máximo del slider de porcentaje.
     */
    public int getMaximumPercentageSliderValue() {
        return percentageSlider.getMaximum();
    }

    /**
     * Obtiene el valor actual del campo de texto para agregar una categoría.
     *
     * @return El texto del campo de texto.
     */
    public String getAddCategoryTextFieldValue() {
        return addCategoryTextField.getText();
    }

    /**
     * Obtiene el valor actual del campo de texto para el nombre.
     *
     * @return El texto del campo de texto para el nombre.
     */
    public String getNameTextFieldValue() {
        return nameTextField.getText();
    }

    /**
     * Obtiene el valor actual del área de texto para la descripción.
     *
     * @return El texto del área de texto para la descripción.
     */
    public String getDescriptionTextAreaValue() {
        return descriptionTextArea.getText();
    }

    /**
     * Obtiene el valor de la fecha seleccionada en el selector de fecha.
     *
     * @return La fecha seleccionada, o null si no se ha seleccionado ninguna.
     */
    public LocalDate getDateValue() {
        if (dateChooser.getDate() == null)
            return null;

        return dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Obtiene la categoría seleccionada en el combo box de categorías.
     *
     * @return La categoría seleccionada.
     */
    public String getSelectedCategory() {
        return (String) categoryComboBox.getSelectedItem();
    }

    /**
     * Obtiene la subtarea seleccionada en el combo box de subtareas.
     *
     * @return La subtarea seleccionada.
     */
    public String getSelectedSubTask() {
        return (String) subtaskComboBox.getSelectedItem();
    }

    /**
     * Obtiene el valor de prioridad seleccionado en el combo box de prioridad.
     *
     * @return El valor de prioridad seleccionado.
     */
    public Priority getPriorityValue() {
        return priorityComboBox.getItemAt(priorityComboBox.getSelectedIndex());
    }

    /**
     * Reinicia los campos de texto y controles relacionados con la tarea.
     */
    public void restartTaskTextFields() {
        nameTextField.setText("");
        descriptionTextArea.setText("");
        dateChooser.setDate(new Date());
        priorityComboBox.setSelectedIndex(0);
        percentageSlider.setValue(TaskManagerView.DEFAULT_PERCENTAGE);
        categoryComboBox.setSelectedIndex(0);
        subtaskComboBox.setSelectedIndex(0);
    }

    /**
     * Actualiza la lista de tareas en la vista.
     *
     * @param tasks La lista de tareas a mostrar en la vista.
     */
    public void updateTaskList(ArrayList<Task> tasks) {
        taskListModel.clear();

        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }

    /**
     * Actualiza la lista de categorías en el combo box de categorías.
     *
     * @param categories La lista de categorías a mostrar en el combo box.
     */
    public void updateCategoriesList(ArrayList<String> categories) {
        categoryComboBox.removeAllItems();

        categoryComboBox.addItem(TaskManagerView.SELECT_CATEGORY_PLACEHOLDER);

        for (String category : categories) {
            categoryComboBox.addItem(category);
        }

        filterDialogView.updateCategoriesList(categories);
    }

    /**
     * Actualiza la lista de subtareas en el combo box de subtareas.
     *
     * @param subtasks La lista de subtareas a mostrar en el combo box.
     */
    public void updateSubtasksList(ArrayList<Task> subtasks) {
        subtaskComboBox.removeAllItems();

        subtaskComboBox.addItem(TaskManagerView.SELECT_NOT_SUBTASK_PLACEHOLDER);

        for (Task subtask : subtasks) {
            subtaskComboBox.addItem(subtask.getName());
        }

        filterDialogView.updateSubtaskList(subtasks);
    }

    /**
     * Actualiza la lista de prioridades en el combo box de prioridad.
     */
    public void updatePriorityList() {
        priorityComboBox.removeAllItems();

        for (Priority p : Priority.values()) {
            priorityComboBox.addItem(p);
        }
    }

    /**
     * Elimina un ítem de la lista de categorías en el combo box.
     *
     * @param categoryIndex El índice del ítem a eliminar.
     */
    public void removeCategoryItemAt(int categoryIndex) {
        categoryComboBox.removeItemAt(categoryIndex);
    }

    /**
     * Obtiene la tarea seleccionada de la lista de tareas.
     *
     * @return La tarea seleccionada.
     */
    public Task getTaskSelected() {
        return taskList.getSelectedValue();
    }

    /**
     * Establece si el botón de edición está habilitado o deshabilitado.
     *
     * @param enabled true para habilitar el botón, false para deshabilitarlo.
     */
    public void setEditButtonEnabled(boolean enabled) {
        editButton.setEnabled(enabled);
    }

    /**
     * Establece el valor del campo de texto para el nombre.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setNameTextFieldValue(String text) {
        nameTextField.setText(text);
    }

    /**
     * Establece el valor del área de texto para la descripción.
     *
     * @param text El texto a establecer en el área de texto.
     */
    public void setDescriptionTextAreaValue(String text) {
        descriptionTextArea.setText(text);
    }

    /**
     * Establece el valor del combo box de prioridad.
     *
     * @param priority El valor de prioridad a establecer en el combo box.
     */
    public void setPriorityComboBoxValue(Priority priority) {
        priorityComboBox.setSelectedItem(priority);
    }

    /**
     * Establece el valor del selector de fecha.
     *
     * @param value La fecha a establecer en el selector de fecha.
     */
    public void setDateChooserValue(LocalDate value) {
        dateChooser.setDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Establece el valor del combo box de categorías.
     *
     * @param category La categoría a establecer en el combo box.
     */
    public void setCategoryComboBoxValue(String category) {
        categoryComboBox.setSelectedItem(category);
    }

    /**
     * Establece el valor del combo box de subtareas.
     *
     * @param subtask La subtarea a establecer en el combo box.
     */
    public void setSubtaskComboBoxValue(String subtask) {
        subtaskComboBox.setSelectedItem(subtask);
    }

    /**
     * Establece el valor del campo de texto para la información del nombre.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setNameInfoTextFieldValue(String text) {
        nameInfoTextField.setText(text);
    }

    /**
     * Establece el valor del área de texto para la información de la descripción.
     *
     * @param text El texto a establecer en el área de texto.
     */
    public void setDescriptionInfoTextAreaValue(String text) {
        descriptionInfoTextArea.setText(text);
    }

    /**
     * Establece el valor del campo de texto para la información de la prioridad.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setPriorityInfoTextFieldValue(String text) {
        priorityInfoTextField.setText(text);
    }

    /**
     * Establece el valor de la barra de progreso de tarea completada.
     *
     * @param progress El valor a establecer en la barra de progreso.
     */
    public void setCompletedProgressBarValue(int progress) {
        completedProgressBar.setValue(progress);
    }

    /**
     * Establece el valor del campo de texto para la información de la categoría.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setCategoryInfoTextFieldValue(String text) {
        categoryInfoTextField.setText(text);
    }

    /**
     * Establece el valor del campo de texto para la información de la subtarea.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setSubtaskInfoTextFieldValue(String text) {
        subtaskInfoTextField.setText(text);
    }

    /**
     * Establece el valor del campo de texto con formato para la información de la fecha de creación.
     *
     * @param text El texto a establecer en el campo de texto con formato.
     */
    public void setDateCreationInfoFormattedTextFieldValue(String text) {
        dateCreationInfoFormattedTextField.setText(text);
    }

    /**
     * Establece el valor del campo de texto para la información de la fecha límite.
     *
     * @param text El texto a establecer en el campo de texto.
     */
    public void setDeadLineInfoTextFieldValue(String text) {
        deadLineInfoFormattedTextField.setText(text);
    }

    /**
     * Establece el valor del filtro de prioridad.
     *
     * @param indexPrior Indice de la prioridad seleccionada.
     */
    public void setPriorityFilterValue(int indexPrior){
        filterDialogView.setPriorityFilterValue(indexPrior);
    }

    /**
     * Establece el valor del filtro de categoría seleccionada.
     *
     * @param indexPrior Indice de la categoría seleccionada.
     */
    public void setSelectCategoryFilterValue(int indexPrior){
        filterDialogView.setSelectCategoryFilterValue(indexPrior);
    }

    /**
     * Establece el valor del filtro de subtarea.
     *
     * @param indexPrior Indice de la subtarea seleccionada.
     */
    public void setSubtaskFilterValue(int indexPrior){
        filterDialogView.setSubtaskFilterValue(indexPrior);
    }

    /**
     * Establece el estado del botón de filtro de porcentaje.
     *
     * @param mode true para activar el modo sin botón, false en caso contrario.
     */
    public void setPercentageFilterNoButton(boolean mode){
        filterDialogView.setPercentageFilterNoButton(mode);
    }

    /**
     * Establece el valor del filtro de porcentaje.
     *
     * @param percentage Valor del porcentaje a filtrar.
     */
    public void setPercentageFilterValue(int percentage){
        filterDialogView.setPercentageFilterValue(percentage);
    }

    /**
     * Establece el estado del botón de filtro de fecha de creación.
     *
     * @param mode true para activar el modo sin botón, false en caso contrario.
     */
    public void setCreationDateFilterNoButton(boolean mode){
        filterDialogView.setCreationDateFilterNoButton(mode);
    }

    /**
     * Establece la fecha seleccionada para el filtro de creación.
     *
     * @param date Fecha a establecer en el filtro de creación.
     */
    public void setCreationFilterChooserDate(Date date) {
        filterDialogView.setCreationFilterChooserDate(date);
    }

    /**
     * Establece el estado del botón de filtro de fecha límite.
     *
     * @param mode true para activar el modo sin botón, false en caso contrario.
     */
    public void setDeadLineFilterNoButton(boolean mode){
        filterDialogView.setDeadLineFilterNoButton(mode);
    }

    /**
     * Establece la fecha seleccionada para el filtro de fecha límite.
     *
     * @param date Fecha a establecer en el filtro de fecha límite.
     */
    public void setDeadlineFilterChooserDate(Date date) {
        filterDialogView.setDeadlineFilterChooserDate(date);
    }

    /**
     * Obtiene el valor actual del campo de texto para la búsqueda.
     *
     * @return El texto del campo de búsqueda.
     */
    public String getSearchTextFieldValue() {
        return searchTextField.getText();
    }

    /**
     * Muestra un panel de error con el mensaje proporcionado.
     *
     * @param message El mensaje de error a mostrar en el modal.
     */
    public void showErrorModal(String message) {
        JOptionPane.showMessageDialog(null, message, "¡Error!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Establece el texto del label de estado de acción.
     *
     * @param str El texto a establecer en el label de estado de acción.
     */
    public void setActionStatusLabel(String str) {
        actionStatusLabel.setText(str);
    }

    /**
     * Inicializa los componentes de la interfaz de usuario.
     *
     * @throws RuntimeException Si alguno de los componentes no puede ser inicializado correctamente.
     */
    private void initComponents() {
        if (anyComponentIsNull())
            throw new RuntimeException("No se puede inicializar el componente");


        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(new Date());
        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(dateChooser, BorderLayout.CENTER);

        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        leftPanel.setBorder(new EmptyBorder(0, 0, 0, 30));
        rightTopPanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        taskListModel = new DefaultListModel<>();
        taskList.setModel(taskListModel);
        taskList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        headerListTextField.setFont(new Font("Monospaced", Font.PLAIN, 14));

        nameInfoTextField.setEditable(false);
        descriptionInfoTextArea.setEditable(false);
        categoryInfoTextField.setEditable(false);
        subtaskInfoTextField.setEditable(false);
        dateCreationInfoFormattedTextField.setEditable(false);
        deadLineInfoFormattedTextField.setEditable(false);
        priorityInfoTextField.setEditable(false);
        headerListTextField.setEditable(false);

        updatePriorityList();

        completedCheckBox.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de acción del checkbox de tareas completadas.
             * Este evento se dispara cuando el usuario selecciona o deselecciona el checkbox,
             * y se encarga de llamar al controlador para manejar el cambio de estado de la tarea.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleSelectCheckBoxEvent();
            }
        });

        percentageSlider.addChangeListener(new ChangeListener() {
            /**
             * Maneja el evento de cambio de valor del slider de porcentaje.
             * Este evento se dispara cuando el usuario cambia el valor del slider de porcentaje,
             * y se encarga de llamar al controlador para manejar el cambio en el porcentaje de la tarea.
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                taskManagerController.handlePercentageSliderChangeEvent();
            }
        });

        addCategoryButton.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de acción del botón para agregar una categoría.
             * Este evento se dispara cuando el usuario hace clic en el botón de agregar categoría,
             * y se encarga de llamar al controlador para manejar la adición de una nueva categoría.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleAddCategoryEvent();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de acción del botón de guardar.
             * Este evento se dispara cuando el usuario hace clic en el botón de guardar,
             * y se encarga de llamar al controlador para manejar el evento de guardar los cambios.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleSaveButtonEvent();
            }
        });

        categoryComboBox.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de selección de un ítem en el combo box de categorías.
             * Este evento se dispara cuando el usuario selecciona una categoría del combo box,
             * y se encarga de llamar al controlador para manejar la selección de la categoría.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleSelectComboBoxEvent();
            }
        });

        taskList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Maneja el evento de selección de un ítem en la lista de tareas.
             * Este evento se dispara cuando el usuario selecciona una tarea de la lista,
             * y se encarga de llamar al controlador para manejar la selección de la tarea.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                taskManagerController.handleSelectTaskEvent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de acción del botón de eliminar.
             * Este evento se dispara cuando el usuario hace clic en el botón de eliminar,
             * y se encarga de llamar al controlador para manejar la eliminación de la tarea seleccionada.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleDeleteButtonEvent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de acción del botón de editar.
             * Este evento se dispara cuando el usuario hace clic en el botón de editar,
             * y se encarga de llamar al controlador para manejar la edición de la tarea seleccionada.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleEditButtonEvent();
            }
        });

        searchTextField.addKeyListener(new KeyAdapter() {
            /**
             * Maneja el evento de escritura en el campo de texto de búsqueda.
             * Este evento se dispara cuando el usuario escribe un carácter en el campo de texto de búsqueda,
             * y se encarga de llamar al controlador para manejar la entrada de búsqueda y filtrar los resultados.
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                taskManagerController.handleKeyTypedSearchInputEvent(e.getKeyChar());
            }
        });

        filtersButton.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de acción del botón de filtros.
             * Este evento se dispara cuando el usuario hace clic en el botón de filtros,
             * y se encarga de llamar al controlador para abrir el cuadro de diálogo de filtros.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleOpenFilterDialogEvent();
            }
        });
    }

    /**
     * Verifica si alguno de los componentes de la interfaz de usuario es nulo.
     *
     * @return true si alguno de los componentes es nulo, false en caso contrario.
     */
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestor de tareas");
        frame.setContentPane(new TaskManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
