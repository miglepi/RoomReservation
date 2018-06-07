package ui;

import javax.swing.*;

public class CheckOutFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JPanel panel;
    private JButton backBtn;
    private JList list;
    private JButton checkOutBtn;
    private JTextField title;

    public CheckOutFrame() {
        setTitle("Check out a guest");
        setSize(WIDTH, HEIGHT);
        setContentPane(panel);
        setLocationRelativeTo(null);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        checkOutBtn.setEnabled(false);
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JList getList() {
        return list;
    }

    public JButton getCheckOutBtn() {
        return checkOutBtn;
    }
}
