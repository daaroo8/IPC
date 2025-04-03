package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    private JComboBox<String> listFilterComboBox;
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
    private JLabel priorityLabel;
    private JLabel dateCreationLabel;
    private JLabel deadLineLabel;
    private JLabel listLabel;
    private JLabel categoryLabel;
    private JLabel percentajeLabel;
    private JPanel priorityPanel;
    private JPanel dateCreationPanelBackGround;
    private JPanel deadLinePanelBackGround;
    private JPanel percentagePanel;
    private JPanel categoryPanel;
    private JPanel listPanel;
    private JPanel buttonsPanel;
    private JDateChooser dateCreationChooser;
    private JDateChooser deadLineChooser;

    public static final String SELECT_CATEGORY_PLACEHOLDER = "Seleccionar opción";
    public static final String SELECT_NOT_SUBTASK_PLACEHOLDER = "No es subtarea";
    public static final String NOT_FILTER_SELECTED_TEXT = "No";
    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    private static final Font FONT = new Font("Helvetica", Font.BOLD, 16);
    private static final Font FONT_ELEMENTS = new Font("Helvetica", Font.BOLD, 14);

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

    public void initColors() {
        contentPane.setBackground(BACKGROUND_COLOR);
        buttonOK.setBackground(ELEMENTS_COLOR);
        buttonCancel.setBackground(ELEMENTS_COLOR);
        buttonReset.setBackground(ELEMENTS_COLOR);
        priorityFilterComboBox.setBackground(ELEMENTS_COLOR);
        percentageFilterSlider.setBackground(BACKGROUND_COLOR);
        percentageFilterSlider.setForeground(FOREGROUND_COLOR);
        categoryFilterComboBox.setBackground(ELEMENTS_COLOR);
        listFilterComboBox.setBackground(ELEMENTS_COLOR);
        dateCreationPanel.setBackground(BACKGROUND_COLOR);
        deadLinePanel.setBackground(BACKGROUND_COLOR);
        sinceDateCreationPanel.setBackground(BACKGROUND_COLOR);
        sinceDeadLinePanel.setBackground(BACKGROUND_COLOR);
        fromPercentagePanel.setBackground(BACKGROUND_COLOR);
        untilPercentageRadioButton.setBackground(BACKGROUND_COLOR);
        untilPercentageRadioButton.setForeground(FOREGROUND_COLOR);
        untilPercentageRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        fromPercentageRadioButton.setBackground(BACKGROUND_COLOR);
        fromPercentageRadioButton.setForeground(FOREGROUND_COLOR);
        fromPercentageRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        untilDateCreationRadioButton.setBackground(BACKGROUND_COLOR);
        untilDateCreationRadioButton.setForeground(FOREGROUND_COLOR);
        untilDateCreationRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        sinceDateCreationRadioButton.setBackground(BACKGROUND_COLOR);
        sinceDateCreationRadioButton.setForeground(FOREGROUND_COLOR);
        sinceDateCreationRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        untilDeadLineRadioButton.setBackground(BACKGROUND_COLOR);
        untilDeadLineRadioButton.setForeground(FOREGROUND_COLOR);
        untilDeadLineRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        sinceDeadLineRadioButton.setBackground(BACKGROUND_COLOR);
        sinceDeadLineRadioButton.setForeground(FOREGROUND_COLOR);
        sinceDeadLineRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        noPercentageRadioButton.setBackground(BACKGROUND_COLOR);
        noPercentageRadioButton.setForeground(FOREGROUND_COLOR);
        noPercentageRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        noDateCreationRadioButton.setBackground(BACKGROUND_COLOR);
        noDateCreationRadioButton.setForeground(FOREGROUND_COLOR);
        noDateCreationRadioButton.setBorder(new LineBorder(FOREGROUND_COLOR, 2));
        noDeadLineRadioButton.setBackground(BACKGROUND_COLOR);
        noDeadLineRadioButton.setForeground(FOREGROUND_COLOR);
        mainPanel.setBackground(BACKGROUND_COLOR);
        dialogPanel.setBackground(BACKGROUND_COLOR);
        leftPanel.setBackground(BACKGROUND_COLOR);
        rightPanel.setBackground(BACKGROUND_COLOR);
        dateCreationChooser.setBackground(ELEMENTS_COLOR);
        dateCreationChooser.getDateEditor().getUiComponent().setBackground(ELEMENTS_COLOR);
        dateCreationChooser.getDateEditor().getUiComponent().setForeground(FOREGROUND_COLOR);
        dateCreationChooser.getCalendarButton().setBackground(ELEMENTS_COLOR);
        dateCreationChooser.getCalendarButton().setForeground(FOREGROUND_COLOR);
        deadLineChooser.setBackground(ELEMENTS_COLOR);
        deadLineChooser.getDateEditor().getUiComponent().setBackground(ELEMENTS_COLOR);
        deadLineChooser.getDateEditor().getUiComponent().setForeground(FOREGROUND_COLOR);
        deadLineChooser.getCalendarButton().setBackground(ELEMENTS_COLOR);
        deadLineChooser.getCalendarButton().setForeground(FOREGROUND_COLOR);
        priorityLabel.setBackground(BACKGROUND_COLOR);
        priorityLabel.setForeground(FOREGROUND_COLOR);
        dateCreationLabel.setBackground(BACKGROUND_COLOR);
        dateCreationLabel.setForeground(FOREGROUND_COLOR);
        deadLineLabel.setBackground(BACKGROUND_COLOR);
        deadLineLabel.setForeground(FOREGROUND_COLOR);
        listLabel.setBackground(BACKGROUND_COLOR);
        listLabel.setForeground(FOREGROUND_COLOR);
        categoryLabel.setBackground(BACKGROUND_COLOR);
        categoryLabel.setForeground(FOREGROUND_COLOR);
        percentajeLabel.setBackground(BACKGROUND_COLOR);
        percentajeLabel.setForeground(FOREGROUND_COLOR);
        priorityPanel.setBackground(BACKGROUND_COLOR);
        categoryPanel.setBackground(BACKGROUND_COLOR);
        dateCreationPanelBackGround.setBackground(BACKGROUND_COLOR);
        deadLinePanelBackGround.setBackground(BACKGROUND_COLOR);
        listPanel.setBackground(BACKGROUND_COLOR);
        percentagePanel.setBackground(BACKGROUND_COLOR);
        buttonsPanel.setBackground(BACKGROUND_COLOR);
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
        listFilterComboBox.removeAllItems();

        listFilterComboBox.addItem(NOT_FILTER_SELECTED_TEXT);
        listFilterComboBox.addItem(SELECT_NOT_SUBTASK_PLACEHOLDER);

        for (Task subtask : subtasks) {
            listFilterComboBox.addItem(subtask.getName());
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
        if (listFilterComboBox.getSelectedItem() == null || listFilterComboBox.getSelectedItem().equals(NOT_FILTER_SELECTED_TEXT))
            return null;

        return listFilterComboBox.getSelectedItem().toString();
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
        listFilterComboBox.setSelectedIndex(indexPrior);
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

        ImageIcon calendarIcon = new ImageIcon(getClass().getResource("/calendar.png"));
        Image calendarImg = calendarIcon.getImage();
        Image resizedCalendarImg = calendarImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedCalendarIcon = new ImageIcon(resizedCalendarImg);

        dateCreationChooser.getCalendarButton().setIcon(resizedCalendarIcon);
        dateCreationChooser.getCalendarButton().revalidate();
        dateCreationChooser.getCalendarButton().repaint();

        deadLineChooser.getCalendarButton().setIcon(resizedCalendarIcon);
        deadLineChooser.getCalendarButton().revalidate();
        deadLineChooser.getCalendarButton().repaint();

        dateCreationChooser.setDateFormatString("dd/MM/yyyy");
        deadLineChooser.setDateFormatString("dd/MM/yyyy");

        dateCreationChooser.setDate(new Date());
        deadLineChooser.setDate(new Date());

        dateCreationChooser.getDateEditor().getUiComponent().setBorder(new EmptyBorder(5, 10, 5, 10));
        deadLineChooser.getDateEditor().getUiComponent().setBorder(new EmptyBorder(5, 10, 5, 10));

        dateCreationPanel.setLayout(new BorderLayout());
        dateCreationPanel.add(dateCreationChooser, BorderLayout.CENTER);

        deadLinePanel.setLayout(new BorderLayout());
        deadLinePanel.add(deadLineChooser, BorderLayout.CENTER);


        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        dialogPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        leftPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        rightPanel.setBorder(new EmptyBorder(0, 10, 0, 0));

        dateCreationChooser.getDateEditor().getUiComponent().setFont(FONT_ELEMENTS);
        deadLineChooser.getDateEditor().getUiComponent().setFont(FONT_ELEMENTS);
        buttonOK.setFont(FONT_ELEMENTS);
        buttonCancel.setFont(FONT_ELEMENTS);
        priorityFilterComboBox.setFont(FONT_ELEMENTS);
        percentageFilterSlider.setFont(FONT_ELEMENTS);
        categoryFilterComboBox.setFont(FONT_ELEMENTS);
        listFilterComboBox.setFont(FONT_ELEMENTS);
        untilPercentageRadioButton.setFont(FONT_ELEMENTS);
        fromPercentageRadioButton.setFont(FONT_ELEMENTS);
        untilDateCreationRadioButton.setFont(FONT_ELEMENTS);
        sinceDateCreationRadioButton.setFont(FONT_ELEMENTS);
        untilDeadLineRadioButton.setFont(FONT_ELEMENTS);
        sinceDeadLineRadioButton.setFont(FONT_ELEMENTS);
        noPercentageRadioButton.setFont(FONT_ELEMENTS);
        noDateCreationRadioButton.setFont(FONT_ELEMENTS);
        noDeadLineRadioButton.setFont(FONT_ELEMENTS);
        buttonReset.setFont(FONT_ELEMENTS);
        priorityLabel.setFont(FONT);
        dateCreationLabel.setFont(FONT);
        deadLineLabel.setFont(FONT);
        listLabel.setFont(FONT);
        categoryLabel.setFont(FONT);
        percentajeLabel.setFont(FONT);
        dateCreationChooser.setFont(FONT_ELEMENTS);
        deadLineChooser.setFont(FONT_ELEMENTS);


        initColors();
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
