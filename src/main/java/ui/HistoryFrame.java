package ui;

import javax.swing.*;

public class HistoryFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JPanel panel;
    private JButton backBtn;
    private JComboBox roomBox;
    private JTextField title;
    private JTextField statusTf;
    private JTextArea historyArea;

    public HistoryFrame() {
        setTitle("Room history");
        setSize(WIDTH, HEIGHT);
        setContentPane(panel);
        setLocationRelativeTo(null);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        statusTf.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JComboBox getRoomBox() {
        return roomBox;
    }

    public JTextField getStatusTf() {
        return statusTf;
    }

    public JTextArea getHistoryArea() {
        return historyArea;
    }

}
