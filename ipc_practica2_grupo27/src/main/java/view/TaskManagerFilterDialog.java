package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import controller.TaskManagerController;
import model.Task;
import model.enums.RangeSelections;

/**
 * Clase que representa el dialogo de filtros de la vista
 */
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
    private JPanel mainPanel;
    private JPanel dialogPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JDateChooser dateCreationChooser;
    private JDateChooser deadLineChooser;

    public static final String SELECT_CATEGORY_PLACEHOLDER = "Seleccionar opción";
    public static final String SELECT_NOT_SUBTASK_PLACEHOLDER = "No es subtarea";
    public static final String NOT_FILTER_SELECTED_TEXT = "No";

    private final TaskManagerController taskManagerController;

    /**
     * Constructor de la clase TaskManagerFilterDialog.
     *
     * @param taskManagerController El controlador que gestiona la lógica del gestor de tareas.
     */
    public TaskManagerFilterDialog(TaskManagerController taskManagerController) {
        initComponents();

        this.taskManagerController = taskManagerController;
    }

    /**
     * Actualiza la lista de categorías en el filtro de tareas.
     *
     * @param categories La lista de categorías a mostrar en el filtro.
     */
    public void updateCategoriesList(ArrayList<String> categories) {
        categoryFilterComboBox.removeAllItems();

        categoryFilterComboBox.addItem(NOT_FILTER_SELECTED_TEXT);
        for (String category : categories) {
            categoryFilterComboBox.addItem(category);
        }
    }

    /**
     * Actualiza la lista de subtareas en el filtro de tareas.
     *
     * @param subtasks La lista de subtareas a mostrar en el filtro.
     */
    public void updateSubtaskList(ArrayList<Task> subtasks) {
        subtaskFilterComboBox.removeAllItems();

        subtaskFilterComboBox.addItem(NOT_FILTER_SELECTED_TEXT);
        subtaskFilterComboBox.addItem(SELECT_NOT_SUBTASK_PLACEHOLDER);

        for (Task subtask : subtasks) {
            subtaskFilterComboBox.addItem(subtask.getName());
        }
    }

    /**
     * Obtiene el valor seleccionado en el filtro de prioridad.
     *
     * @return El valor de la prioridad seleccionada o null si no se ha seleccionado ninguna.
     */
    public String getPriorityFilterValue() {
        if (priorityFilterComboBox.getSelectedItem() == null || priorityFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return priorityFilterComboBox.getSelectedItem().toString();
    }

    /**
     * Obtiene el modo de filtro seleccionado para el porcentaje.
     *
     * @return El modo de filtro de porcentaje seleccionado (UNTIL, SINCE o NO).
     */
    public RangeSelections getPercentageFilterMode() {
        if (untilPercentageRadioButton.isSelected()) {
            return RangeSelections.UNTIL;
        }

        if (fromPercentageRadioButton.isSelected()) {
            return RangeSelections.SINCE;
        }

        return RangeSelections.NO;
    }
    /**
     * Obtiene el valor del filtro de porcentaje seleccionado.
     *
     * @return El valor actual del slider de porcentaje.
     */
    public int getPercentageFilterValue() {
        return percentageFilterSlider.getValue();
    }

    /**
     * Obtiene el modo de filtro seleccionado para la fecha de creación.
     *
     * @return El modo de filtro de fecha de creación seleccionado (UNTIL, SINCE o NO).
     */
    public RangeSelections getCreationChooserFilterMode() {
        if (untilDateCreationRadioButton.isSelected()) {
            return RangeSelections.UNTIL;
        }

        if (sinceDateCreationRadioButton.isSelected()) {
            return RangeSelections.SINCE;
        }

        return RangeSelections.NO;
    }

    /**
     * Obtiene la fecha seleccionada en el filtro de fecha de creación.
     *
     * @return La fecha de creación seleccionada o null si no se ha seleccionado ninguna.
     */
    public LocalDate getCreationChooserDate() {
        if (dateCreationChooser.getDate() == null)
            return null;

        return dateCreationChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Obtiene el modo de filtro seleccionado para la fecha de vencimiento.
     *
     * @return El modo de filtro de fecha de vencimiento seleccionado (UNTIL, SINCE o NO).
     */
    public RangeSelections getDeadlineChooserFilterMode() {
        if (untilDeadLineRadioButton.isSelected()) {
            return RangeSelections.UNTIL;
        }

        if (sinceDeadLineRadioButton.isSelected()) {
            return RangeSelections.SINCE;
        }

        return RangeSelections.NO;
    }

    /**
     * Obtiene la fecha seleccionada en el filtro de fecha de vencimiento.
     *
     * @return La fecha de vencimiento seleccionada o null si no se ha seleccionado ninguna.
     */
    public LocalDate getDeadlineChooserDate() {
        if (deadLineChooser.getDate() == null)
            return null;

        return deadLineChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Obtiene el valor seleccionado en el filtro de categoría.
     *
     * @return El valor de la categoría seleccionada o null si no se ha seleccionado ninguna opción.
     */
    public String getSelectCategoryFilterValue() {
        if (categoryFilterComboBox.getSelectedItem() == null || categoryFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return categoryFilterComboBox.getSelectedItem().toString();
    }

    /**
     * Obtiene el valor seleccionado en el filtro de subtareas.
     *
     * @return El valor de la subtarea seleccionada o null si no se ha seleccionado ninguna opción.
     */
    public String getSubtaskFilterValue() {
        if (subtaskFilterComboBox.getSelectedItem() == null || subtaskFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return subtaskFilterComboBox.getSelectedItem().toString();
    }

    /**
     * Establece el valor del filtro de prioridad seleccionando el índice correspondiente en el combo box.
     *
     * @param indexPrior El índice de la prioridad que se debe seleccionar en el combo box.
     */
    public void setPriorityFilterValue(int indexPrior) {
        priorityFilterComboBox.setSelectedIndex(indexPrior);
    }

    /**
     * Establece el valor del filtro de categoría seleccionando el índice correspondiente en el combo box.
     *
     * @param indexPrior El índice de la categoría que se debe seleccionar en el combo box.
     */
    public void setSelectCategoryFilterValue(int indexPrior) {
        categoryFilterComboBox.setSelectedIndex(indexPrior);
    }

    /**
     * Establece el valor del filtro de subtarea seleccionando el índice correspondiente en el combo box.
     *
     * @param indexPrior El índice de la subtarea que se debe seleccionar en el combo box.
     */
    public void setSubtaskFilterValue(int indexPrior) {
        subtaskFilterComboBox.setSelectedIndex(indexPrior);
    }

    /**
     * Establece el estado del botón de filtro "sin porcentaje", seleccionando o deseleccionando el radio button correspondiente.
     *
     * @param mode El estado que debe tener el botón: true para seleccionarlo, false para deseleccionarlo.
     */
    public void setPercentageFilterNoButton(boolean mode) {
        noPercentageRadioButton.setSelected(mode);
    }

    /**
     * Establece el estado del botón de filtro "sin fecha límite", seleccionando o deseleccionando el radio button correspondiente.
     *
     * @param mode El estado que debe tener el botón: true para seleccionarlo, false para deseleccionarlo.
     */
    public void setDeadLineFilterNoButton(boolean mode) {
        noDeadLineRadioButton.setSelected(mode);
    }

    /**
     * Establece la fecha en el selector de fecha para el filtro de fecha de vencimiento.
     *
     * @param date La fecha a establecer en el selector de fecha.
     */
    public void setDeadlineFilterChooserDate(Date date) {
        deadLineChooser.setDate(date);
    }

    /**
     * Establece el estado del botón de radio para no aplicar el filtro de fecha de creación.
     *
     * @param mode El estado (verdadero o falso) para el botón de radio.
     */
    public void setCreationDateFilterNoButton(boolean mode) {
        noDateCreationRadioButton.setSelected(mode);
    }

    /**
     * Establece la fecha seleccionada en el selector de fecha de creación.
     *
     * @param date La fecha que se desea establecer en el selector de fecha de creación.
     */
    public void setCreationFilterChooserDate(Date date) {
        dateCreationChooser.setDate(date);
    }

    /**
     * Establece el estado del botón "sin porcentaje".
     *
     * @param mode El estado a establecer: verdadero para seleccionado, falso de lo contrario.
     */
    public void setPercentageNoButton(boolean mode) {
        noDeadLineRadioButton.setSelected(mode);
    }

    /**
     * Establece el valor del filtro de porcentaje.
     *
     * @param percentage El valor de porcentaje a establecer.
     */
    public void setPercentageFilterValue(int percentage) {
        percentageFilterSlider.setValue(percentage);
    }

    /**
     * Inicializa los componentes de la interfaz de usuario para el diálogo de filtros.
     * Establece las configuraciones predeterminadas y asigna las acciones de los botones.
     *
     * @throws RuntimeException Si algún componente es nulo al intentar inicializar.
     */
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

        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        dialogPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        leftPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        rightPanel.setBorder(new EmptyBorder(0, 10, 0, 0));

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonReset.addActionListener(new ActionListener() {
            /**
             * Acción para el botón de resetear filtros.
             * Llama al controlador para resetear los filtros aplicados.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleResetFiltersDialog();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            /**
             * Acción para el botón de aceptar filtros.
             * Llama al controlador para aplicar los filtros seleccionados.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleAcceptFiltersDialog();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            /**
             * Acción para el botón de cancelar filtros.
             * Llama al controlador para cancelar la selección de filtros y cerrar el diálogo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                taskManagerController.handleCancelFiltersDialog();
            }
        });
    }

    /**
     * Verifica si algún componente necesario es nulo.
     *
     * @return true si algún componente es nulo, false si todos los componentes están inicializados.
     */
    private boolean anyComponentIsNull() {
        return (
                dateCreationPanel == null ||
                        dialogPanel == null ||
                        mainPanel == null ||
                        rightPanel == null ||
                        leftPanel == null ||
                        deadLinePanel == null ||
                        buttonOK == null ||
                        buttonCancel == null ||
                        buttonReset == null
        );
    }
}
