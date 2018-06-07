package ui;

import core.RoomManager;
import entity.Guest;

import javax.swing.*;
import java.awt.event.*;

public class CheckInFrameController {

    private CheckInFrame checkInFrame;
    private JButton backBtn;
    private JButton registerBtn;
    private JTextField nameTf;
    private JTextField surnameTf;
    private RoomManager roomManager;

    public CheckInFrameController(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
        initListeners();
    }

    public void showCheckInFrameWindow() {
        checkInFrame.setVisible(true);
    }

    private void initComponents() {
        checkInFrame = new CheckInFrame();
        backBtn = checkInFrame.getBackBtn();
        registerBtn = checkInFrame.getRegisterBtn();
        nameTf = checkInFrame.getNameTf();
        surnameTf = checkInFrame.getSurnameTf();
    }

    private void initListeners() {
        backBtn.addActionListener(new BackBtnLister());
        registerBtn.addActionListener(new RegisterBtnLister());
        nameTf.addKeyListener(new InputKeyAdapter());
        surnameTf.addKeyListener(new InputKeyAdapter());
    }

    private class BackBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrameController mainFrameController = new MainFrameController(roomManager);
            mainFrameController.showMainFrameWindow();
            checkInFrame.setVisible(false);
        }
    }

    private class RegisterBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameTf.getText();
            String surname = surnameTf.getText();
            Guest guest = new Guest(name, surname);
            if (!name.equals("") && !surname.equals("")) {
                int roomNumber = roomManager.checkIn(guest);
                if (roomNumber != -1) {
                    JOptionPane.showMessageDialog(null,
                            "Registration successful! Booked room nr: " + roomNumber);
                    MainFrameController mainFrameController = new MainFrameController(roomManager);
                    mainFrameController.showMainFrameWindow();
                    checkInFrame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Unfortunately, there's no available room.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter guest name and surname!");
            }
        }
    }

    private class InputKeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            enableButton();
            int code = e.getKeyCode();
            char c = e.getKeyChar();
            if(!Character.isLetter(c) && code!=KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            enableButton();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            enableButton();
        }
    }

    public void enableButton() {
        if (nameTf.getText().equals("") || surnameTf.getText().equals(""))
        {
            registerBtn.setEnabled(false);
        }
        else
        {
            registerBtn.setEnabled(true);
        }
    }
}
