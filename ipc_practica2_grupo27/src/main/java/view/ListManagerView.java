package view;

import controller.ListManagerController;
import main.Main;
import model.ListElement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListManagerView extends JFrame {
    private JPanel mainPanel;
    private JPanel leftListManagerPanel;
    private JPanel rightListManagerPanel;
    private JTextField searchTextField;
    private JButton addListButton;
    private JList<ListElement> listOfLists;
    private DefaultListModel<ListElement> listOfListsModel;
    private JPanel pendingTasksPanel;
    private JPanel completedTasksPanel;
    private JLabel pendingLabel;
    private JLabel completedLabel;
    private JLabel numberPendingTaskLabel;
    private JLabel numberCompletedTaskLabel;
    private JPanel labelPendingPanel;
    private JPanel labelCompletedPanel;
    private JPanel listSearchAddPanel;
    private JScrollPane pendingScrollpane;
    private JScrollPane completedScrollpane;
    private JButton returnButton;
    private JPanel returnPanel;
    private JPanel addListPanel;
    private JPanel listSearchPanel;

    private static final Color BACKGROUND_COLOR = new Color(161, 197, 255);
    private static final Color FOREGROUND_COLOR = new Color(51, 51, 51);
    private static final Color ELEMENTS_COLOR = new Color(231, 240, 253);

    private static final Font FONT = new Font("Helvetica", Font.BOLD, 14);

    private final AddListView addListView;

    private final ListManagerController listManagerController;

    public ListManagerView() {
        initComponents();

        mainPanel.setLayout(new GridLayout(1, 2));

        listManagerController = new ListManagerController(this);
        addListView = new AddListView(listManagerController);


        addListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddListDialog();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getPrincipalMenuView().showPrincipalMenuView();
            }
        });
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

    public void updateTasksList(ArrayList<ListElement> taskLists) {
        listOfListsModel.clear();

        for (ListElement taskList : taskLists) {
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

        listOfLists.setFont(FONT);
        searchTextField.setFont(FONT);
        addListButton.setFont(FONT);
        pendingLabel.setFont(FONT);
        completedLabel.setFont(FONT);
        returnButton.setFont(FONT);

        String placeholder = "Ingrese su nombre";

        searchTextField.setText(placeholder);
        searchTextField.setForeground(Color.GRAY);

        listOfListsModel = new DefaultListModel<>();
        listOfLists.setModel(listOfListsModel);

        listOfLists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // TEMPORAL
        ListElement listElement = new ListElement("IPC");
        updateTasksList(new ArrayList<>(List.of(listElement)));

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
        pendingTasksPanel.setBackground(BACKGROUND_COLOR);
        completedTasksPanel.setBackground(BACKGROUND_COLOR);
        listSearchAddPanel.setBackground(BACKGROUND_COLOR);
        addListPanel.setBackground(BACKGROUND_COLOR);
        listSearchPanel.setBackground(BACKGROUND_COLOR);
        returnPanel.setBackground(BACKGROUND_COLOR);
        pendingScrollpane.setBackground(ELEMENTS_COLOR);
        completedScrollpane.setBackground(ELEMENTS_COLOR);
        listOfLists.setBackground(ELEMENTS_COLOR);
        listOfLists.setForeground(FOREGROUND_COLOR);
        searchTextField.setBackground(ELEMENTS_COLOR);
        searchTextField.setForeground(FOREGROUND_COLOR);
        addListButton.setBackground(ELEMENTS_COLOR);
        addListButton.setForeground(FOREGROUND_COLOR);
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
}
