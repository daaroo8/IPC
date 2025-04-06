package view;

import controller.ListManagerController;
import main.Main;
import model.Task;
import model.TaskList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListManagerView extends JFrame {
    private JPanel mainPanel;
    private JPanel leftListManagerPanel;
    private JPanel rightListManagerPanel;
    private JTextField searchTextField;
    private JButton addListButton;
    private JList<TaskList> listOfLists;
    private DefaultListModel<TaskList> listOfListsModel;
    private JPanel pendingTasksPanel;
    private JPanel completedTasksPanel;
    private JLabel pendingLabel;
    private JLabel completedLabel;
    private JLabel numberPendingTaskLabel;
    private JLabel numberCompletedTaskLabel;
    private JPanel labelPendingPanel;
    private JPanel labelCompletedPanel;
    private JPanel listSearchAddPanel;
    private JScrollPane pendingScrollPanel;
    private JScrollPane completedScrollPanel;
    private JButton returnButton;
    private JPanel returnPanel;
    private JPanel addListPanel;
    private JPanel listSearchPanel;
    private JButton deleteListButton;
    private JPanel pendingInsidePanel;
    private JPanel completedInsidePanel;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    private static final Font FONT = new Font("Helvetica", Font.BOLD, 18);
    private static final Font FONT_ELEMENTS = new Font("Helvetica", Font.BOLD, 16);

    private final AddListView addListView;

    private final ListManagerController listManagerController;

    public ListManagerView() {
        listManagerController = new ListManagerController(this);
        addListView = new AddListView(listManagerController);

        initComponents();

        mainPanel.setLayout(new GridLayout(1, 2));

        addListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddListDialog();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getViewManager().showPrincipalMenuView();
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
                listManagerController.handleKeyTypedSearchInputEvent(e.getKeyChar());
            }
        });
        deleteListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listManagerController.handleDeleteList();
            }
        });
        listOfLists.addListSelectionListener(new ListSelectionListener() {
            /**
             * Maneja el evento de selección de un ítem en la lista de tareas.
             * Este evento se dispara cuando el usuario selecciona una tarea de la lista,
             * y se encarga de llamar al controlador para manejar la selección de la tarea.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listManagerController.handleSelectListEvent();
            }
        });
    }

    public TaskList getSelectedTaskList() {
        return listOfLists.getSelectedValue();
    }

    public void setPendingCount(int count) {
        numberPendingTaskLabel.setText(String.valueOf(count));
    }

    public void setCompletedCount(int count) {
        numberCompletedTaskLabel.setText(String.valueOf(count));
    }

    public void updateTaskListInformation(TaskList taskList) {
        ArrayList<Task> pendingTasks = taskList.getPendingTasks();
        ArrayList<Task> completedTasks = taskList.getCompletedTasks();

        setPendingCount(pendingTasks.size());
        setCompletedCount(completedTasks.size());

        updatePendingPanel(pendingTasks);
        updateCompletedPanel(completedTasks);
    }

    public void updatePendingPanel(ArrayList<Task> pendingTasks) {
        pendingInsidePanel.removeAll();
        pendingInsidePanel.setLayout(new BoxLayout(pendingInsidePanel, BoxLayout.Y_AXIS));
        for (Task task : pendingTasks) {
            JPanel taskPanel = new JPanel();
            taskPanel.setLayout(new BorderLayout());
            taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            taskPanel.setBackground(new Color(0, 0, 100));
            taskPanel.setPreferredSize(new Dimension(0, 100));
            taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

            JLabel taskLabel = new JLabel(task.getName());
            taskLabel.setFont(FONT_ELEMENTS);
            taskLabel.setForeground(FOREGROUND_COLOR);
            taskPanel.add(taskLabel, BorderLayout.CENTER);

            pendingInsidePanel.add(taskPanel);
        }

        pendingInsidePanel.revalidate();
        pendingInsidePanel.repaint();

        pendingScrollPanel.setViewportView(pendingInsidePanel);
    }

    public void updateCompletedPanel(ArrayList<Task> completedTasks) {

    }

    public void closeAddListDialog() {
        addListView.setVisible(false);
    }

    public void restartAddTaskListInput() {
        addListView.restartAddTaskListInput();
    }

    public String getNewTaskListTextValue() {
        return addListView.getNewTaskListTextValue();
    }

    public String getSearchTextFieldValue() {
        return searchTextField.getText();
    }

    public void updateTasksList(ArrayList<TaskList> taskLists) {
        listOfListsModel.clear();

        for (TaskList taskList : taskLists) {
            listOfListsModel.addElement(taskList);
        }
    }

    public void showAddListDialog() {
        addListView.pack();
        addListView.setResizable(false);
        addListView.setLocationRelativeTo(null);
        addListView.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void initComponents() {
        searchTextField.setBorder(new EmptyBorder(5, 10, 5, 10));
        listOfLists.setBorder(new EmptyBorder(5, 10, 5, 10));

        listOfLists.setFont(FONT_ELEMENTS);
        searchTextField.setFont(FONT_ELEMENTS);
        addListButton.setFont(FONT_ELEMENTS);
        pendingLabel.setFont(FONT);
        numberPendingTaskLabel.setFont(FONT);
        completedLabel.setFont(FONT);
        numberCompletedTaskLabel.setFont(FONT);
        returnButton.setFont(FONT_ELEMENTS);

        String placeholder = "Ingrese su nombre";

        searchTextField.setText(placeholder);
        searchTextField.setForeground(Color.GRAY);

        listOfListsModel = new DefaultListModel<>();
        listOfLists.setModel(listOfListsModel);

        listOfLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        updateTasksList(listManagerController.getTaskLists());

        initColors();
    }

    public void initColors(){
        leftListManagerPanel.setBorder(new EmptyBorder(20, 20, 20, 10));
        rightListManagerPanel.setBorder(new EmptyBorder(20, 10, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);
        leftListManagerPanel.setBackground(BACKGROUND_COLOR);
        rightListManagerPanel.setBackground(BACKGROUND_COLOR);
        labelPendingPanel.setBackground(BACKGROUND_COLOR);
        labelCompletedPanel.setBackground(BACKGROUND_COLOR);
        completedLabel.setForeground(FOREGROUND_COLOR);
        pendingLabel.setForeground(FOREGROUND_COLOR);
        numberPendingTaskLabel.setForeground(FOREGROUND_COLOR);
        numberCompletedTaskLabel.setForeground(FOREGROUND_COLOR);
        pendingTasksPanel.setBackground(BACKGROUND_COLOR);
        completedTasksPanel.setBackground(BACKGROUND_COLOR);
        listSearchAddPanel.setBackground(BACKGROUND_COLOR);
        addListPanel.setBackground(BACKGROUND_COLOR);
        listSearchPanel.setBackground(BACKGROUND_COLOR);
        returnPanel.setBackground(BACKGROUND_COLOR);
        pendingScrollPanel.setBackground(ELEMENTS_COLOR);
        completedScrollPanel.setBackground(ELEMENTS_COLOR);
        listOfLists.setBackground(ELEMENTS_COLOR);
        listOfLists.setForeground(FOREGROUND_COLOR);
        searchTextField.setBackground(ELEMENTS_COLOR);
        searchTextField.setForeground(FOREGROUND_COLOR);
        addListButton.setBackground(ELEMENTS_COLOR);
        addListButton.setForeground(FOREGROUND_COLOR);
        deleteListButton.setBackground(ELEMENTS_COLOR);
        deleteListButton.setForeground(FOREGROUND_COLOR);
        returnButton.setBackground(ELEMENTS_COLOR);
        returnButton.setForeground(FOREGROUND_COLOR);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new ListManagerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 600));
        frame.pack();
        frame.setSize(frame.getWidth(), 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        addListButton = new JButton();

        ImageIcon addIcon = new ImageIcon(getClass().getResource("/add.png"));
        Image addImg = addIcon.getImage();
        Image resizedAddImg = addImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedAddIcon = new ImageIcon(resizedAddImg);

        addListButton.setIcon(resizedAddIcon);

        deleteListButton = new JButton();

        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/delete.png"));
        Image deleteImg = deleteIcon.getImage();
        Image resizedDeleteImg = deleteImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedDeleteIcon = new ImageIcon(resizedDeleteImg);

        deleteListButton.setIcon(resizedDeleteIcon);

    }
}
