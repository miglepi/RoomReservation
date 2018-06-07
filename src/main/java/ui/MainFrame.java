package ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JPanel panel;
    private JTextField title;
    private JButton checkInBtn;
    private JButton checkOutBtn;
    private JButton currentStatusBtn;
    private JButton historyBtn;

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(panel);
        setLocationRelativeTo(null);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setTitle("Room reservation system");
    }

    public JButton getCheckInButton() {
        return checkInBtn;
    }

    public JButton getCheckOutBtn() {
        return checkOutBtn;
    }

    public JButton getCurrentStatusBtn() {
        return currentStatusBtn;
    }

    public JButton getHistoryBtn() {
        return historyBtn;
    }
}
