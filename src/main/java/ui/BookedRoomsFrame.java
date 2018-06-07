package ui;

import javax.swing.*;

public class BookedRoomsFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JPanel panel;
    private JTextField title;
    private JButton backBtn;
    private JList list;

    public BookedRoomsFrame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(panel);
        setLocationRelativeTo(null);
        setTitle("Booked hotel rooms");
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JList getList() {
        return list;
    }
}
