package ui;

import javax.swing.*;

public class CheckInFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JPanel panel;
    private JButton backBtn;
    private JButton registerBtn;
    private JTextField nameTf;
    private JTextField surnameTf;
    private JTextField title;

    public CheckInFrame() {
        setTitle("Register a guest");
        setContentPane(panel);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        registerBtn.setEnabled(false);
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public JTextField getNameTf() {
        return nameTf;
    }

    public JTextField getSurnameTf() {
        return surnameTf;
    }
}
