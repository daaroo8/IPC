package view;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    private JFrame actualState;

    public ViewManager() {
        actualState = new JFrame();
        showPrincipalMenuView();
    }

    public void showPrincipalMenuView() {
        if (actualState != null) {
            actualState.dispose();
            actualState.setVisible(false);
        }

        actualState.setContentPane(new PrincipalMenuView().getMainPanel());
        actualState.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actualState.setResizable(false);
        actualState.pack();
        actualState.setLocationRelativeTo(null);
        actualState.setVisible(true);
    }

    public void showTaskManagerView() {
        if (actualState != null) {
            actualState.setVisible(false);
            actualState.dispose();
        }

        actualState.setContentPane(new TaskManagerView().getMainPanel());
        actualState.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actualState.setMinimumSize(new Dimension(1400, 800));
        actualState.setSize(1400, 800);
        actualState.setResizable(false);
        actualState.setLocationRelativeTo(null);
        actualState.setVisible(true);
    }

    public void showListManagerView() {
        if (actualState != null) {
            actualState.setVisible(false);
            actualState.dispose();
        }

        actualState.setContentPane(new ListManagerView().getMainPanel());
        actualState.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actualState.setMinimumSize(new Dimension(1000, 700));
        actualState.pack();
        actualState.setResizable(true);
        actualState.setSize(actualState.getWidth(), 600);
        actualState.setLocationRelativeTo(null);
        actualState.setVisible(true);
    }
}